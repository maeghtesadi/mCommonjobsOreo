package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.UserSession;


public class UserTypeSelectionActivity extends AppCompatActivity {

    Button btnJobSeeker, btnJobProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type_selection_activity);

        btnJobSeeker = (Button) findViewById(R.id.btn_jobseeker);
        btnJobProvider = (Button) findViewById(R.id.btn_jobprovider);

        btnJobSeeker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserSession.getInstance().setUserType("JobSeeker");

                // Go to Job Seeker Profile Menu
                startActivity(new Intent(getApplicationContext(), JobSeekerProfileMenuActivity.class));
            }
        });

        btnJobProvider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserSession.getInstance().setUserType("JobProvider");

                // Go to Job Provider Navigation Menu
                startActivity(new Intent(getApplicationContext(), JobProviderNavigationMenuActivity.class));
            }
        });
    }
}
