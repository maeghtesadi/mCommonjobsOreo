package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Models.Application;
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
 * for a user of type JobProvider.
 *
 * @author Jason
 */
public class JobProviderController {


    /**
     * This method creates a job posting and sends it to be entered into the database.
     * Makes a volley request, sends job information for server to handle adding jobposting to database
     * @param typeofjob
     * @param descriptionofjob
     * @param c
     * @return void
     */
    public void createPosting(final String typeofjob, final String descriptionofjob, final String email, final Context c) {
        // validateinputs(params[])
        String loginLink = "http://192.168.2.11/mcommonjobs/addjob.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
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
                params.put("description", descriptionofjob);
                params.put("typeofjob", typeofjob);
                params.put("email",email);

                return params;
            }
        };

        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);
    }



    public List<Application> getApplicants(final String jobprovider_email,Context context){

        final List<Application> applicants = new ArrayList<>();


        String url = "http://192.168.2.11/mcommonjobs/getApplicants.php";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                  ;
                        try {

                            JSONArray jsonApplicantssarray = response.getJSONArray("applications");

                            for (int i = 0; i < jsonApplicantssarray.length(); i++) {
                                JSONObject applicant_current_position = jsonApplicantssarray.getJSONObject(i);

                                String displayname = applicant_current_position.getString("displayname");
                                String typeofjob = applicant_current_position.getString("typeofjob");
                                Application app= new Application(typeofjob,displayname);

                                applicants.add(app);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String z= "hello";
            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("jobprovider_email", jobprovider_email);


                return params;
            }


        };
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);







        return applicants;
    }








}
