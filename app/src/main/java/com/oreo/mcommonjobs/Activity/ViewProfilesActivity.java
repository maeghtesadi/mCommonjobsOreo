package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Profile;
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
 * Created by jason on 2017-03-27.
 */

public class ViewProfilesActivity extends AppCompatActivity {
    Button addprofilebutton;
    String selectedprofile;
    ListView list;
    private List<Profile> profiles = new ArrayList<>();
    JobSeekerController jobSeekerController = new JobSeekerController();
    PersonSession personInstance = PersonSession.getInstance();
    CheckedTextView prevChecked;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_profiles);
        addprofilebutton = (Button) findViewById(R.id.addnewprofile);

        profiles = getProfiles(personInstance.getEmail(), getApplicationContext());





    addprofilebutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //  personInstance.setCurrentprofile(selectedprofile);
            Intent i = new Intent(getApplicationContext(), AddProfileActivity.class);
            startActivity(i);
        }
    });

    }


    private class customAdapter extends ArrayAdapter<Profile> {

        public customAdapter() {
            super(ViewProfilesActivity.this, R.layout.profilefragment, profiles);
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.profilefragment, parent, false);
            }

            final Profile currentProfile = profiles.get(position);
            TextView heading = (TextView) convertView.findViewById(R.id.prof);
            heading.setText(currentProfile.getType());


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(prevChecked != null) {
                        prevChecked.setChecked(false);
                    }
                    CheckedTextView heading = (CheckedTextView) view.findViewById(R.id.prof);
                    selectedprofile= heading.getText().toString();
                    personInstance.setCurrentprofile(selectedprofile);
                    heading.setChecked(true);
                    prevChecked=heading;
                    Intent i = new Intent(getApplicationContext(), NavigationActivityForJobSeeker.class);
                    startActivity(i);


                }
            });
            return convertView;
        }






    }


    /**
     * This method gets the user's profiles
     * @param email - email of the JobSeeker
     * @param context
     */
    private List<Profile> getProfiles(final String email, final Context context ){
        final List<Profile> profiles = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProfiles, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonApplicantssarray = response.getJSONArray("Profile");

                            for (int i = 0; i < jsonApplicantssarray.length(); i++) {
                                JSONObject profile_current_position = jsonApplicantssarray.getJSONObject(i);

                                String typeofprofile = profile_current_position.getString("typeofprofile");



                                profiles.add(new Profile(typeofprofile));

                            }
                            list = (ListView) findViewById(R.id.viewprofiles);
                            ArrayAdapter<Profile> adapter = new customAdapter();
                            list.setAdapter(adapter);

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

        return profiles;
    }












}
