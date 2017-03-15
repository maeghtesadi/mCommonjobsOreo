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
 * @author Jason Tsalikis
 * @version 1.0
 * @since 2017-03-34
 */


public class ViewJobsActivity extends AppCompatActivity {
    /**
     * Variables
     */
    ListView jobslist;
    private List<Job> jobs = new ArrayList<>();

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_jobs);


        engine();


    }

    /**
     * makes a volley request, expects JsonObject as response and procceeds to fill jobs arraylist
     *
     * @param void
     * @return void
     * @throws JSONException
     */
    private void engine() {

        String url = "http://192.168.0.104/getjobs.php";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jobz = response.getJSONArray("jobs");


                            for (int i = 0; i < jobz.length(); i++) {
                                JSONObject temp = jobz.getJSONObject(i);

                                String des = temp.getString("description");
                                String typeofjob = temp.getString("typeofjob");


                                jobs.add(new Job(des, typeofjob));

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

        // initializing the custom adapter
        ArrayAdapter<Job> adapter = new customAdapter();
        // retrieving list widget from activity_view_jobs
        ListView jobslist = (ListView) (findViewById(R.id.joblist));
        // binding the custom adapter to our list
        jobslist.setAdapter(adapter);


    }


    /**
     * inner class , extends arrayadapter
     * used to customize  adapter handles
     */

    private class customAdapter extends ArrayAdapter<Job> {

        /**
         * Constructor for customAdapter
         * takes fragment layout , decorates it with values taken from a job and than returns the converted view
         * @return convertView(VIEW)
         */
        public customAdapter() {
            super(ViewJobsActivity.this, R.layout.jobfragment, jobs);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.jobfragment, parent, false);
            }

            Job currentJob = jobs.get(position);

            // ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);


            heading.setText(currentJob.getCategory());
            desc.setText(currentJob.getDescription());


            return convertView;


        }
    }


}






