package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Profile;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_profiles);
        addprofilebutton = (Button) findViewById(R.id.addnewprofile);
        list = (ListView) findViewById(R.id.viewprofiles);
        profiles = jobSeekerController.getYourProfiles(personInstance.getEmail(), getApplicationContext());

        ArrayAdapter<Profile> adapter = new customAdapter();
        list.setAdapter(adapter);




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

                    TextView heading = (TextView) view.findViewById(R.id.prof);
                    heading.setBackgroundColor(Color.GREEN);
                    selectedprofile= heading.getText().toString();
                    personInstance.setCurrentprofile(selectedprofile);
                    Intent i = new Intent(getApplicationContext(), NavigationActivityForJobSeeker.class);
                    startActivity(i);


                }
            });
            return convertView;
        }





    }















}
