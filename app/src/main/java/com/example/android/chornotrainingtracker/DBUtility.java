package com.example.android.chornotrainingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBUtility {

    DBHelper dbHelper;
    Context context;
    DBUtility(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public long insertAthlete(AthleteModel athlete) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("firstName", athlete.getFirstName());
        contentValues.put("surname", athlete.getSurName());
        contentValues.put("profilePic", athlete.getProfilePic());

        result = db.insert("athlete", null, contentValues);

        return result;
    }

    public long insertFavSports(int sportID, int athleteID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("sportID", sportID);
        contentValues.put("athleteID", athleteID);

        result = db.insert("favSports", null, contentValues);

        return result;
    }

    public void insertSports(String sportName, int sportID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("sportID", sportID);
        contentValues.put("name", sportName);

        db.insert("sports", null, contentValues);
    }

    public ArrayList<AthleteModel> getAthletes(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<AthleteModel> athletes = new ArrayList<>();
        AthleteModel athleteModel = null;

        Cursor cursor = db.query("athlete", null, null, null, null,
                null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String fname = cursor.getString(cursor.getColumnIndex("firstName"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            String profilePic = cursor.getString(cursor.getColumnIndex("profilePic"));
            athleteModel = new AthleteModel(fname, surname, profilePic);

            athletes.add(athleteModel);

            cursor.moveToNext();
        }
        cursor.close();
        return athletes;
    }

    public int getLastID() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int athleteID = 0;
        Cursor cursor = db.query("athlete", null, null, null, null, null, null);

        cursor.moveToLast();
        athleteID = cursor.getInt(cursor.getColumnIndex("athleteID"));

        cursor.close();
        return athleteID;
    }

    public int getSportID(String sportName){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int sportID = 0;
        Cursor cursor = db.query("sports", null, "name = ?", new String[]{sportName},
                null, null, null, null);

            if(cursor.getCount() > 0 ){
                cursor.moveToFirst();
                sportID = cursor.getInt(cursor.getColumnIndex("sportID"));
            }
        cursor.close();
        return sportID;
    }

    public int getAthleteID(String athleteName){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int athleteID = 0;
        //String fname = athleteName.substring(0, athleteName.indexOf(" "));
        String lname = athleteName.substring(athleteName.indexOf(" ")+1);
        Cursor cursor = db.query("athlete", null, "surname= ?", new String[]{lname},
                null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            athleteID = cursor.getInt(cursor.getColumnIndex("athleteID"));
            cursor.moveToNext();
        }
        cursor.close();
        return athleteID;
    }

    public ArrayList<String> getFavSports(int athleteID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<String> favSports = new ArrayList<>();

        Cursor cur1 = db.query("favSports", null, "athleteID = ?", new String[]{athleteID+""},
                null, null, null, null);

        int sportID = 0;
        cur1.moveToFirst();
        while(!cur1.isAfterLast()){
            Log.d("tag1", "fav sports selected");
            sportID = cur1.getInt(cur1.getColumnIndex("sportID"));
            Cursor cur2 = db.query("sports", null, "sportID = ?", new String[]{sportID+""},
                    null, null, null);
            cur2.moveToFirst();
            while (!cur2.isAfterLast()){
                favSports.add(cur2.getString(cur2.getColumnIndex("name")));
                cur2.moveToNext();
            }
            cur2.close();
            cur1.moveToNext();
        }
        cur1.close();
        if(favSports.size() > 0){
            Log.d("tag2", "fav sports added");
        }
        return favSports;
    }

    public long insertSportSession(SportSessionModel sportSessionModel, int athleteID, int sportID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("startTime", sportSessionModel.getStartTime());
        contentValues.put("endTime", sportSessionModel.getEndTime());
        contentValues.put("date", sportSessionModel.getDate());
        contentValues.put("speed", sportSessionModel.getSpeed());
        contentValues.put("distance", sportSessionModel.getDistance());
        contentValues.put("athleteId", athleteID);
        contentValues.put("sportType", sportSessionModel.sportType);

        result = db.insert("sportSession", null, contentValues);

        return result;
    }

    public ArrayList<SportSessionModel> getSportsSessions(String date, int athleteID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<SportSessionModel> arr = new ArrayList<>();
        SportSessionModel sportSessionModel = null;

        Cursor cursor = db.query("sportSession", null, "date = ? and athleteID = ?",
                new String[]{date, athleteID+""},
                null, null, null, null);

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            sportSessionModel = new SportSessionModel(
                    cursor.getString(cursor.getColumnIndex("startTime")),
                    cursor.getString(cursor.getColumnIndex("endTime")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getFloat(cursor.getColumnIndex("speed")),
                    cursor.getFloat(cursor.getColumnIndex("distance")),
                    cursor.getString(cursor.getColumnIndex("sportType"))
            );
            arr.add(sportSessionModel);
        }
        cursor.close();
        return arr;
    }
}
