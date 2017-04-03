package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
    public List<Job> getAllJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

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
     * @param emailProvider - email of the JobProvider who posted the job
     * @param emailSeeker - email of the JobSeeker user applying to the job
     * @param yearsofExperience - the years of experience user has for a job
     * @param avalibilty - their general avalibility
     * @param expected_wage - the users expected wage
     * @param context
     */
    public void applyToJob(final String type, final String description, final String emailProvider, final String emailSeeker,final String yearsofExperience, final String avalibilty, final String expected_wage, final Context context){

        // Post params to be sent to the server
        Map<String, String> params = new HashMap<String, String>();

        params.put("description", description);
        params.put("typeofjob", type);
        params.put("emailProvider", emailProvider);
        params.put("emailSeeker", emailSeeker);
        params.put("yearsofExperience", yearsofExperience);
        params.put("availability", avalibilty);
        params.put("expected_wage", expected_wage);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.apply, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


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

        CharSequence text = "Application Sent!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }



    /**
     * This method allows a jobseeker to add a profille to their account
     *
     * @param profile - the type of job (category)
     * @param email - email of the JobSeeker
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

        //send to navigation menu after adding a profile
        Intent i = new Intent(context, NavigationActivityForJobSeeker.class);
        context.startActivity(i);
    }


    /**
     * This method gets your profiles
     * @param email - email of the JobSeeker
     * @param context
     */
    public List<Profile> getYourProfiles(final String email, final Context context ){
        final List<Profile> profiles = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProfiles, new JSONObject(params),
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
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return profiles;
    }


    /**
     * This method gets the jobs that pertain to the seeker's current profile
     * @param currentProfile - email of the JobSeeker
     * @param context
     */

    public List<Job> getProfileJobs(final String currentProfile, Context context){
        final List<Job> jobs = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("currentProfile", currentProfile);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getJobsForCurrentProfile, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonJobsarray = response.getJSONArray("jobs");

                            for (int i = 0; i < jsonJobsarray.length(); i++) {
                                JSONObject currentJob = jsonJobsarray.getJSONObject(i);

                                String des = currentJob.getString("description");
                                String typeofjob = currentJob.getString("typeofjob");
                                String email = currentJob.getString("posterEmail");
                                jobs.add(new Job(des, typeofjob, email));
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
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);
 
        return jobs;
    }





}
