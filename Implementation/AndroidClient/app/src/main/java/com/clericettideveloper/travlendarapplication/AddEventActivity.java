package com.clericettideveloper.travlendarapplication;

import android.app.DatePickerDialog;
import android.support.annotation.XmlRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.clericettideveloper.travlendarapplication.model.Event;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddEventActivity extends AppCompatActivity {
    private Event event = new Event();

    private EditText editStartDate = findViewById(R.id.editStartDate);
    private EditText editStartTime = findViewById(R.id.editStartTime);
    private EditText editEndDate = findViewById(R.id.editEndDate);
    private EditText editEndTime = findViewById(R.id.editEndTime);
    private EditText editLocation = findViewById(R.id.editLocation);

    private Switch endEvent = findViewById(R.id.switchEndEvent);

    private Button createEvent = findViewById(R.id.buttonCreate);

    private DatePickerDialog datePickerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField(editStartDate) ;
            }
        });



        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

    }

    private void setDateTimeField(EditText dateEditText) {
        Calendar newCalendar = new GregorianCalendar();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dateEditText.setText(dateFormatter.format(dateSelected.getTime()));
    }
}
