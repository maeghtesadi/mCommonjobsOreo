package com.oreo.mcommonjobs.Session;

/**
 * Created by Rameen on 3/18/2017.
 */

public class JobSession {

    private static JobSession currentJob = null;
    private String typeofjob;
    private String description;

    /**
     * Default constructor & exists only to defeat instantiation.
     */
    protected JobSession() {

    }

    /**
     * Method returns the instance of person session
     *
     * @return If a job session exists - return the job session instance
     *         If a job session does not exist - create a job session and return the instance
     */
    public static JobSession getInstance() {
        if (currentJob == null) {
            currentJob = new JobSession();
        }
        return currentJob;
    }

    public String getTypeofjob() {
        return typeofjob;
    }

    public void setTypeofjob(String typeofjob) {
        this.typeofjob = typeofjob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
