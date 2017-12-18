package com.example.ago.travlendarandroidclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;



import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.EventDay;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MaterialCalendarActivity extends AppCompatActivity {
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });
        List<EventDay> mEventDays = new ArrayList<>();
        CalendarView mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        Calendar c=Calendar.getInstance();
        c.set(2017, 11, 1);
        EventDay myEventDay = new EventDay(c,R.drawable.ic_dot_two);
        mEventDays.add(myEventDay);

        Calendar c1=Calendar.getInstance();
        c1.set(2017, 11, 2);
        EventDay myEventDay1 = new EventDay(c1,R.drawable.ic_dot_two_ok);
        mEventDays.add(myEventDay1);
        Calendar c2=Calendar.getInstance();
        c2.set(2017, 11, 9);
        EventDay myEventDay2 = new EventDay(c2,R.drawable.ic_dot_three_b);
        mEventDays.add(myEventDay2);

        mCalendarView.setEvents(mEventDays);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }*/




    }
    private void addNote() {
        //Intent intent = new Intent(this, AddNoteActivity.class);
       // startActivityForResult(intent, ADD_NOTE);
    }
    private void previewNote(EventDay eventDay) {
        //Intent intent = new Intent(this, NotePreviewActivity.class);
        //if(eventDay instanceof MyEventDay){
        //    intent.putExtra(EVENT, (MyEventDay) eventDay);
       // }
       // startActivity(intent);
    }
}
