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
import com.oreo.mcommonjobs.Models.ReviewableJobProvider;
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
 * This is the class for displaying a list of the seeker's employers Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class SeekerEmployersListActivity extends AppCompatActivity {
    private List<ReviewableJobProvider> providersList = new ArrayList<>();
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * onCreate method initialize the SeekerEmployersListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_employers_list);

        providersList = getReviewableJobProviders(personInstance.getEmail(), this.getApplicationContext());
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            Intent i=new Intent(SeekerEmployersListActivity.this, SeekerRateActivity.class);
            i.putExtra("EMAIL_EXTRA", String.valueOf(providersList.get(position).getEmail()));
            startActivity(i);
        }
    };

    class customAdapter extends ArrayAdapter<ReviewableJobProvider> {

        /**
         * Constructor for customAdapter class
         * Takes fragment layout, decorates it with values taken from an employee and than returns the converted view
         */
        public customAdapter() {
            super(SeekerEmployersListActivity.this, R.layout.list_content, providersList);
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
                convertView = getLayoutInflater().inflate(R.layout.list_content, parent, false);
            }

            final ReviewableJobProvider currentProvider = providersList.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.name);

            name.setText(currentProvider.getDisplayName() + " " + currentProvider.getLastName());

            return convertView;
        }
    }

    /**
     * getReviewableJobProviders method that retrieves a list of reviewable job providers from the database
     *
     * @param seekerEmail
     * @param context
     * @return list of reviewable job providers
     */
    private List<ReviewableJobProvider> getReviewableJobProviders(final String seekerEmail, Context context){

        final List<ReviewableJobProvider> reviewableJobProviderList= new ArrayList<ReviewableJobProvider>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("seekerEmail", seekerEmail);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProvidersForSeeker, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonReviewableJobproviderArray = response.getJSONArray("providersForSeeker");

                    for(int i = 0; i < jsonReviewableJobproviderArray.length(); i++){
                        JSONObject reviewableJobProviderCurrentPosition = jsonReviewableJobproviderArray.getJSONObject(i);

                        String displayName = reviewableJobProviderCurrentPosition.getString("displayName");
                        String firstName = reviewableJobProviderCurrentPosition.getString("firstName");
                        String lastName = reviewableJobProviderCurrentPosition.getString("lastName");
                        String email = reviewableJobProviderCurrentPosition.getString("posterEmail");

                        ReviewableJobProvider reviewableJobProvider = new ReviewableJobProvider(displayName, firstName, lastName, email);

                        reviewableJobProviderList.add(reviewableJobProvider);
                    }
                    ArrayAdapter<ReviewableJobProvider> adapter = new SeekerEmployersListActivity.customAdapter();
                    ListView employersList = (ListView) (findViewById(R.id.seekeremployerslist));
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

        return reviewableJobProviderList;
    }

}