package com.example.ago.travlendarandroidclient;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class addEventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bDate,bHour;
    private EditText eDate,eHour;
    private int anno,mese,giorno,ora,minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
/*
        bDate= findViewById(R.id.btn_data);
        bHour= findViewById(R.id.btn_ora);
        eDate= findViewById(R.id.e_data);
        eHour= findViewById(R.id.e_ora);*/

        //bDate.setOnClickListener(this);
        //bHour.setOnClickListener(this);



        /**grsphic status bar configuration**/
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
        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Add New Event");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_text));
        myToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));*/
    }

    @Override
    public void onClick(View v) {
        if(v==bDate){
            final Calendar c=Calendar.getInstance();
            giorno=c.get(Calendar.DAY_OF_MONTH);
            anno=c.get(Calendar.YEAR);
            mese=c.get(Calendar.MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.AppTheme_DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                }
            },anno,mese,giorno);
            datePickerDialog.show();
        }
        if(v==bHour){
            final Calendar c=Calendar.getInstance();
            ora=c.get(Calendar.HOUR_OF_DAY);
            minuto=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.AppTheme_DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(minute<10 && hourOfDay<10) {
                        eHour.setText("0" + hourOfDay + ":0" + minute);
                    }else{
                        if(minute<10) {
                            eHour.setText(hourOfDay + ":0" + minute);
                        }else if(hourOfDay<10){
                            eHour.setText("0"+hourOfDay+":"+minute);
                        }else{
                            eHour.setText(hourOfDay+":"+minute);
                        }
                    }
                }
            },ora,minuto,true);
            timePickerDialog.show();

        }
    }
}
