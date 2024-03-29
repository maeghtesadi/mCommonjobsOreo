package com.oreo.mcommonjobs.Models;

/**
 * Contains the URL Path to all php scripts on the client's server (web services that query the db)
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
    public static final String getApplicants = prefix + "/getapplicants.php";
    public static final String addProfile = prefix + "/addprofile.php";
    public static final String getProfiles = prefix + "/getprofiles.php";
    public static final String getJobsForCurrentProfile = prefix + "/getjobsforcurrentprofile.php";

    public static final String addProviderRating = prefix + "/addProviderRating.php";
    public static final String addSeekerRating = prefix + "/addSeekerRating.php";
    public static final String getProviderRatings = prefix + "/getProviderRatings.php";
    public static final String getSeekerRatings = prefix + "/getSeekerRatings.php";
    public static final String getProvidersForSeeker = prefix + "/getProvidersForSeeker.php";
    public static final String getHiredSeekersForJobProvider = prefix + "/getHiredSeekersForJobProvider.php";

    public static final String acceptApplicant = prefix + "/acceptapplicant.php";
    public static final String getApplications = prefix + "/getapplicationstatuses.php";
    public static final String checkIfAcceptedApplication = prefix + "/checkifacceptedapplication.php";
    public static final String getApplicantAccount = prefix + "/getapplicantaccount.php";

    public static final String getDisplayName = prefix + "/getdisplayname.php";
    public static final String updatePhoneNumber = prefix + "/updatephonenumber.php";
    public static final String updateDisplayName = prefix + "/updatedisplayname.php";
    public static final String uploadImage = prefix + "/updateinfo.php";

    public static final String shareJob = prefix + "/sharejob.php";
    public static final String getSharedJobs = prefix + "/getsharedjobs.php";
    public static final String acceptJob = prefix + "/acceptjob.php";

}

