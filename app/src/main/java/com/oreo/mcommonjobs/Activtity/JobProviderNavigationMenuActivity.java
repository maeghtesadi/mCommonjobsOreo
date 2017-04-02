package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

/**
 * This is the class for the Navigation Activity belonging to a JobProvider.
 *
 * @author jason
 * @author kimcodes
 */
public class JobProviderNavigationMenuActivity extends AppCompatActivity {

    Button btnAddJob;
    Button viewApplicants;

    /**
     * Initializes the NavigationActivity for a JobProvider.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_provider_navigation_menu_activity);
        /*
        btnAddJob = (Button) findViewById(R.id.btn_addjob);
        viewApplicants = (Button) findViewById(R.id.btn_view_applicants);

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), CreateJobPostActivity.class);
                //startActivity(i);
            }
        });


        viewApplicants.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v ){
                // Intent i = new Intent(getApplicationContext(), ViewApplicantsActivity.class);
                // startActivity(i);
            }
        });
        */


    }

}