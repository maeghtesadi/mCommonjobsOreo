package com.oreo.mcommonjobs.Models;

/**
 * Created by Sam on 2017-04-01.
 */

public class JobProviderRating {

    int raterId;
    int jobProviderRating;
    int rating1;
    int rating2;
    int rating3;
    int averageRating;
    String comment;

    public JobProviderRating(int raterId, int jobProviderRating, int rating1, int rating2, int rating3, int averageRating, String comment){
        this.raterId = raterId;
        this.jobProviderRating = jobProviderRating;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.averageRating = averageRating;
        this.comment = comment;
    }

    public int getRaterId() {
        return raterId;
    }

    public int getJobProviderRating() {
        return jobProviderRating;
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

    public int getAverageRating() {
        return averageRating;
    }

    public String getComment() {
        return comment;
    }
}
