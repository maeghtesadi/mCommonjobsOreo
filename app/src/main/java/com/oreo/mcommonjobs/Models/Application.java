package com.oreo.mcommonjobs.Models;

/**
 * This class consists methods for operating on an Application object.
 *
 * @author Jason
 * @author Rameen
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
