package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017-03-17.
 */

public class JobSeekerController {

    /**
     * Makes a volley request, expects JsonObject as response and proceeds to return Jobs Arraylist
     * @param context
     * @return List<Jobs>
     * @throws JSONException
     */
    public List<Job> getJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

        String url = "http://192.168.0.104/getjobs.php";
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
}
