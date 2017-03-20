package com.oreo.mcommonjobs.Models;

/**
 * Created by jason on 2017-03-19.
 */

public class Application {

    String jobtype;
    String user_name;


    public Application(String jobtype, String user_name) {
        this.jobtype = jobtype;
        this.user_name = user_name;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
