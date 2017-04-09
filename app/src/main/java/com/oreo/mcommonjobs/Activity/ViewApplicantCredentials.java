package com.oreo.mcommonjobs.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Created by jason on 2017-04-02.
 */

public class ViewApplicantCredentials extends AppCompatActivity {
    PersonSession personSession = PersonSession.getInstance();
    TextView usernameTextView, exspected_wageTextView, availibilityTextView, typeofjobTextView, yearsofExperienceTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant_credentials);
        Button btnAccept = (Button) findViewById(R.id.acceptApplicant);

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


        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JobProviderController jobProviderController = new JobProviderController();
                String s = personSession.getEmail();
                String y = usernameTextView.getText().toString();
                String z = typeofjobTextView.getText().toString();

                jobProviderController.acceptApplicant(personSession.getEmail(),usernameTextView.getText().toString(),typeofjobTextView.getText().toString(), getApplicationContext());
                Intent i = new Intent(getApplicationContext(), ViewAcceptedApplicantProfileActivity.class);
                i.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(i);
                finish();
            }
        });
    }
}