package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobProviderRatingController;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class for displaying a list of the provider's employees Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderEmployeesListActivity extends AppCompatActivity {
    public final static String ID_EXTRA = null;
    private List<ReviewableJobSeeker> applicantsList = new ArrayList<>();
    JobProviderRatingController jobProviderController = new JobProviderRatingController();
    PersonSession personInstance = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_employees_list);

        applicantsList = jobProviderController.getReviewableJobSeekers(personInstance.getEmail(), this.getApplicationContext());

        ArrayAdapter<ReviewableJobSeeker> adapter = new customAdapter();
        ListView employeeList = (ListView) (findViewById(R.id.provideremployeelist));
        employeeList.setAdapter(adapter);
        employeeList.setOnItemClickListener(onListClick);
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            Intent i=new Intent(ProviderEmployeesListActivity.this, ProviderRateActivity.class);
            i.putExtra(ID_EXTRA, String.valueOf(applicantsList.get(position).getEmail()));
            startActivity(i);
        }
    };

    class customAdapter extends ArrayAdapter<ReviewableJobSeeker> {

        public customAdapter() {
            super(ProviderEmployeesListActivity.this, R.layout.seeker_content, applicantsList);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.seeker_content, parent, false);
            }

            final ReviewableJobSeeker currentSeeker = applicantsList.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.seekerName);

            name.setText(currentSeeker.getDisplayName() + " " + currentSeeker.getLastName());

            /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {

                    TextView name = (TextView) view.findViewById(R.id.seekerName);
                    String headingString = name.getText().toString();

                    JobSession jobSession = JobSession.getInstance();
                    jobSession.setTypeOfJob(headingString);
                    jobSession.setDescription(descString);
                    jobSession.setEmail_job_provider(currentJob.getJob_provider_email());

                    String test = jobSession.getDescription();
                    Intent i = new Intent(ViewJobsActivity.this, JobInfoActivity.class);
                    startActivity(i);
                }
            });*/
            return convertView;
        }
    }

}
