package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Controllers.JobProviderRatingController;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        applicantsList = getReviewableJobSeekers(personInstance.getEmail(), this.getApplicationContext());

        /*
        ArrayAdapter<ReviewableJobSeeker> adapter = new customAdapter();
        ListView employeeList = (ListView) (findViewById(R.id.provideremployeelist));
        employeeList.setAdapter(adapter);
        employeeList.setOnItemClickListener(onListClick);
        */
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

    private List<ReviewableJobSeeker> getReviewableJobSeekers(final String providerEmail, Context context) {

        final List<ReviewableJobSeeker> reviewableJobSeekerList = new ArrayList<ReviewableJobSeeker>();

        Map<String, String> params = new HashMap<String, String>();
        params.put("providerEmail", providerEmail);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getHiredSeekersForJobProvider, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray reviewableJobProviderArray = response.getJSONArray("hiredSeekersForJob");

                    for (int i = 0; i < reviewableJobProviderArray.length(); i++) {
                        JSONObject seekerCurrentPosition = reviewableJobProviderArray.getJSONObject(i);

                        String displayName = seekerCurrentPosition.getString("displayName");
                        String firstName = seekerCurrentPosition.getString("firstName");
                        String lastName = seekerCurrentPosition.getString("lastName");
                        String email = seekerCurrentPosition.getString("email");

                        ReviewableJobSeeker reviewableJobSeeker = new ReviewableJobSeeker(displayName, firstName, lastName, email);

                        reviewableJobSeekerList.add(reviewableJobSeeker);
                    }
                    ArrayAdapter<ReviewableJobSeeker> adapter = new customAdapter();
                    ListView employeeList = (ListView) (findViewById(R.id.provideremployeelist));
                    employeeList.setAdapter(adapter);
                    employeeList.setOnItemClickListener(onListClick);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Unable to parse json array");
            }
        });

        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return reviewableJobSeekerList;
    }

}
