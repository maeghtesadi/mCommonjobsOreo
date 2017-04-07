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
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
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
    private List<ReviewableJobSeeker> applicantsList = new ArrayList<>();
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * onCreate method initialize the ProviderEmployeesListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_employees_list);

        applicantsList = getReviewableJobSeekers(personInstance.getEmail(), this.getApplicationContext());
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            Intent i=new Intent(ProviderEmployeesListActivity.this, ProviderRateActivity.class);
            i.putExtra("EMAIL_EXTRA", String.valueOf(applicantsList.get(position).getEmail()));
            startActivity(i);
        }
    };

    class customAdapter extends ArrayAdapter<ReviewableJobSeeker> {

        /**
         * Constructor for customAdapter class
         * Takes fragment layout, decorates it with values taken from an employee and than returns the converted view
         */
        public customAdapter() {
            super(ProviderEmployeesListActivity.this, R.layout.list_content, applicantsList);
        }

        /**
         * method that customizes the View
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_content, parent, false);
            }

            final ReviewableJobSeeker currentSeeker = applicantsList.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.name);

            name.setText(currentSeeker.getDisplayName() + " " + currentSeeker.getLastName());

            return convertView;
        }
    }

    /**
     * getReviewableJobSeekers method that retrieves a list of reviewable seekers from the database
     *
     * @param providerEmail
     * @param context
     * @return
     */
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