package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 *  JobInfoActivity class for viewing a Job in detail.
 *
 * @author Rameen 3/18/2017.
 */
public class JobInfoActivity extends AppCompatActivity{

     JobSession jobSession = JobSession.getInstance();
     PersonSession personSession = PersonSession.getInstance();
     Context c;
    /**
     * Initialize the activity.
     * Grabs the job details and displays the content on the UI.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        TextView jobTitle = (TextView) findViewById(R.id.jobtitle);
        TextView description = (TextView) findViewById(R.id.jobdescription);

        Button apply = (Button) findViewById(R.id.btnApply);

        jobTitle.setText(jobSession.getTypeOfJob());
        description.setText(jobSession.getDescription());

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent (getApplicationContext(), ApplicationQuestionsActivity.class);
                startActivity(i);
                // JobSeekerController jobSeekerController = new JobSeekerController();

             //   jobSeekerController.applyToJob(jobSession.getTypeOfJob(),jobSession.getDescription(),jobSession.getEmail_job_provider(),personSession.getEmail(),c);

            finish();
            }
        });

    }
}
