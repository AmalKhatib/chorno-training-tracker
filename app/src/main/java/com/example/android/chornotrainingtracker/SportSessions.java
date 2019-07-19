package com.example.android.chornotrainingtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class SportSessions extends AppCompatActivity {


    DBUtility dbUtility;
    ActıvıtıesAdapter actıvıtıesAdapter;
    RecyclerView recyclerView;
    ArrayList<SportSessionModel> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_sessions);


        dbUtility = new DBUtility(this);
        recyclerView = findViewById(R.id.RecycleView_activities);

        activities = dbUtility.getSportsSessions(getIntent().getStringExtra("date"),
                dbUtility.getAthleteID(AthletesList.sharedPreferences.getString("athleteName", "")));

        //to show sessıons ın the spsefic date
        actıvıtıesAdapter = new ActıvıtıesAdapter(activities);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SportSessions.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(SportSessions.this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(actıvıtıesAdapter);
    }
}
