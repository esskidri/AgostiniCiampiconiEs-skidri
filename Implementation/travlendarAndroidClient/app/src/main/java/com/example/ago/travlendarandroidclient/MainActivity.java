package com.example.ago.travlendarandroidclient;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ago.travlendarandroidclient.serverStub.ServerConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);

        final Button request = findViewById(R.id.request);
        request.setOnClickListener(MainActivity.this);
        /**graphic button configuration**/
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        request.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        button.setTextColor(getResources().getColor(R.color.toolbar_text));
        request.setTextColor(getResources().getColor(R.color.toolbar_text));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                Intent intent=new Intent(this,CalendarView.class);
                startActivity(intent);
                break;
                case R.id.request:
                //String s=serverConnection.fetchEvents((long) 6);
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
