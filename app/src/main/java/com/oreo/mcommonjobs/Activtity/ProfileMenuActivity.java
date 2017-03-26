package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.oreo.mcommonjobs.R;


public class ProfileMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);
        Button finishBt = (Button) findViewById(R.id.finish_bt);
        Button addProfileBt = (Button) findViewById(R.id.add_profile_bt);

        finishBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // JobSeekerMenu or JobProviderMenu
                startActivity(new Intent(getApplicationContext(), JobSeekerNavigationMenuActivity.class));
            }
        });

        addProfileBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileSkillSelectionActivity.class));
            }
        });
    }
}
