package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.Models.Application;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017-03-19.
 */

public class ViewApplicants extends AppCompatActivity {
    private List<Application> applicants = new ArrayList<>();
     JobProviderController jobProviderController = new JobProviderController();
    PersonSession personInstance = PersonSession.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants);

        populateApplicantList();

    }



 private void populateApplicantList (){

    applicants = jobProviderController.getApplicants(personInstance.getEmail(),this.getApplicationContext());
     ListView applicantslist = (ListView) (findViewById(R.id.applicantslist));
     ArrayAdapter<Application> adapter = new customAdapter();
     applicantslist.setAdapter(adapter);




 }


 private class customAdapter extends ArrayAdapter<Application>{


     /**
      * Constructor for customAdapter
      * Takes fragment layout, decorates it with values taken from a job and than returns the converted view
      * @return convertView(VIEW)
      */
     public customAdapter() {
         super(ViewApplicants.this, R.layout.applicantfragment, applicants);
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

         Application currentApplicant = applicants.get(position);

         TextView jobtitle = (TextView) convertView.findViewById(R.id.textView2);
         TextView name = (TextView) convertView.findViewById(R.id.desc);

         jobtitle.setText(currentApplicant.getJobtype());
         name.setText(currentApplicant.getUser_name());


/*

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

                 //String test = jobSession.getDescription();
              //   Intent i = new Intent(ViewJobsActivity.this, JobInfoActivity.class);
                // startActivity(i);
             }
         });

         */
         return convertView;
     }



 }

 }



