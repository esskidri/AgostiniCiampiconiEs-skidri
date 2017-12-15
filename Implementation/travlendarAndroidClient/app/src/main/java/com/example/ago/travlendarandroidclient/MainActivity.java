package com.example.ago.travlendarandroidclient;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,CalendarView.class);
        startActivity(intent);
    }
}
