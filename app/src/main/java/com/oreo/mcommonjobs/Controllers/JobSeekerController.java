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







}
