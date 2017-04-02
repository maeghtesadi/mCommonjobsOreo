package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;

/**
 * Created by jason on 2017-04-02.
 */

public class ViewApplicantCredentials extends AppCompatActivity {

    TextView usernameTextView, exspected_wageTextView, availibilityTextView, typeofjobTextView, yearsofExperienceTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant_credentials);

        // getting the views to display info aftewards
        usernameTextView= (TextView) findViewById(R.id.nameofapplicant);
        exspected_wageTextView= (TextView) findViewById(R.id.exepctedwage);
        availibilityTextView= (TextView) findViewById(R.id.availabilityofuser);
        typeofjobTextView= (TextView) findViewById(R.id.typeofjob);
        yearsofExperienceTextview= (TextView) findViewById(R.id.yearsofexperience);

        //setting the textviews with the data of applicants we recieved from previous activity
        usernameTextView.setText(getIntent().getStringExtra("username"));
        exspected_wageTextView.setText(getIntent().getStringExtra("expected_wage"));
        typeofjobTextView.setText(getIntent().getStringExtra("typeofjob"));
        availibilityTextView.setText(getIntent().getStringExtra("availibility"));
        yearsofExperienceTextview.setText(getIntent().getStringExtra("yearsofExperience"));





    }









}
