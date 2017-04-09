package com.oreo.mcommonjobs.Models;

/**
 * This class consists methods for operating on an ApplicationStatus object.
 *
 * @author Rameen
 */
public class ApplicationStatus {
    private String status;
    private String typeOfJob;
    private String description;

    public ApplicationStatus(String typeOfJob, String status, String description){

        this.status = status;
        this.typeOfJob = typeOfJob;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }
}
