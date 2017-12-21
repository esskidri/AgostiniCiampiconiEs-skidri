package com.example.ago.travlendarandroidclient;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ago.travlendarandroidclient.model.EventClient;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FloatingLabels extends AppCompatActivity implements View.OnClickListener {
    private EditText eStartDate,eEndDate,eStartHour,eEndHour,eName,eDescription,eLocation;
    private CheckBox cEndEvent;
    private int anno,mese,giorno,ora,minuto;
    private int startYear,startMonth,startDay,startHour,startMinute,endYear,endMonth,endDay,endHour,
                endMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_labels);
        /**setting the ids**/
        eStartDate=findViewById(R.id.editText4);
        eStartDate.setOnClickListener(this);
        eStartDate.setShowSoftInputOnFocus(false);

        eEndDate=findViewById(R.id.editText5);
        eEndDate.setOnClickListener(this);
        eEndDate.setShowSoftInputOnFocus(false);

        eStartHour=findViewById(R.id.editText25);
        eStartHour.setOnClickListener(this);
        eStartHour.setShowSoftInputOnFocus(false);

        eEndHour=findViewById(R.id.editText26);
        eEndHour.setOnClickListener(this);
        eEndHour.setShowSoftInputOnFocus(false);

        eName=findViewById(R.id.input_name);
        eDescription=findViewById(R.id.input_email);
        eLocation=findViewById(R.id.input_location);
        cEndEvent=findViewById(R.id.checkBox2);

        init(eStartDate,eEndDate,eStartHour,eEndHour);


        /**graphic status bar configuration**/
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorStatusBar));

        /**
         * ToolBar Configuration
         */

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("New Event");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_text));
        myToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insert_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this, "Saving", Toast.LENGTH_LONG).show();
                saveEvent();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    //todo integrare con chiamata al server
    private void saveEvent() {
        String name= eName.getText().toString();
        String location= eLocation.getText().toString();
        String description= eDescription.getText().toString();
        Calendar calendar= Calendar.getInstance();
        calendar.set(startYear,startMonth,startDay,startHour,startMinute);
        Timestamp startDate=new Timestamp(calendar.getTimeInMillis());
        Calendar calendar2= Calendar.getInstance();
        calendar2.set(endYear,endMonth,endDay,endHour,endMinute);
        Timestamp endDate=new Timestamp(calendar2.getTimeInMillis());
        int posX=Extra.getPosX(location);
        int posY=Extra.getPosY(location);

        EventClient newEvent=new EventClient(startDate,endDate,Extra.getPosX(location),
                                Extra.getPosY(location),description,name,cEndEvent.isChecked());
        UserSettings.getEvents().add(newEvent);
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
        //onBackPressed();
    }

    private void init(EditText sd,EditText ed,EditText sh, EditText eh){
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        /**configurazione start time**/
        startDay=calendar.get(Calendar.DAY_OF_MONTH);
        startHour=calendar.get(Calendar.HOUR_OF_DAY);
        startMonth=calendar.get(Calendar.MONTH);
        startYear=calendar.get(Calendar.YEAR);
        startMinute=0;
        sd.setText(weekDay+"   "+startDay+"/"+(startMonth+1)+"/"+startYear);
        sh.setText(""+Extra.fromCalendarTo24Hour(calendar));
        /**configurazione end time**/
        calendar.add(Calendar.HOUR, 1);
        weekDay = dayFormat.format(calendar.getTime());
        endDay=calendar.get(Calendar.DAY_OF_MONTH);
        endHour=calendar.get(Calendar.HOUR_OF_DAY);
        endMonth=calendar.get(Calendar.MONTH);
        endYear=calendar.get(Calendar.YEAR);
        endMinute=0;
        ed.setText(weekDay+"   "+endDay+"/"+(endMonth+1)+"/"+endYear);
        eh.setText(""+Extra.fromCalendarTo24Hour(calendar));
    }


    @Override
    public void onClick(View v) {

       if(v==eStartDate){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.AppTheme_DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String weekDay;
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month,dayOfMonth);
                    weekDay = dayFormat.format(calendar.getTime());
                    startDay=dayOfMonth;
                    startMonth=month;
                    startYear=year;
                    eStartDate.setText(weekDay+"   "+startDay+"/"+(startMonth+1)+"/"+startYear);
                }
            },startYear,startMonth,startDay);
            datePickerDialog.show();
        }
        if(v==eEndDate){

            final Calendar c=Calendar.getInstance();
            giorno=c.get(Calendar.DAY_OF_MONTH);
            anno=c.get(Calendar.YEAR);
            mese=c.get(Calendar.MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.AppTheme_DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String weekDay;
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month,dayOfMonth);
                    weekDay = dayFormat.format(calendar.getTime());
                    endDay=dayOfMonth;
                    endMonth=month;
                    endYear=year;
                    eEndDate.setText(weekDay+"   "+endDay+"/"+(endMonth+1)+"/"+endYear);
                }
            },endYear,endMonth,endDay);
            datePickerDialog.show();
        }
        if(v==eStartHour){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.AppTheme_DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startMinute=minute;
                    startHour=hourOfDay;
                    if(startMinute<10 && startHour<10) {
                        eStartHour.setText("0" + startHour + ":0" + startMinute);
                    }else{
                        if(startMinute<10) {
                            eStartHour.setText(startHour + ":0" + startMinute);
                        }else if(startHour<10){
                            eStartHour.setText("0"+startHour+":"+startMinute);
                        }else{
                            eStartHour.setText(startHour+":"+startMinute);
                        }
                    }
                }
            },startHour,startMinute,true);
            timePickerDialog.show();
        }
        if(v==eEndHour){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.AppTheme_DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endMinute=minute;
                    endHour=hourOfDay;
                    if(endMinute<10 && endHour<10) {
                        eEndHour.setText("0" + hourOfDay + ":0" + minute);
                    }else{
                        if(endMinute<10) {
                            eEndHour.setText(hourOfDay + ":0" + minute);
                        }else if(hourOfDay<10){
                            eEndHour.setText("0"+hourOfDay+":"+minute);
                        }else{
                            eEndHour.setText(hourOfDay+":"+minute);
                        }
                    }
                }
            },endHour,endMinute,true);
            timePickerDialog.show();
        }
    }


}
