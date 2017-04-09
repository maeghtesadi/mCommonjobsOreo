package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Displays a list of jobs (All Jobs, Profile-specific jobs, or Shared jobs based on the button clicked on the navigation menu)
 *
 * @author Jason Tsalikis
 * @author rameenrastan
 * @version 1.0
 * @since 2017-03-34
 */
public class ViewJobsActivity extends AppCompatActivity {

    private List<Job> listOfJobs = new ArrayList<>();
    JobSeekerController jobSeekerController = new JobSeekerController();
    String buttonClicked;
    PersonSession personInstance = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        populateJobList();
    }

    /**
     * Makes a volley request, expects JsonObject as response and proceeds to fill listOfJobs Arraylist
     *
     * @throws JSONException
     */
    private void populateJobList() {


        buttonClicked = getIntent().getStringExtra("EXTRA_JOB_BUTTON_CLICKED");

        if(buttonClicked.equals("allJobs")){
            listOfJobs = getAllJobs(this.getApplicationContext());}
        else if(buttonClicked.equals("profileJobs")){
            listOfJobs = getProfileJobs(personInstance.getCurrentprofile() ,this.getApplicationContext());
        }
        else if(buttonClicked.equals("sharedJobs")){
            listOfJobs = getSharedJobs(personInstance.getEmail() ,this.getApplicationContext());
        }


    }

    /**
     * Inner class to customize adapter handles
     */
    private class customAdapter extends ArrayAdapter<Job> {

        /**
         * Constructor for customAdapter
         * Takes fragment layout, decorates it with values taken from a job and than returns the converted view
         * @return convertView(VIEW)
         */
        public customAdapter() {
            super(ViewJobsActivity.this, R.layout.jobfragment, listOfJobs);
        }

        /**
         * Method description here
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.jobfragment, parent, false);
            }

            final Job currentJob = listOfJobs.get(position);

            TextView heading = (TextView) convertView.findViewById(R.id.heading);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);

            heading.setText(currentJob.getCategory());
            desc.setText(currentJob.getDescription());

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    TextView heading = (TextView) view.findViewById(R.id.heading);
                    TextView desc = (TextView) view.findViewById(R.id.desc);

                    String headingString = heading.getText().toString();
                    String descString = desc.getText().toString();

                    JobSession jobSession = JobSession.getInstance();
                    jobSession.setTypeOfJob(headingString);
                    jobSession.setDescription(descString);
                    jobSession.setEmail_job_provider(currentJob.getJob_provider_email());
                    jobSession.setAddress(currentJob.getLocation());
                    jobSession.setDuration(currentJob.getDuration());


                    Intent i = new Intent(ViewJobsActivity.this, JobInfoActivity.class);

                    startActivity(i);
                }
            });
            return convertView;
        }



    }


    /**
     * This method retrieves a list of jobs from the database.
     *
     * Makes a volley request, expects JsonObject as response and proceeds to return Jobs Arraylist
     * @param context
     * @return List<Jobs>
     * @throws JSONException
     */
    private List<Job> getAllJobs(Context context){
        final List<Job> jobs = new ArrayList<>();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLPath.getJobs, null,
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
                                String location = currentJob.getString("location");
                                String duration = currentJob.getString("duration");

                                jobs.add(new Job(des, typeofjob, email, location, duration));
                            }
                            ArrayAdapter<Job> adapter = new customAdapter();
                            ListView jobsList = (ListView) (findViewById(R.id.joblist));
                            jobsList.setAdapter(adapter);
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
     * This method gets the jobs that pertain to the seeker's current profile
     * @param currentProfile - job seeker's current profile
     * @param context
     */
    private List<Job> getProfileJobs(final String currentProfile, Context context){
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
                                String location = currentJob.getString("location");
                                String duration = currentJob.getString("duration");

                                jobs.add(new Job(des, typeofjob, email, location, duration));
                            }

                            ArrayAdapter<Job> adapter = new customAdapter();
                            ListView jobsList = (ListView) (findViewById(R.id.joblist));
                            jobsList.setAdapter(adapter);
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


    /**
     * This method gets the jobs that were shared to the user
     * @param email - email of the JobSeeker
     * @param context
     */
    private List<Job> getSharedJobs(final String email, Context context){
        final List<Job> jobs = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getSharedJobs, new JSONObject(params),
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
                                String location = currentJob.getString("location");
                                String duration = currentJob.getString("duration");

                                jobs.add(new Job(des, typeofjob, email, location, duration));
                            }

                            ArrayAdapter<Job> adapter = new customAdapter();
                            ListView jobsList = (ListView) (findViewById(R.id.joblist));
                            jobsList.setAdapter(adapter);
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






