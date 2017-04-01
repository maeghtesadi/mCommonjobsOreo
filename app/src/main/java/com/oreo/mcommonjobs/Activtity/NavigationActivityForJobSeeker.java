package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

/**
 * This is the class for the Navigation Activity belonging to a JobSeeker.
 *
 * @author jason
 * @author kimcodes
 */
public class NavigationActivityForJobSeeker extends AppCompatActivity {

    Button btnViewAllJobs, btnProfile, btnViewProfileJobs;


    /**
     * Initializes the NavigationActivity for a JobSeeker.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_jobseeker);
        btnViewAllJobs = (Button) findViewById(R.id.btn_addjob);
        btnProfile = (Button) findViewById(R.id.btn_profile);
       btnViewProfileJobs = (Button) findViewById(R.id.btn_view_jobs_for_your_profile);

       btnViewProfileJobs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), ViewJobsActivity.class);
               i.putExtra("EXTRA_JOB_BUTTON_CLICKED", "profileJobs");
               startActivity(i);
           }
       });




        btnViewAllJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewJobsActivity.class);
                i.putExtra("EXTRA_JOB_BUTTON_CLICKED", "allJobs");
                startActivity(i);
            }
        });




    btnProfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), ViewProfilesActivity.class);
            startActivity(i);
        }
    });

    }

}
