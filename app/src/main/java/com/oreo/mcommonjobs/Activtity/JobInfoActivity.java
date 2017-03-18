package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;

/**
 *  JobInfoActivity class for viewing a Job in detail.
 *
 * @author Rameen 3/18/2017.
 */
public class JobInfoActivity extends AppCompatActivity{

     JobSession jobSession = JobSession.getInstance();

    /**
     * Initialize the activity.
     * Grabs the job details and displays the content on the UI.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        TextView jobTitle = (TextView) findViewById(R.id.jobtitle);
        TextView description = (TextView) findViewById(R.id.jobdescription);

        jobTitle.setText(jobSession.getTypeOfJob());
        description.setText(jobSession.getDescription());

    }
}
