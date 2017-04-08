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
import com.oreo.mcommonjobs.Models.JobProviderRating;
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
 * This is the class for the list of a provider's given ratings Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderRatingsListActivity extends AppCompatActivity {
    private List<JobProviderRating> jobProviderRatingsList = new ArrayList<>();
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * onCreate method initialize the ProviderRatingsListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_ratings_list);

        jobProviderRatingsList = getJobProviderRatings(personInstance.getEmail(), this.getApplicationContext());
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            Intent i=new Intent(ProviderRatingsListActivity.this, ProviderViewRatingActivity.class);
            i.putExtra("ID_EXTRA", jobProviderRatingsList.get(position).getRaterId());
            i.putExtra("DISPNAME_EXTRA", String.valueOf(jobProviderRatingsList.get(position).getDisplayName()));
            i.putExtra("LASTNAME_EXTRA", String.valueOf(jobProviderRatingsList.get(position).getLastName()));
            i.putExtra("R1_EXTRA", jobProviderRatingsList.get(position).getRating1());
            i.putExtra("R2_EXTRA", jobProviderRatingsList.get(position).getRating2());
            i.putExtra("R3_EXTRA", jobProviderRatingsList.get(position).getRating3());
            i.putExtra("AVG_EXTRA",jobProviderRatingsList.get(position).getAverageRating());
            i.putExtra("COMMENT_EXTRA", String.valueOf(jobProviderRatingsList.get(position).getComment()));
            startActivity(i);
        }
    };


    class customAdapter extends ArrayAdapter<JobProviderRating> {

        /**
         * Constructor for customAdapter class
         * Takes fragment layout, decorates it with values taken from an job provider's given rating and than returns the converted view
         */
        public customAdapter() {
            super(ProviderRatingsListActivity.this, R.layout.rating_content, jobProviderRatingsList);
        }

        /**
         * method that customizes the View
         *
         * @param position
         * @param convertView
         * @param parent
         * @return view
         */
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.rating_content, parent, false);
            }

            final JobProviderRating currentRater = jobProviderRatingsList.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.raterName);
            TextView avgRating = (TextView) convertView.findViewById(R.id.ratingAverage);

            name.setText(currentRater.getDisplayName() + " " + currentRater.getLastName());
            avgRating.setText(String.valueOf(currentRater.getAverageRating()));

            return convertView;
        }
    }

    /**
     * method that retrieves a list of the ratings given to a job provider from the database
     * @param providerEmail
     * @param context
     * @return list og job provider ratings.
     */
    private List<JobProviderRating> getJobProviderRatings(final String providerEmail, Context context){

        final List<JobProviderRating> jobProviderRatingList = new ArrayList<JobProviderRating>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("providerEmail", providerEmail);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProviderRatings, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonJobProviderRatingsArray = response.getJSONArray("providerRatings");

                    for(int i = 0; i < jsonJobProviderRatingsArray.length(); i++){
                        JSONObject ratingCurrentPosition = jsonJobProviderRatingsArray.getJSONObject(i);

                        String displayName = ratingCurrentPosition.getString("displayName");
                        String firstName = ratingCurrentPosition.getString("firstName");
                        String lastName = ratingCurrentPosition.getString("lastName");
                        String email = ratingCurrentPosition.getString("email");
                        int raterId = ratingCurrentPosition.getInt("raterid");
                        int jobProviderId = ratingCurrentPosition.getInt("jobproviderid");
                        int rating1 = ratingCurrentPosition.getInt("rating1");
                        int rating2 = ratingCurrentPosition.getInt("rating2");
                        int rating3 = ratingCurrentPosition.getInt("rating3");
                        double averageRating = ratingCurrentPosition.getDouble("averagerating");
                        String comment = ratingCurrentPosition.getString("comment");

                        JobProviderRating jobProviderRating = new JobProviderRating(displayName, firstName, lastName, email, raterId, jobProviderId, rating1, rating2, rating3, averageRating, comment);

                        jobProviderRatingList.add(jobProviderRating);
                    }

                    ArrayAdapter<JobProviderRating> adapter = new ProviderRatingsListActivity.customAdapter();
                    ListView employeeList = (ListView) (findViewById(R.id.providerRatingslist));
                    employeeList.setAdapter(adapter);
                    employeeList.setOnItemClickListener(onListClick);

                }catch(JSONException e){
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

        return jobProviderRatingList;
    }

}