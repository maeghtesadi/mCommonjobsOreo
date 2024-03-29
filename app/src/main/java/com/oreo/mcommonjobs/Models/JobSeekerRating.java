package com.oreo.mcommonjobs.Models;

/**
 * Created by Sam on 2017-04-01.
 */

public class JobSeekerRating {

    String displayName;
    String firstName;
    String lastName;
    String email;
    int raterId;
    int jobSeekerId;
    int rating1;
    int rating2;
    int rating3;
    double averageRating;
    String comment;

    public JobSeekerRating(String displayName, String firstName, String lastName, String email, int raterId, int jobSeekerId, int rating1, int rating2, int rating3, double averageRating, String comment) {
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.raterId = raterId;
        this.jobSeekerId = jobSeekerId;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.averageRating = averageRating;
        this.comment = comment;
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

    public int getRaterId() {
        return raterId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public int getRating1() {
        return rating1;
    }

    public int getRating2() {
        return rating2;
    }

    public int getRating3() {
        return rating3;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getComment() {
        return comment;
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

    public void setRaterId(int raterId) {
        this.raterId = raterId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public void setRating1(int rating1) {
        this.rating1 = rating1;
    }

    public void setRating2(int rating2) {
        this.rating2 = rating2;
    }

    public void setRating3(int rating3) {
        this.rating3 = rating3;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
