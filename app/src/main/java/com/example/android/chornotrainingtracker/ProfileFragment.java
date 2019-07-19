package com.example.android.chornotrainingtracker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProfileFragment extends android.support.v4.app.Fragment {

    DBUtility dbUtility;
    FavSportAdapter favSportAdapter;
    ArrayList<String> favSports;
    String athleteName = "";
    TextView athleteName_textView;
    RecyclerView favSportsRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        athleteName_textView = view.findViewById(R.id.TextView_athlete_name);
        favSportsRecyclerView = view.findViewById(R.id.RecycleView_sports);

        if(getArguments() != null){
            athleteName = getArguments().getString("athleteName");
            athleteName_textView.setText(getArguments().getString("athleteName"));
        }

        //to add athlete’s fav sports on the fragment
        dbUtility = new DBUtility(getActivity());

        favSports = dbUtility.getFavSports(dbUtility.getAthleteID(athleteName));

        favSportAdapter = new FavSportAdapter(favSports);

        //to set the array on the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        favSportsRecyclerView.setLayoutManager(layoutManager);
        favSportsRecyclerView.setAdapter(favSportAdapter);
        Toast.makeText(getContext(), favSports.size()+"", Toast.LENGTH_LONG);
        return view;

    }
}