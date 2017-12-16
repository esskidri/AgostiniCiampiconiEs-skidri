package com.example.ago.travlendarandroidclient;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.ago.travlendarandroidclient.serverStub.ServerConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //caricare info utente.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);
        final Button request = findViewById(R.id.request);
        request.setOnClickListener(MainActivity.this);
        final Button transportSolution = findViewById(R.id.transport);
        request.setOnClickListener(MainActivity.this);

        /**graphic button configuration**/
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        request.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        transportSolution.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        button.setTextColor(getResources().getColor(R.color.toolbar_text));
        request.setTextColor(getResources().getColor(R.color.toolbar_text));
        transportSolution.setTextColor(getResources().getColor(R.color.toolbar_text));
        /**graphic status bar configuration**/
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorStatusBar));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                    Intent intent=new Intent(this,CalendarView.class);
                    startActivity(intent);
                break;
            case R.id.request:
                Intent intentb=new Intent(this,EmptyActivity.class);
                startActivity(intentb);
                break;
            case R.id.transport:
                Intent intentc=new Intent(this,NavigationDrawer.class);
                startActivity(intentc);
                break;
            default:
                break;
        }
    }
}
