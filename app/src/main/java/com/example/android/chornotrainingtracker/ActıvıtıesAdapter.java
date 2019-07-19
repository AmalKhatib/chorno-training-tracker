package com.example.android.chornotrainingtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ActıvıtıesAdapter extends RecyclerView.Adapter{

    ArrayList<SportSessionModel> activities;
    ActıvıtıesAdapter.ViewHolder viewHolder ;
    TextView startTime, endTime, sportType, speed, dıstance;

    public ActıvıtıesAdapter(ArrayList<SportSessionModel> attrs){
        activities = attrs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_custom, parent, false);
        viewHolder = new ActıvıtıesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolder.startTime.setText(activities.get(position).getStartTime());
        viewHolder.endTime.setText(activities.get(position).getEndTime());
        viewHolder.sportType.setText(activities.get(position).sportType);
        viewHolder.speed.append(activities.get(position).getSpeed()+"");
        viewHolder.dıstance.append(activities.get(position).getDistance()+"");
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View rootView ;
        TextView startTime, endTime, sportType, speed, dıstance;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            startTime = rootView.findViewById(R.id.TextView_startTime);
            endTime = rootView.findViewById(R.id.TextView_endTime);
            sportType = rootView.findViewById(R.id.TextView_sportType);
            speed = rootView.findViewById(R.id.TextView_speed);
            dıstance = rootView.findViewById(R.id.TextView_dıstance);
        }
    }
}