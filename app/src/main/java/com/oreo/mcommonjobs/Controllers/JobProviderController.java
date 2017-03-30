package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Models.Application;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.Session.RequestSingleton;
import com.oreo.mcommonjobs.Session.ValidateInputs;

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
    ValidateInputs validateInputs = ValidateInputs.getInstance();

    /**
     * This method creates a job posting and sends it to be entered into the database.
     * Makes a volley request, sends job information for server to handle adding jobposting to database
     * @param typeofjob
     * @param descriptionOfJob
     * @param context
     * @return void
     */
    public void createPosting(final String typeofjob, final String descriptionOfJob, final String email, final Context context) {

    if(validateInputs.ValidateCreatePosting(typeofjob,descriptionOfJob,email)) {

        // Post params to be sent to the server
        Map<String, String> params = new HashMap<String, String>();
        params.put("description", descriptionOfJob);
        params.put("typeofjob", typeofjob);
        params.put("email",email);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.addJob, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        CharSequence text = "New Job Posting Created!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();



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
    }else{

        CharSequence text = "Job Post Invalid: enter a description please!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
    }


    /**
     * This method allows a user of type JobProvider to get the list of people who applied to a job he posted
     * @param jobprovider_email - email of the Jobprovider
     * @param context
     * @return List of applications
     */
    public List<Application> getApplicants(final String jobprovider_email,Context context){

        final List<Application> applicants = new ArrayList<>();


        String url = "http://192.168.0.104/getApplicants.php";

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

                Log.e("Error", "Unable to parse json array");
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
