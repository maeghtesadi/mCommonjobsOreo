package com.oreo.mcommonjobs.Models;

/**
 * Created by Sam on 2017-04-02.
 */

public class ReviewableJobSeeker {

    String displayName;
    String firstName;
    String lastName;
    String email;

    public ReviewableJobSeeker(String displayName, String firstName, String lastName, String email) {
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
