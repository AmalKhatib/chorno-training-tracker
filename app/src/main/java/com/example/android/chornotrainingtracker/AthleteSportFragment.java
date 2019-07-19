package com.example.android.chornotrainingtracker;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class AthleteSportFragment extends android.support.v4.app.Fragment {

    Button button;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton = null; //to store the selected one

    //radio buttons
    RadioButton radioButton_cycling;
    RadioButton radioButton_running;
    RadioButton radioButton_swimming_freestyle;
    RadioButton radioButton_swimming_butterfly;
    RadioButton radioButton_swimming_breast;
    RadioButton radioButton_swimming_back;
    ArrayList<RadioButton> radioButtons = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_athlete_sport, container, false);

        radioGroup = view.findViewById(R.id.RadioGroup);
        button = view.findViewById(R.id.Button_start_activity);

        radioButton_cycling = view.findViewById(R.id.RadioButton_cycling);
        radioButton_running = view.findViewById(R.id.RadioButton_running);
        radioButton_swimming_back = view.findViewById(R.id.RadioButton_swimming_backstroke);
        radioButton_swimming_breast = view.findViewById(R.id.RadioButton_swimming_breaststroke);
        radioButton_swimming_butterfly = view.findViewById(R.id.RadioButton_swimming_butterfly);
        radioButton_swimming_freestyle = view.findViewById(R.id.RadioButton_swimming_freestyle);

        radioButtons.add(radioButton_cycling); radioButtons.add(radioButton_running);
        radioButtons.add(radioButton_swimming_back); radioButtons.add(radioButton_swimming_breast);
        radioButtons.add(radioButton_swimming_butterfly); radioButtons.add(radioButton_swimming_freestyle);

        //instead of radioGroup
        for(int i=0 ; i<radioButtons.size(); i++){
            final RadioButton radioButton = radioButtons.get(i);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(radioButton.isChecked()){
                        selectedRadioButton = radioButton;

                        //to set all the other radioButtons to false
                        for(int j=0 ; j<radioButtons.size(); j++){
                            if(!radioButtons.get(j).getText().toString().equals(selectedRadioButton.getText().toString()))
                                radioButtons.get(j).setChecked(false);
                        }
                    }
                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFrag();
            }
        });
        return view;
    }

   public void swapFrag(){
       Intent intent = new Intent(getActivity(), Lap.class);
       intent.putExtra("sportType", selectedRadioButton.getText().toString());
       getActivity().startActivity(intent);
//       Fragment lapFragment = new Lap();
//       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//       fragmentTransaction.replace(R.id.container, lapFragment);
//       fragmentTransaction.addToBackStack(null);
//       fragmentTransaction.commit();
//
//       Bundle bundle = new Bundle();
//       bundle.putString("sportType", selectedRadioButton.getText().toString());
//       lapFragment.setArguments(bundle);
   }
}
