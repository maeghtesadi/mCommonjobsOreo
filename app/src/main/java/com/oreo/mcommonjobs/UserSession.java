package com.oreo.mcommonjobs;


public class UserSession {

    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private String googleFirstName = "";
    private String googleLastName = "";
    private String userType = "";
    private String currentProfileType = "";
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

    public String getGoogleLastName() {
        return googleLastName;
    }

    public void setGoogleLastName(String googleLastName) {
        this.googleLastName = googleLastName;
    }

    public String getCurrentProfileType() {
        return currentProfileType;
    }

    public void setCurrentProfileType(String currentProfileType) {
        this.currentProfileType = currentProfileType;
    }

    public String getGoogleFirstName() {
        return googleFirstName;
    }

    public void setGoogleFirstName(String googleFirstName) {
        this.googleFirstName = googleFirstName;
    }
}
