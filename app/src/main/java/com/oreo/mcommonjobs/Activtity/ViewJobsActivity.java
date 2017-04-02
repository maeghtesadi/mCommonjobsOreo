package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class/Activity Description here
 *
 * @author Jason Tsalikis
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
            listOfJobs = jobSeekerController.getAllJobs(this.getApplicationContext());}
        else{
            listOfJobs = jobSeekerController.getProfileJobs(personInstance.getCurrentprofile() ,this.getApplicationContext());
        }

        ArrayAdapter<Job> adapter = new customAdapter();
        ListView jobsList = (ListView) (findViewById(R.id.joblist));
        jobsList.setAdapter(adapter);

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

                    Intent i = new Intent(ViewJobsActivity.this, JobInfoActivity.class);
                    startActivity(i);
                }
            });
            return convertView;
        }



    }

}






