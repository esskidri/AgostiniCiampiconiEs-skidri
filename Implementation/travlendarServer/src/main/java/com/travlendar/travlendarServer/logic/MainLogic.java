package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.*;
import com.travlendar.travlendarServer.logic.util.EventGraph;
import com.travlendar.travlendarServer.logic.util.TimeRequest;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.model.enumModel.MeanType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainLogic {

    /**
     * Principal method that is called by Data Manager when transport solutions is needed.
     * The Algorithm that this method mask receive a list of ordered events and the user preferences
     * First of all the connection between events are recognized, then the solution calculated.
     *
     * @param events Ordered List of subsequent events interface given by the Data Manager (?) //TODO politics of giving list of event
     * @param user   User interface that have the event in the calendar, this parameter is useful to query the user preferences
     * @return
     */
    public static List<TransportSolutionLogic> calculateTransportSolutions(List<EventLogic> events, UserLogic user) {
        EventGraph eventGraph = EventConnector.findConnection(events);
        List<TransportSolutionLogic> transportSolutions = new ArrayList<>();
        TransportSolutionLogic transportSolutionLogic = null;
        Timestamp arrivalAtHome = null;
        List<MeanOfTransportLogic> meansOfTransport = user.getMeanPreferences();
        List<MeanOfTransportLogic> meansOfTransportForSolution;

        for (EventLogic outGoing : events) {
            for (EventLogic inGoing : eventGraph.edges().get(outGoing)) {
                if (inGoing.atHome()) {
                    for (EventLogic eventAfter : eventGraph.edges().get(inGoing)) {
                        if (!outGoing.overlapping(eventAfter)) {
                            meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport,
                                    outGoing.getCoordinates(),
                                    inGoing.getCoordinates(),
                                    outGoing.getEndDate(),
                                    eventAfter.getStartDate(),
                                    TimeRequest.DEPARTURE);
                            transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.DEPARTURE)).calculateSolution(outGoing.getCoordinates(),
                                    inGoing.getCoordinates(),
                                    outGoing.getEndDate(),
                                    eventAfter.getStartDate(),
                                    meansOfTransportForSolution);
                            arrivalAtHome = transportSolutionLogic.getArrivalTime();
                            break;
                        }
                    }
                } else if (outGoing.atHome()) {
                    //I assume that this algorithm is never asked to calculate solution from a home event when before
                    //it didn't calculate solution to reach home, this is granted by the structure and the behave of
                    // event connector
                    if (arrivalAtHome != null) {
                        meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport, outGoing.getCoordinates(), inGoing.getCoordinates(), arrivalAtHome, inGoing.getStartDate(), TimeRequest.ARRIVAL);
                        transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.ARRIVAL)).calculateSolution(outGoing.getCoordinates(), inGoing.getCoordinates(), arrivalAtHome, inGoing.getStartDate(), meansOfTransportForSolution);
                    } else {
                        //notificare l'impossibilità di arrivare a casa
                    }
                } else {
                    if (outGoing.overlapping(inGoing)) {
                        meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport,
                                outGoing.getCoordinates(),
                                inGoing.getCoordinates(),
                                outGoing.getStartDate(),
                                inGoing.getStartDate(),
                                TimeRequest.ARRIVAL);
                        transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.ARRIVAL)).calculateSolution(outGoing.getCoordinates(),
                                inGoing.getCoordinates(),
                                outGoing.getStartDate(),
                                inGoing.getStartDate(),
                                meansOfTransportForSolution);
                    } else {
                        meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport,
                                outGoing.getCoordinates(),
                                inGoing.getCoordinates(),
                                outGoing.getEndDate(),
                                inGoing.getStartDate(),
                                TimeRequest.ARRIVAL);
                        transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.ARRIVAL)).calculateSolution(outGoing.getCoordinates(),
                                inGoing.getCoordinates(),
                                outGoing.getEndDate(),
                                inGoing.getStartDate(),
                                meansOfTransportForSolution);
                    }
                }
                if (transportSolutionLogic != null) {
                    transportSolutionLogic.setStartEvent(outGoing);
                    transportSolutionLogic.setEndEvent(inGoing);
                    setMeansOfTransport(transportSolutionLogic, meansOfTransport, user);
                    if (!transportSolutionLogic.isEmpty())
                        transportSolutions.add(transportSolutionLogic);
                    transportSolutionLogic = null;
                }
            }
        }

        events = eventGraph.nodes();
        for (EventLogic eventLogic : events)
            if (eventLogic.atHome()) {
                eventLogic.setUser(user);
            }

        arrangeFreeTimes(user, events, transportSolutions);

        return transportSolutions;
    }

    private static void arrangeFreeTimes(UserLogic user, List<EventLogic> events, List<TransportSolutionLogic> transportSolutions) {
        List<FreeTimeLogic> freeTimes = user.getFreeTimesLogic();
        selectFreeTimes(freeTimes, events);

        for(FreeTimeLogic freeTime: freeTimes){
            if(freeTime.getStartDate().compareTo(events.get(0).getStartDate()) >= 0 &&
                    freeTime.getEndDate().compareTo(events.get(events.size() -1).getEndDate()) <=0)
                freeTime.setSatisfied(false);
        }

        for(FreeTimeLogic freeTime: freeTimes){
            for(TransportSolutionLogic transportSolution: transportSolutions){
                EventLogic event = transportSolution.getStartEvent();
                if(freeTime.getEndDate().compareTo(event.getEndDate()) > 0 ) {
                    EventLogic subsequentEvent = transportSolution.getEndEvent();
                    long timeBetween1 = event.getEndDate().getTime() -
                            transportSolution.getDepartureTime().getTime();
                    long timeBetween2 = subsequentEvent.getStartDate().getTime() - transportSolution.getArrivalTime().getTime();
                    if(freeTime.getDuration() <= timeBetween1){
                        freeTime.setSatisfied(true);
                        freeTime.setSpendingStartDate(event.getEndDate());
                        freeTime.setSpendingEndDate(new Timestamp(event.getEndDate().getTime() + freeTime.getDuration()));
                    }
                    else if(freeTime.getDuration() <= timeBetween2){
                        freeTime.setSatisfied(true);
                        freeTime.setSpendingStartDate(transportSolution.getArrivalTime());
                        freeTime.setSpendingEndDate(new Timestamp(transportSolution.getArrivalTime().getTime() + freeTime.getDuration()));
                    }
                    else if(freeTime.isSatisfied() &&
                            freeTime.getSpendingEndDate().compareTo(events.get(0).getStartDate()) >= 0 &&
                            freeTime.getSpendingStartDate().compareTo(events.get(events.size() -1).getEndDate()) <= 0)
                        freeTime.setSatisfied(false);
                }
            }
        }
    }

    /**
     * select free times to compare with transport solutions that has been calculated
     * @param freeTimes List from which select the free times
     * @param events list of events to decide if free times is coherent with the analyzed time
     */
    private static void selectFreeTimes(List<FreeTimeLogic> freeTimes, List<EventLogic> events) {
        List<FreeTimeLogic> readList = new ArrayList<>();
        readList.addAll(freeTimes);
        for(FreeTimeLogic freeTime: readList)
            if(freeTime.getEndDate().compareTo(events.get(0).getStartDate()) < 0 && freeTime.getStartDate().compareTo(events.get(events.size() -1).getEndDate()) > 0)
                freeTimes.remove(freeTime);
    }

    public static List<EventLogic> getDailyEventsForReplan(List<EventLogic> events, Timestamp startingDate, Timestamp endingDate) {
        boolean foundedDay = false;
        EventLogic firstEndEvent;
        EventLogic secondEndEvent = null;
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());

        int i = 0;
        for(EventLogic event: events){
            if(currentTime.compareTo(event.getEndDate()) < 0)
                break;
            i++;
        }

        if(!events.isEmpty())
            events = events.subList(i, events.size());

        firstEndEvent = events.get(0);

        for (EventLogic eventLogic : events) {
            if (eventLogic.isEndEvent() && eventLogic.getEndDate().compareTo(startingDate) <= 0)
                firstEndEvent = eventLogic;
            if (eventLogic.isEndEvent() && eventLogic.getStartDate().compareTo(endingDate) >= 0) {
                foundedDay = true;
                secondEndEvent = eventLogic;
                break;
            }
        }
        if (foundedDay)
            return events.subList(events.indexOf(firstEndEvent), events.indexOf(secondEndEvent) + 1);
        else
            return events.subList(events.indexOf(firstEndEvent), events.size());
    }

    private static void setMeansOfTransport(TransportSolutionLogic transportSolutionLogic,
                                            List<MeanOfTransportLogic> meansOfTransport,
                                            UserLogic user) {
        List<MeanOfTransportLogic> readList = new ArrayList<>();
        readList.addAll(meansOfTransport);

        if (transportSolutionLogic.getEndEvent().atHome())
            meansOfTransport = user.getMeanPreferences();
        else {
            for (MeanOfTransportLogic meanOfTransportLogic : readList)
                if (meanOfTransportLogic.isPrivate() && meanOfTransportLogic.getTypeOfTransport() != MeanType.WALKING && !transportSolutionLogic.getPrivateMeansUsed().contains(meanOfTransportLogic))
                    meansOfTransport.remove(meanOfTransportLogic);
        }
    }


}
