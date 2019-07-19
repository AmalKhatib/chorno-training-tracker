package com.example.android.chornotrainingtracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class AthletesList extends Activity {


    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    AthletesAdapter athletesAdapter;
    ArrayList<String> sports;
    DBUtility dbUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athletes_list);

        sharedPreferences = getSharedPreferences("athleteInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AthletesList.this, AthleteForm.class);
                startActivity(intent);
            }
        });

        sports = new ArrayList<>();
        dbUtility = new DBUtility(this);

        //to show added athletes
        athletesAdapter = new AthletesAdapter(dbUtility.getAthletes());
        RecyclerView recyclerView = findViewById(R.id.RecycleView_athletes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AthletesList.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(AthletesList.this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(athletesAdapter);

        //to assign sports values
        sports.add("Running");
        sports.add("Cycling");
        sports.add("Freestyle swimming");
        sports.add("Butterfly stroke");
        sports.add("Breaststroke");
        sports.add("Backstroke");

        //to insert sports into DB
        for(int i=0; i<sports.size(); i++){
            dbUtility.insertSports(sports.get(i), i);
        }
    }
}
