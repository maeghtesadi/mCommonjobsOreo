package com.oreo.mcommonjobs;

import com.oreo.mcommonjobs.Models.Profile;

public class UserSession {

    private String email;
    private String firstName;
    private String lastName;
    private String userType = null;
    private Profile profile = null;
    private static UserSession session = null;

    private UserSession() {
    }

    public static synchronized UserSession getInstance() {
        if (session == null) {
            session = new UserSession();
        }
        return session;
    }

    public String getUserType(){
        return userType;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public Profile getProfile(){
        return profile;
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
