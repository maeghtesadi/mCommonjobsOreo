package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Models.Job;
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
    public List<Job> getJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

        String url = "http://xxx.xxx.xx/mcommonjobs/getjobs.php";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonJobsarray = response.getJSONArray("jobs");

                            for (int i = 0; i < jsonJobsarray.length(); i++) {
                                JSONObject job_current_position = jsonJobsarray.getJSONObject(i);

                                String des = job_current_position.getString("description");
                                String typeofjob = job_current_position.getString("typeofjob");

                                jobs.add(new Job(des, typeofjob));
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
     * @param email - email of the JobSeeker user applying to the job
     * @param c
     */
    public void applyToJob(final String type, final String description, final String email, final Context c) {

        String applyUrl = "http://xxx.xxx.xx.x/mcommonjobs/apply.php";

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
                params.put("email", email);

                return params;
            }
        };

        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);
    }
}
