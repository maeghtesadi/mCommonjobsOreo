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
 * Created by jason on 2017-03-17.
 */

public class JobSeekerController {


    public List<Job> getJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

        String url = "http://192.168.2.11/mcommonjobs/getjobs.php";
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




    public void applyToJob(final String type, final String description, final String email, final Context c) {


        String applyUrl = "http://192.168.2.11/mcommonjobs/apply.php";


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
