package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
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

import java.util.List;

/**
 * Created by jason on 2017-03-26.
 */

public class AddProfileActivity extends AppCompatActivity {
    String selectedprofile="Painting";
    private List<Profile> profiles;
    ListView profiledisplaylist;
    Button addNewProfile;
    JobSeekerController jobSeekerController;
    PersonSession personInstance = PersonSession.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

       addNewProfile=(Button)findViewById(R.id.addprofile);

       Profile d = new Profile("Painting");
        Profile e = new Profile("Gardening");
        Profile f = new Profile("Vehicle Repair");
        Profile g = new Profile("Restaurant");
        Profile h = new Profile("House Work");
        Profile i = new Profile("Care");
        profiles.add(d);
        profiles.add(e);
        profiles.add(f);
        profiles.add(g);
        profiles.add(h);
        profiles.add(i);

        //listOfJobs = jobSeekerController.getJobs(this.getApplicationContext());

        ArrayAdapter<Profile> adapter = new customAdapter();

        profiledisplaylist.setAdapter(adapter);


        addNewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             jobSeekerController.addProfile(selectedprofile,personInstance.getEmail() , getApplicationContext());
                Intent i = new Intent(getApplicationContext(), NavigationActivityForJobSeeker.class);
                startActivity(i);

            }
        });



    }


    private class customAdapter extends ArrayAdapter<Profile> {

        public customAdapter() {
            super(AddProfileActivity.this, R.layout.profilefragment, profiles);
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
                    selectedprofile= heading.toString();

                }
            });
            return convertView;
        }





    }
}
