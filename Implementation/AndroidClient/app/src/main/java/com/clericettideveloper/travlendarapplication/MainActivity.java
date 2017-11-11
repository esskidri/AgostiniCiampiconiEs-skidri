package com.clericettideveloper.travlendarapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.Map;
import io.skygear.skygear.AuthResponseHandler;
import io.skygear.skygear.Configuration;
import io.skygear.skygear.Container;
import io.skygear.skygear.Error;
import io.skygear.skygear.LambdaResponseHandler;
import io.skygear.skygear.Record;
import io.skygear.skygear.RecordSaveResponseHandler;

public class MainActivity extends AppCompatActivity {
    /*prova andrea*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Sky-gear CONFIGURATION**/
        Configuration config = new Configuration.Builder()
                .endPoint("https://travlendar.skygeario.com/")
                .apiKey("90bbd0894c6c408abf4c56f82df18d6f")
                .build();
        Container.defaultContainer(this).configure(config);

        /*Button configuration*/
        final TextView textView=findViewById(R.id.text1);
        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serverCall(textView);
            }
        });
    /******prova e  /
        /****************************************************************************************/
        /*Sky-gear insert a test row in the DB*//*
        final Container skygear = Container.defaultContainer(this);
        skygear.getAuth().signupAnonymously(new AuthResponseHandler() {
        @Override
        public void onAuthSuccess(Record user) {
            Log.i("MyApplication", "Signup successfully");

            // Create Record Type "test" and put "Hello world" as value of key "content"
            // Advanced: Skygear Server will create a table "test" and appropriate
            //           columns in PostgreSQL in Development mode.
            Record test = new Record("test");
            test.set("content", "Hello world");

            skygear.getPublicDatabase().save(test, new RecordSaveResponseHandler() {
                @Override
                public void onSaveSuccess(Record[] records) {
                    Log.i("MyApplication", "Record saved");
                }

                @Override
                public void onPartiallySaveSuccess(Map<String, Record> successRecords, Map<String, Error> errors) {
                    Log.i("MyApplication", "Some records are failed to save");
                }

                @Override
                public void onSaveFail(Error error) {
                    Log.w("MyApplication", "Failed to save: " + error.getMessage(), error);
                }
            });
        }

            @Override
            public void onAuthFail(Error error) {
                Log.w("MyApplication", "Failed to signup: " + error.getMessage(), error);
            }
    });*/

    }


    private void serverCall(final TextView textView){
        /*SkyGear retrieving data from my server lambdaExpression*/

        String lambdaName = "hello_from_server";
        final Container skygear = Container.defaultContainer(this);
        skygear.callLambdaFunction(lambdaName, new LambdaResponseHandler() {
            @Override
            public void onLambdaSuccess(JSONObject result) {
                System.out.println("hello from server ok");
                System.out.println(result.toString());
                textView.setText(result.toString());

            }

            @Override
            public void onLambdaFail(Error error) {
                System.out.println("hello from server ko");

            }
        });
    }
}
