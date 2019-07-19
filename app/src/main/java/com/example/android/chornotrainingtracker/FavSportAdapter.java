package com.example.android.chornotrainingtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FavSportAdapter extends RecyclerView.Adapter {

    ArrayList<String> favSpots;
    ViewHolder viewHolder;

    public FavSportAdapter(ArrayList<String> attrs){
        favSpots = attrs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list, parent, false);
        viewHolder = new ViewHolder(view);
        viewHolder.favSportNaame = view.findViewById(R.id.TextView_sportName);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolder.favSportNaame.setText(favSpots.get(position));
    }

    @Override
    public int getItemCount() {
        return favSpots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        TextView favSportNaame;

        public ViewHolder(View rootView){
            super(rootView);
            this.rootView = rootView;
        }
    }
}
