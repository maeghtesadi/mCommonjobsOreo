package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobSeeker;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.Models.Profile;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JobSeekerController class that handles all functionality related to jobs
 * for a user of type Job Seeker.
 *
 * @author Jason
 * @author Rameen
 */
public class JobSeekerController {

    /**
     * This method retrieves a list of jobs from the database.
     *
     * Makes a volley request, expects JsonObject as response and proceeds to return Jobs Arraylist
     * @param context
     * @return List<Jobs>
     * @throws JSONException
     */
    public List<Job> getallJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

        String url = "http://192.168.0.104/getjobs.php";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLPath.getJobs, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonJobsarray = response.getJSONArray("jobs");

                            for (int i = 0; i < jsonJobsarray.length(); i++) {
                                JSONObject job_current_position = jsonJobsarray.getJSONObject(i);

                                String des = job_current_position.getString("description");
                                String typeofjob = job_current_position.getString("typeofjob");
                                String email = job_current_position.getString("posterEmail");
                                 jobs.add(new Job(des, typeofjob, email));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return jobs;
    }

    /**
     * This method allows a user of type Job Seeker to apply to a job.
     *
     * @param type - the type of job (category)
     * @param description - description of the job
     * @param email_provider - email of the JobSeeker user applying to the job
     * @param c
     */
    public void applyToJob(final String type, final String description, final String email_provider, final String email_seeker, final Context c) {

        String applyUrl = "http://192.168.0.104/apply.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, applyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("description", description);
                params.put("typeofjob", type);
                params.put("email_provider", email_provider);
                params.put("email_seeker", email_seeker);

                return params;
            }
        };

        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);
    }




    /**
     * This method allows a jobseeker to add a profille to their account
     *
     * @param profile - the type of job (category)
     * @param email - email of the JobSeeker
     * @param context
     */

/*
public void addProfile(final String profile,final String email , final Context context){

    //String Url = "http://192.168.2.11/mcommonjobs/addProfile.php";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLPath.addProfile, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Intent i = new Intent(context, NavigationActivityForJobSeeker.class);
            context.startActivity(i);


        }
    }, new Response.ErrorListener() {
        public void onErrorResponse(VolleyError error) {

        }
    }
    ) {

        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();

            params.put("profile", profile);
            params.put("email", email);


            return params;
        }
    };

    RequestSingleton.getInstance(context).addToRequestQueue(stringRequest);


}*/

    /**
     * Makes a volley request which adds user into the database after selecting their profile type (JobSeeker or JobProvider)
     *
     * @param profile
     * @param email
     * @param context
     */
    public void addProfile(final String profile, final String email , final Context context){

        // Post params to be sent to the server
        Map<String, String> params = new HashMap<String, String>();

        params.put("profile", profile);
        params.put("email", email);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.addProfile, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Intent i = new Intent(context, NavigationActivityForJobSeeker.class);
                        context.startActivity(i);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        request.setShouldCache(false);
        RequestSingleton.getInstance(context).addToRequestQueue(request);

    }


    /**
     * This method gets your profiles
     * @param email - email of the JobSeeker
     * @param context
     */



    public List<Profile> getYourProfiles(final String email, final Context context ){
        final List<Profile> profiles = new ArrayList<>();

        String url = "http://192.168.0.104/getProfiles.php";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonApplicantssarray = response.getJSONArray("Profile");

                            for (int i = 0; i < jsonApplicantssarray.length(); i++) {
                                JSONObject profile_current_position = jsonApplicantssarray.getJSONObject(i);

                                String typeofprofile = profile_current_position.getString("typeofprofile");



                                profiles.add(new Profile(typeofprofile));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error", "Unable to parse json array");
            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);


                return params;
            }


        };
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return profiles;
    }


    /**
     * This method gets your jobs that pertain to your current profile
     * @param currentProfile - email of the JobSeeker
     * @param context
     */

    public List<Job> getYourProfileJobs(final String currentProfile, Context context){
        final List<Job> jobs = new ArrayList<>();

        String url = "http://192.168.0.104/getJobforCurrentProfile.php";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonJobsarray = response.getJSONArray("jobs");

                            for (int i = 0; i < jsonJobsarray.length(); i++) {
                                JSONObject job_current_position = jsonJobsarray.getJSONObject(i);

                                String des = job_current_position.getString("description");
                                String typeofjob = job_current_position.getString("typeofjob");
                                String email = job_current_position.getString("email_job_provider");
                                jobs.add(new Job(des, typeofjob, email));
                                //jobs.add(new Job(des, typeofjob));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error", "Unable to parse json array");
            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("currentProfile", currentProfile);


                return params;
            }


        };
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);
 
        return jobs;
    }





}
