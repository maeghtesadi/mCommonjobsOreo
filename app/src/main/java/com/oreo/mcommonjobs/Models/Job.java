package com.oreo.mcommonjobs.Models;

/**
 * This class consists methods for operating on a Job object.
 *
 * @author Jason
 * @author kimcodes
 */
public class Job {
    int id;
    String description;
    String category;
    String status;

    enum JobStatus { AVAILABLE, EXPIRED}
    enum JobCategory {GARDENING, VEHICULE, COOKING, CARE}

    /**
     * Constructor for object Job.
     *
     * @param description String describing a job.
     * @param category
     */
    public Job(String description, String category) {
        this.description = description;
        this.category = category;
    }

    /**
     * Getter method for Id of a Job.
     *
     * @return  integer - Id of a Job
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for the Id of a Job.
     *
     * @param id integer - Id of a Job
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for the Category of a Job.
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for Job Category.
     *
     * @param category -
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter method for a Job's description
     *
     * @return String, describing the job
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for a Job description.
     *
     * @param description - String describing the job
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for the status of a Job.
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for the status of a Job.
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
