package com.oreo.mcommonjobs.Session;

/**
 * JobSession class.c
 *
 * Created by Rameen on 3/18/2017.
 */
public class JobSession {
    private static JobSession currentJob = null;
    private String typeOfJob;
    private String description;
    private String email_job_provider;
    private String address;
    private String duration;

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

    /**
     * Getter method for type of Job.
     *
     * @return
     */
    public String getTypeOfJob() {
        return typeOfJob;
    }

    /**
     * Getter method for the address of a Job.
     *
     * @return - String, address of a job.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setter method for the address of a Job.
     *
     * @param address - String, address of a job.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Getter method for the duration of a Job.
     *
     * @return - String, duration of a job.
     */
    public String getDuration() {
        return duration;
    }
    /**
     * Setter method for the duration of a Job.
     *
     * @param duration - String, description of a job.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Setter method for type of Job.
     *
     * @param typeOfJob
     */
    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    /**
     * Getter method for the description of a Job.
     *
     * @return - String, description of a job.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for the description of a Job.
     *
     * @param description - String, description of a job.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail_job_provider() {
        return email_job_provider;
    }

    public void setEmail_job_provider(String email_job_provider) {
        this.email_job_provider = email_job_provider;
    }
}
