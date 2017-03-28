package com.oreo.mcommonjobs.Models;

/**
 * Created by Rameen on 3/27/2017.
 */

public class URLPath {

    //client server address
    public static final String prefix = "http://192.175.117.182";

    //provides access to the php web service scripts
    public static final String login = prefix + "/login.php";
    public static final String getJobs = prefix + "/getjobs.php";
    public static final String insert = prefix + "/insert.php";
    public static final String apply = prefix + "/apply.php";
    public static final String addJob = prefix + "/addjob.php";
    public static final String getApplicants = prefix + "/getApplicants.php";
}

