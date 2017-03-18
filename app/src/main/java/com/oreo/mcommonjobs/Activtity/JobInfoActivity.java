package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;

/**
 * Created by Rameen on 3/18/2017.
 */

public class JobInfoActivity extends AppCompatActivity{

    JobSession jobSession = JobSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        TextView jobTitle = (TextView) findViewById(R.id.jobtitle);
        TextView description = (TextView) findViewById(R.id.jobdescription);

        jobTitle.setText(jobSession.getTypeofjob());
        description.setText(jobSession.getDescription());
    }
}
