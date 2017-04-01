package com.oreo.mcommonjobs.Activtity;

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
 * Created by jason on 2017-03-26.
 */

public class AddProfileActivity extends AppCompatActivity {
    String selectedprofile="Painting";
    private List<Profile> profiles = new ArrayList<>();
    ListView profiledisplaylist;
    Button addNewProfile;
    JobSeekerController jobSeekerController = new JobSeekerController();

    //profile enum to store the names of profiles
    private enum ProfileEnum{
        Painting("Painting"), Gardening("Gardening"), VehicleRepair("Vehicle Repair"), Restaurant("Restaurant"), HouseWork("House Work"), Care("Care");

        private String name;

        ProfileEnum(String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;

        }
    };

    PersonSession personInstance = PersonSession.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

       addNewProfile=(Button)findViewById(R.id.addprofile);
        profiledisplaylist= (ListView) findViewById(R.id.Profiles);

        //iterate through profile enum to add the profiles to the profile list
        for(ProfileEnum profileEnum : ProfileEnum.values()){

            Profile profile = new Profile(profileEnum.getName());
            profiles.add(profile);

        }



        ArrayAdapter<Profile> adapter = new customAdapter();

        profiledisplaylist.setAdapter(adapter);


        addNewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInstance.setCurrentprofile(selectedprofile);
                String s = personInstance.getEmail();
                jobSeekerController.addProfile(selectedprofile,personInstance.getEmail() , getApplicationContext());


            }
        });



    }

    /**
     * Inner class to customize adapter handles
     */
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
                    heading.setBackgroundColor(Color.GREEN);
                    selectedprofile= heading.getText().toString();





                }
            });
            return convertView;
        }





    }
}
