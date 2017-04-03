package com.oreo.mcommonjobs.Activtity;

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
import com.oreo.mcommonjobs.Models.ApplicationStatus;
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
 * Class/Activity Description here
 *
 * @author Jason Tsalikis
 * @version 1.0
 * @since 2017-03-34
 */
public class ViewPendingApplicationsActivity extends AppCompatActivity {

    private List<ApplicationStatus> listOfApplications = new ArrayList<>();
    JobSeekerController jobSeekerController = new JobSeekerController();
    String buttonClicked;
    PersonSession personInstance = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_applications);

       listOfApplications = getApplications(personInstance.getEmail(),getApplicationContext());
    }



    /**
     * Inner class to customize adapter handles
     */
    private class customAdapter extends ArrayAdapter<ApplicationStatus> {

        /**
         * Constructor for customAdapter
         * Takes fragment layout, decorates it with values taken from a job and than returns the converted view
         * @return convertView(VIEW)
         */
        public customAdapter() {
            super(ViewPendingApplicationsActivity.this, R.layout.jobfragment, listOfApplications);
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
                convertView = getLayoutInflater().inflate(R.layout.pendingapplicationfragment, parent, false);
            }

            final ApplicationStatus currentApplication = listOfApplications.get(position);

            TextView typeOfJobView = (TextView) convertView.findViewById(R.id.textView_jobAppliedFor);
            TextView statusView = (TextView) convertView.findViewById(R.id.textView_currentStatusOfApplication);

            typeOfJobView.setText(currentApplication.getTypeOfJob());
            statusView.setText(currentApplication.getStatus());
/*
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


                    Intent i = new Intent(ViewPendingApplicationsActivity.this, JobInfoActivity.class);

                    startActivity(i);
                }
            }); */
            return convertView;
        }



    }




    /**
     * This method gets the list of applications that the job seeker has applied to, with their status (Pending, Accepted, etc.)
     * @param email - email of the JobSeeker
     * @param context
     */
    private List<ApplicationStatus> getApplications(final String email, Context context){
        final List<ApplicationStatus> applications = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getApplications, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonJobsarray = response.getJSONArray("applications");

                            for (int i = 0; i < jsonJobsarray.length(); i++) {
                                JSONObject currentApplication = jsonJobsarray.getJSONObject(i);

                                String typeOfJob = currentApplication.getString("typeofjob");
                                String status = currentApplication.getString("status");
                                applications.add(new ApplicationStatus(typeOfJob, status));
                            }

                            ArrayAdapter<ApplicationStatus> adapter = new customAdapter();
                            ListView applicationsList = (ListView) (findViewById(R.id.listViewPendingApplicants));
                            applicationsList.setAdapter(adapter);
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

        return applications;
    }

}






