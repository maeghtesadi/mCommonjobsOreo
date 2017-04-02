package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Created by jason on 2017-04-01.
 */

public class ApplicationQuestionsActivity extends AppCompatActivity {

    JobSeekerController jobSeekerController = new JobSeekerController();
    Button btn_submit;
    String yearsofExperience, avalibity, expected_wage;

    JobSession jobSession = JobSession.getInstance();
    PersonSession personSession = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_questions);
        btn_submit = (Button) findViewById(R.id.apply);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //jobSeekerController.applyToJob(jobSession.getTypeOfJob(),jobSession.getDescription(),jobSession.getEmail_job_provider(),personSession.getEmail(),,yearsofExperience,avalibity,expected_wage,c);

            }
        });


    }






}
