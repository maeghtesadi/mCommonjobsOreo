package com.oreo.mcommonjobs;


public class URLPath {

    //client server address
    public static final String prefix = "http://192.175.117.182";

    //provides access to the php web service scripts
    public static final String userExists = prefix + "/user-exists.php";
    public static final String userType = prefix + "/user-type.php";
    public static final String profileCategories = prefix + "/skill-categories.php";

    public static final String login = prefix + "/login.php";
    public static final String getJobs = prefix + "/getjobs.php";
    public static final String insert = prefix + "/insert.php";
    public static final String apply = prefix + "/apply.php";
    public static final String addJob = prefix + "/addjob.php";
    public static final String getApplicants = prefix + "/getapplicants.php";
    public static final String addProfile = prefix + "/addprofile.php";
    public static final String getProfiles = prefix + "/getprofiles.php";
    public static final String getJobsForCurrentProfile = prefix + "/getjobsforcurrentprofile.php";

}

