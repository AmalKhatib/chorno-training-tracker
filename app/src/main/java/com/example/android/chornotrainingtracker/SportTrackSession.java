package com.example.android.chornotrainingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SportTrackSession extends AppCompatActivity {

    DBUtility dbUtility;
    SportSessionModel sportSessionModel;

    TextView speed;
    TextView distance;
    TextView time;
    TextView startTime, endTime;
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_track_session);

        //for db
        dbUtility = new DBUtility(this);

        speed = findViewById(R.id.TextView_speed);
        distance = findViewById(R.id.TextView_distance);
        time = findViewById(R.id.TextView_time);
        startTime = findViewById(R.id.TextView_startTime);
        endTime = findViewById(R.id.TextView_endTime);
        okButton = findViewById(R.id.Button_ok);

        speed.append(getIntent().getFloatExtra("speed", 0)+"");
        distance.append(getIntent().getFloatExtra("distance", 0)+"");
        time.append(getIntent().getStringExtra("time"));
        startTime.append(getIntent().getStringExtra("startTime"));
        endTime.append(getIntent().getStringExtra("endTime"));

        sportSessionModel = new SportSessionModel
                (startTime.getText().toString(),
                endTime.getText().toString(),
                getIntent().getStringExtra("date"),
                getIntent().getFloatExtra("speed", 0),
                getIntent().getFloatExtra("distance", 0),
                getIntent().getStringExtra("sportType"));
        
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inserting sportSession for the athlete
                String sportName = getIntent().getStringExtra("sportType");
                String athleteName = AthletesList.sharedPreferences.getString("athleteName", "");
                int athleteID = dbUtility.getAthleteID(athleteName);
                int sportID = dbUtility.getSportID(sportName);

                long result = dbUtility.insertSportSession(sportSessionModel, athleteID, sportID);
                if(result > 0){
                    Toast.makeText(SportTrackSession.this, "sport session saved.", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(SportTrackSession.this, AthleteAccount.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
