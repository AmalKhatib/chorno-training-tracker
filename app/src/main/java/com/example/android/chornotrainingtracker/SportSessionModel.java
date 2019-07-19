package com.example.android.chornotrainingtracker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class SportSessionModel {

    private String startTime;
    private String endTime;
    private String date;
    private float speed;
    private float distance;
    String sportType;

    public SportSessionModel(String startTime, String endTime, String date, float speed, float distance, String sportType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.speed = speed;
        this.distance = distance;
        this.sportType = sportType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDistance() {
        return distance;
    }
}
