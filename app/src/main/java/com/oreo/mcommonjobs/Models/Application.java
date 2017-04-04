package com.oreo.mcommonjobs.Models;

/**
 * This class consists methods for operating on an Application object.
 *
 * @author Jason
 * @author Rameen
 */
public class Application {

    private    String jobtype;
    private    String user_name;
    private    String yearsofExperience;
    private    String avalibility;
    private    String expected_wage;


    public Application(String jobtype, String user_name) {
        this.jobtype = jobtype;
        this.user_name = user_name;
    }

    public Application(String jobtype, String user_name, String yearsofExperience, String avalibility, String expected_wage) {
        this.jobtype = jobtype;
        this.user_name = user_name;
        this.yearsofExperience = yearsofExperience;
        this.avalibility = avalibility;
        this.expected_wage = expected_wage;
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

    public String getYearsofExperience() {
        return yearsofExperience;
    }

    public void setYearsofExperience(String yearsofExperience) {
        this.yearsofExperience = yearsofExperience;
    }

    public String getAvalibility() {
        return avalibility;
    }

    public void setAvalibility(String avalibility) {
        this.avalibility = avalibility;
    }

    public String getExpected_wage() {
        return expected_wage;
    }

    public void setExpected_wage(String expected_wage) {
        this.expected_wage = expected_wage;
    }
}
