package com.example.android.chornotrainingtracker;

public class AthleteModel {

    private String firstName;
    private String surname;
    private String profilePic;

    public AthleteModel(String firstName, String surname, String profilePic) {
        this.firstName = firstName;
        this.surname = surname;
        this.profilePic = profilePic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surname = surName;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surname;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
