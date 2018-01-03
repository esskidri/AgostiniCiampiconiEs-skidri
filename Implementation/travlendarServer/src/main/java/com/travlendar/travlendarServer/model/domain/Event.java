package com.travlendar.travlendarServer.model.domain;

import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.model.clientModel.EventClient;
import com.travlendar.travlendarServer.model.clientModel.TransportSolutionClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event implements Serializable,  EventLogic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name = "pos_x")
    private float posX;

    @Column(name = "pos_y")
    private float posY;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "end_event")
    private  boolean endEvent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event1")
    private List<TransportSolution> transportSolutions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event2")
    private List<TransportSolution> transportSolutions2;

    @Column(name="at_home")
    private boolean atHome = false;

    public Event(){}

    public Event(Timestamp startDate, Timestamp endDate, float posX, float posY, String description, String name, boolean endEvent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
    }

    public Event(User u, Timestamp startDate, Timestamp endDate, Float posX, Float posY, String description, String name, Boolean endEvent){
        this.user=u;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEndEvent() {
        return endEvent;
    }

    public void setEndEvent(boolean endEvent) {
        this.endEvent = endEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TransportSolution> getTransportSolutions() {
        return transportSolutions;
    }

    public void setTransportSolutions(List<TransportSolution> transportSolutions) {
        this.transportSolutions = transportSolutions;
    }

    public List<TransportSolution> getTransportSolutions2() {
        return transportSolutions2;
    }

    public void setTransportSolutions2(List<TransportSolution> transportSolutions2) {
        this.transportSolutions2 = transportSolutions2;
    }
    public boolean getEndEvent() {
        return endEvent;
    }

    public void completeSet(User u, Timestamp startDate, Timestamp endDate, Float posX, Float posY, String description, String name, boolean endEvent) {
        this.user=u;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
    }



    /***
     *  Logic Interface Implementation
     */


    /***
     *
     * @param e
     * @return
     */

    @Override
    public boolean overlapping(EventLogic e){
        if(this.getEndDate() == null || this.getStartDate() == null || e.getEndDate() == null || e.getStartDate() == null )
            return false;
        if(this.compareTo(e) < 0 && e.getStartDate().compareTo(this.endDate) < 0)
            return true;
        return false;
    }

    @Override
    public boolean atHome() {
        return atHome;
    }

    @Override
    public Coordinates getCoordinates() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(posX);
        coordinates.setLng(posY);

        return coordinates;
    }

    @Override
    public Coordinates getCurrentHome() {
        //this method is created to be extended extra functionalities (like set a temporary home, f.e. when you are in hotel)
        return user.getHomeCoordinates();
    }


    @Override
    public int compareTo(@NotNull EventLogic e) {
        if(this.startDate.compareTo(e.getStartDate())!= 0)
            return this.startDate.compareTo(e.getStartDate());
        else
            return this.endDate.compareTo(e.getEndDate());
    }


    public EventClient getEventClient(){
        List<TransportSolutionClient> transportSolutionClientsA=new ArrayList<>();
        List<TransportSolutionClient> transportSolutionClientsB=new ArrayList<>();
        if(transportSolutions!=null)
        for(TransportSolution t:transportSolutions){
            transportSolutionClientsA.add(t.getTransportSolutionClient());
        }
        if(transportSolutions2!=null)
        for(TransportSolution t2:transportSolutions2){
            transportSolutionClientsB.add(t2.getTransportSolutionClient());
        }

        EventClient eventClient = new EventClient(this.id,this.startDate,this.endDate,this.posX,this.posY,
                                                    this.description,this.name,this.endEvent,transportSolutionClientsA,transportSolutionClientsB);


        return eventClient;
    }

    @Override
    public void setUser(UserLogic user) {
        this.user = (User) user;
    }

    @Override
    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

}
