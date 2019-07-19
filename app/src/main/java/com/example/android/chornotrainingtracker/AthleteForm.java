package com.example.android.chornotrainingtracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.chornotrainingtracker.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AthleteForm extends AppCompatActivity {

    DBUtility dbUtility;

    ArrayList<CheckBox> sportsCheckBoxes;

    ImageView imageView_profilePic;
    EditText editText_firstName;
    EditText editText_surname;
    CheckBox checkBox_running;
    CheckBox checkBox_cycling;
    CheckBox checkBox_swimmingFreestyle;
    CheckBox checkBox_swimmingButterflyStroke;
    CheckBox checkBox_swimmingBreastStroke;
    CheckBox checkBox_swimmingBackStroke;
    Button button_ddAthlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_form);

        dbUtility = new DBUtility(this);
        sportsCheckBoxes = new ArrayList<>();

        //to find views by its ID//
        imageView_profilePic = findViewById(R.id.ImageView_profile_pic);
        editText_firstName = findViewById(R.id.EditText_first_name);
        editText_surname = findViewById(R.id.EditText_surname);

        checkBox_running = findViewById(R.id.CheckBox_running);
        checkBox_cycling = findViewById(R.id.CheckBox_cycling);
        checkBox_swimmingFreestyle = findViewById(R.id.CheckBox_swimming_freestyle);
        checkBox_swimmingButterflyStroke = findViewById(R.id.CheckBox_swimming_butterfly);
        checkBox_swimmingBreastStroke = findViewById(R.id.CheckBox_swimming_breaststroke);
        checkBox_swimmingBackStroke = findViewById(R.id.CheckBox_swimming_backstroke);

        button_ddAthlete = findViewById(R.id.Button_add_athlete);
        //_______________________//

        //to fill the sports array list//
        sportsCheckBoxes.add(checkBox_running); sportsCheckBoxes.add(checkBox_cycling);
        sportsCheckBoxes.add(checkBox_swimmingFreestyle); sportsCheckBoxes.add(checkBox_swimmingButterflyStroke);
        sportsCheckBoxes.add(checkBox_swimmingBreastStroke); sportsCheckBoxes.add(checkBox_swimmingBackStroke);
        //____________________________//

        button_ddAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editText_firstName.getText().toString();
                String surname = editText_surname.getText().toString();
//                Bitmap bitmap = (Bitmap) imageView_profilePic.getDrawingCache();
//                String profilePic_encoded = encodeToBase64(bitmap);

                AthleteModel athlete = new AthleteModel(firstName, surname,
                        "profile pic");

                long athleteResult = dbUtility.insertAthlete(athlete);

                //to store the athlete’s fav sports
                long favSportsResult = 0;
                for(int i = 0; i<sportsCheckBoxes.size(); i++){
                    int sportID, athleteID;
                    if(sportsCheckBoxes.get(i).isChecked()){
                        sportID = dbUtility.getSportID(sportsCheckBoxes.get(i).getText().toString());
                        athleteID = dbUtility.getLastID();
                        favSportsResult = dbUtility.insertFavSports(sportID, athleteID);
                    }
                }
                if(athleteResult > 0 && favSportsResult > 0){
                    Intent intent = new Intent(AthleteForm.this, AthleteAccount.class);
                    intent.putExtra("athleteName", firstName+" "+surname);
                    intent.putExtra("profilePic", "profile pic");
                    AthletesList.editor.putString("athleteName", firstName+" "+surname);
                    AthletesList.editor.commit();
                    startActivity(intent);
                }else if(favSportsResult == 0){
                    Toast.makeText(AthleteForm.this, "un problema", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //to get image from DB we have to convert its string to a bitmap
    static public Bitmap decodeToBitmap(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    //to store image we have to convert it to string(base64)
    static public String encodeToBase64(Bitmap image){
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}