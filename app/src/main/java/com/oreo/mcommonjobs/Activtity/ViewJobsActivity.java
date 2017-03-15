package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);
        engine(); // @jason rename this method to be descriptive - pull from database? getJobsFromDatabase?
    }

    /**
     * Makes a volley request, expects JsonObject as response and proceeds to fill listOfJobs Arraylist
     *
     * @throws JSONException
     */
    private void engine() {

        String url = "http://xx.xx.x.xx/getjobs.php";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonJobsArray = response.getJSONArray("listOfJobs");

                            for (int i = 0; i < jsonJobsArray.length(); i++) {
                                JSONObject job_current_position = jsonJobsArray.getJSONObject(i);

                                String des = job_current_position.getString("description");
                                String typeofjob = job_current_position.getString("typeofjob");

                                listOfJobs.add(new Job(des, typeofjob));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestSingleton.getInstance(this).addToRequestQueue(jsonRequest);

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






