package com.example.android.chornotrainingtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.ArrayList;

public class CalenderFragment extends Fragment {

    String date;
    DBUtility dbUtility;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        dbUtility = new DBUtility(getContext());

        DatePicker datePicker = view.findViewById(R.id.DatePicker);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                String month_text = "";
                month++;
                switch(month){
                    case 1:
                        month_text = "Jan";
                        break;
                    case 2:
                        month_text = "Feb";
                        break;
                    case 3:
                        month_text = "Mar";
                        break;
                    case 4:
                        month_text = "Apr";
                        break;
                    case 5:
                        month_text = "May";
                        break;
                    case 6:
                        month_text = "June";
                        break;
                    case 7:
                        month_text = "July";
                        break;
                    case 8:
                        month_text = "Aug";
                        break;
                    case 9:
                        month_text = "Sept";
                        break;
                    case 10:
                        month_text = "Oct";
                        break;
                    case 11:
                        month_text = "Nov";
                        break;
                    case 12:
                        month_text = "Dec";
                        break;
                    default:
                            break;
                }
                date = day +" "+ month_text + " "+year;

                ArrayList<SportSessionModel> arr = dbUtility.getSportsSessions(date,
                        dbUtility.getAthleteID(AthletesList.sharedPreferences.getString("athleteName", "")));
                if(arr.size() > 0){
                    Intent intent = new Intent(getContext(), SportSessions.class);

                    intent.putExtra("date", date);

                    startActivity(intent);
                    //Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
