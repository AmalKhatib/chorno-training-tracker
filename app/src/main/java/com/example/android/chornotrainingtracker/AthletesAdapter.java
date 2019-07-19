package com.example.android.chornotrainingtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AthletesAdapter extends RecyclerView.Adapter{

    ArrayList<AthleteModel> athletes;
    ViewHolder viewHolder ;
    String athleteName;

    public AthletesAdapter(ArrayList<AthleteModel> attrs){
        athletes = attrs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_list, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        athleteName = athletes.get(position).getFirstName()+" "+athletes.get(position).getSurName();
        viewHolder.athleteName.setText(athleteName);
    }

    @Override
    public int getItemCount() {
        return athletes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View rootView ;
        ImageView profilePic;
        TextView athleteName;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            profilePic = rootView.findViewById(R.id.ImageView_profile_pic);
            athleteName = rootView.findViewById(R.id.TextView_athlete_name);

            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(rootView.getContext(), AthleteAccount.class);
            AthletesList.editor.putString("profilePic", "profile pic");
            AthletesList.editor.putString("athleteName", athleteName.getText().toString());
            AthletesList.editor.commit();

//            intent.putExtra("profilePic", "profile pic");
//            intent.putExtra("athleteName", athleteName.getText().toString());

            rootView.getContext().startActivity(intent);
        }
    }
}
