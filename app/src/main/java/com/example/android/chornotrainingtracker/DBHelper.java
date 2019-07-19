package com.example.android.chornotrainingtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static String dp_name = "sport traker";
    static int dp_version = 1;

    String sp1 = "Running";
    String sp2 = "Cycling";
    String sp3 = "Freestyle swimming";
    String sp4 = "Butterfly stroke";
    String sp5 = "Breaststroke";
    String sp6 = "Backstroke";

    DBHelper(Context context) {
        super(context, dp_name, null, dp_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table athlete" +
                "(athleteID integer primary key autoincrement, firstName text, surname text, profilePic text)");
        sqLiteDatabase.execSQL("create table sports" +
                "(sportID integer primary key ,name text)");
        sqLiteDatabase.execSQL("create table favSports" +
                "(sportID integer , athleteID integer, " +
                "foreign key (sportID) references sports(sportID), foreign key (athleteID) references athlete(athleteID))");
        sqLiteDatabase.execSQL("create table sportSession" +
                "(startTime text, endTime text, date text, speed real, distance real, sportType text, athleteID integer," +
                "foreign key (athleteID) references athleteID)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table athlete");
        sqLiteDatabase.execSQL("drop table sports");
        sqLiteDatabase.execSQL("drop table athleteID");
        sqLiteDatabase.execSQL("drop table sportSession");

        sqLiteDatabase.execSQL("create table athlete(id integer primary key autoincrement, firstName text, surname text, profilePic text)");
        sqLiteDatabase.execSQL("create table sports(id integer primary key autoincrement ,name text)");
        sqLiteDatabase.execSQL("create table athleteID (sportID integer , athleteID integer, " +
                "foreign key (sportID) references sports, foreign key (athleteID) references athleteID)");
        sqLiteDatabase.execSQL("create table sportSession(startTime text, endTime text, date text, speed integer, distance integer, sportID integer, athleteID integer," +
                "foreign key (sportID) references sports, foreign key (athleteID) references athleteID)");
    }
}
