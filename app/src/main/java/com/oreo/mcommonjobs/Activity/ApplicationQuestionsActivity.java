package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Created by jason on 2017-04-01.
 */

public class ApplicationQuestionsActivity extends AppCompatActivity {

    JobSeekerController jobSeekerController = new JobSeekerController();
    Button btn_submit; // submit button
    EditText expectedWageText; // editTextfield holding the users desired wage

    String [] yearsofExperienceOptions = {"1-3 years", "4-6 years" , "6+ years"}; //options for years of experience
    String [] avilabilityOptions = {"DAY", "NIGHT" , "DAY AND NIGHT"}; // options for avalibility
    String yearsofExperience, availability, expected_wage;
    RadioGroup experienceRDG, availabilityRDG;

    JobSession jobSession = JobSession.getInstance();
    PersonSession personSession = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_questions);
        btn_submit = (Button) findViewById(R.id.apply);
        expectedWageText=(EditText) findViewById(R.id.expectedwage) ;






        /**
         * When submit button is clicked on xml, the values from the two radio groups and one edittextview are recorded and sent to jobseekercontrller
         *
         */




        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expected_wage= expectedWageText.getText().toString(); // getting the expected wage input
                experienceRDG = (RadioGroup) findViewById(R.id.yearsEXP);
                availabilityRDG = (RadioGroup) findViewById(R.id.availability);

                switch (experienceRDG.getCheckedRadioButtonId()) {   // getting years of experience radio button checked
                    case R.id.yearsOption1:
                        yearsofExperience= yearsofExperienceOptions[0];
                        break;
                    case R.id.yearsOption2:
                        yearsofExperience= yearsofExperienceOptions[1];
                        break;
                    case R.id.yearsOption3:
                        yearsofExperience= yearsofExperienceOptions[2];
                        break;
                }

                switch (availabilityRDG.getCheckedRadioButtonId()) {   // getting avaliabilty radio button checked
                    case R.id.avalibityOption1:
                        availability= avilabilityOptions[0];
                        break;
                    case R.id.avalibityOption2:
                        availability= avilabilityOptions[1];
                        break;
                    case R.id.avalibityOption3:
                        availability= avilabilityOptions[2];
                        break;
                }



                jobSeekerController.applyToJob(jobSession.getTypeOfJob(),jobSession.getDescription(),jobSession.getEmail_job_provider(),personSession.getEmail(), yearsofExperience, availability, expected_wage,getApplicationContext());
              finish();
            }
        });


    }






}
