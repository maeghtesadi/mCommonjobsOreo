package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.oreo.mcommonjobs.Models.JobProviderRating;
import com.oreo.mcommonjobs.Models.JobSeekerRating;
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

public class SeekerRatingsListActivity extends AppCompatActivity {

    private List<JobSeekerRating> seekerRatingsList = new ArrayList<>();
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * onCreate method initialize the SeekerRatingsListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_ratings_list);

        seekerRatingsList = getJobSeekerRatingsList(personInstance.getEmail(), this.getApplicationContext());
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            Intent i=new Intent(SeekerRatingsListActivity.this, SeekerViewRatingActivity.class);
            i.putExtra("ID_EXTRA", seekerRatingsList.get(position).getRaterId());
            i.putExtra("DISPNAME_EXTRA", String.valueOf(seekerRatingsList.get(position).getDisplayName()));
            i.putExtra("LASTNAME_EXTRA", String.valueOf(seekerRatingsList.get(position).getLastName()));
            i.putExtra("R1_EXTRA", seekerRatingsList.get(position).getRating1());
            i.putExtra("R2_EXTRA", seekerRatingsList.get(position).getRating2());
            i.putExtra("R3_EXTRA", seekerRatingsList.get(position).getRating3());
            i.putExtra("AVG_EXTRA",seekerRatingsList.get(position).getAverageRating());
            i.putExtra("COMMENT_EXTRA", String.valueOf(seekerRatingsList.get(position).getComment()));
            startActivity(i);
        }
    };


    class customAdapter extends ArrayAdapter<JobSeekerRating> {

        /**
         * Constructor for customAdapter class
         * Takes fragment layout, decorates it with values taken from an job seeker's given rating and than returns the converted view
         */
        public customAdapter() {
            super(SeekerRatingsListActivity.this, R.layout.rating_content, seekerRatingsList);
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

            final JobSeekerRating currentRater = seekerRatingsList.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.raterName);
            TextView avgRating = (TextView) convertView.findViewById(R.id.ratingAverage);

            name.setText(currentRater.getDisplayName() + " " + currentRater.getLastName());
            avgRating.setText(String.valueOf(currentRater.getAverageRating()));

            return convertView;
        }
    }

    /**
     * method that retrieves a list of the ratings given to a job seeker from the database
     * @param seekerEmail
     * @param context
     * @return list of job seeker ratings
     */
    private List<JobSeekerRating> getJobSeekerRatingsList(final String seekerEmail, Context context){

        final List<JobSeekerRating> jobSeekerRatingList = new ArrayList<JobSeekerRating>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("seekerEmail", seekerEmail);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getSeekerRatings, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonJobSeekerRatingsArray = response.getJSONArray("seekerRatings");

                    for(int i = 0; i < jsonJobSeekerRatingsArray.length(); i++){
                        JSONObject ratingCurrentPosition = jsonJobSeekerRatingsArray.getJSONObject(i);

                        String displayName = ratingCurrentPosition.getString("displayName");
                        String firstName = ratingCurrentPosition.getString("firstName");
                        String lastName = ratingCurrentPosition.getString("lastName");
                        String email = ratingCurrentPosition.getString("email");
                        int raterId = ratingCurrentPosition.getInt("raterid");
                        int jobSeekerId = ratingCurrentPosition.getInt("jobseekerid");
                        int rating1 = ratingCurrentPosition.getInt("rating1");
                        int rating2 = ratingCurrentPosition.getInt("rating2");
                        int rating3 = ratingCurrentPosition.getInt("rating3");
                        double averageRating = ratingCurrentPosition.getDouble("averagerating");
                        String comment = ratingCurrentPosition.getString("comment");

                        JobSeekerRating jobSeekerRating = new JobSeekerRating(displayName, firstName, lastName, email, raterId, jobSeekerId, rating1, rating2, rating3, averageRating, comment);

                        jobSeekerRatingList.add(jobSeekerRating);
                    }

                    ArrayAdapter<JobSeekerRating> adapter = new SeekerRatingsListActivity.customAdapter();
                    ListView employersList = (ListView) (findViewById(R.id.seekerRatingslist));
                    employersList.setAdapter(adapter);
                    employersList.setOnItemClickListener(onListClick);

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

        return jobSeekerRatingList;
    }
}
