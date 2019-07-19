package com.example.android.chornotrainingtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class AthleteAccount extends AppCompatActivity {


    SharedPreferences sharedPreferences;


    static DBUtility dbUtility;

    Fragment profFrag = new ProfileFragment();
    Fragment sportFrag = new AthleteSportFragment();
    Fragment calendarFrag = new CalenderFragment();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    addFragmentt(profFrag);
                    return true;
                case R.id.navigation_activity:

                    addFragmentt(sportFrag);
                    return true;
                case R.id.navigation_calendar:

                    addFragmentt(calendarFrag);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_account);

        addFragmentt(profFrag);

        sharedPreferences = getSharedPreferences("athleteInfo", MODE_PRIVATE);

        dbUtility = new DBUtility(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        //to prepare profile fragment
        String athleteName = "";
        if(AthletesList.sharedPreferences.contains("athleteName") && AthletesList.sharedPreferences.contains("profilePic")){
            Bundle profFragBundle = new Bundle();
            profFragBundle.putString("athleteName", AthletesList.sharedPreferences.getString("athleteName", ""));

            athleteName = sharedPreferences.getString("athleteName", "");

            profFrag.setArguments(profFragBundle);
            addFragmentt(profFrag);
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int endIndex = athleteName.indexOf(" ");
        Snackbar.make(findViewById(R.id.container), "Welcome "+athleteName.substring(0, endIndex)+"!",
                Snackbar.LENGTH_LONG)
                .show();
    }

    public void addFragmentt(Fragment frag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, frag);
        fragmentTransaction.commit();
    }


}
