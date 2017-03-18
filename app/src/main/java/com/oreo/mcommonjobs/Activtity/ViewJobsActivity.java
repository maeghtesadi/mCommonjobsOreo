package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class/Activity Description here
 *
 * @author Jason Tsalikis
 * @version 1.0
 * @since 2017-03-34
 */
public class ViewJobsActivity extends AppCompatActivity {

    private List<Job> listOfJobs = new ArrayList<>();
    JobSeekerController jobSeekerController = new JobSeekerController();
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);
        populateJobList(); // @jason rename this method to be descriptive - pull from database? getJobsFromDatabase?
    }

    /**
     * Makes a volley request, expects JsonObject as response and proceeds to fill listOfJobs Arraylist
     *
     * @throws JSONException
     */
    private void populateJobList() {

    listOfJobs = jobSeekerController.getJobs(this.getApplicationContext());

        ArrayAdapter<Job> adapter = new customAdapter();
        ListView jobsList = (ListView) (findViewById(R.id.joblist));
        jobsList.setAdapter(adapter);
    }


    /**
     * Inner class to customize adapter handles
     */

    private class customAdapter extends ArrayAdapter<Job> {

        /**
         * Constructor for customAdapter
         * Takes fragment layout, decorates it with values taken from a job and than returns the converted view
         * @return convertView(VIEW)
         */
        public customAdapter() {
            super(ViewJobsActivity.this, R.layout.jobfragment, listOfJobs);
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

            Job currentJob = listOfJobs.get(position);

            TextView heading = (TextView) convertView.findViewById(R.id.heading);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);

            heading.setText(currentJob.getCategory());
            desc.setText(currentJob.getDescription());

            return convertView;
        }
    }

}






