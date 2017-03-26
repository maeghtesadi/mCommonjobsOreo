package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

public class ProfileSkillSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_skill_selection);
        Button finishBt = (Button) findViewById(R.id.finish_bt);

        finishBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // JobSeekerMenu or JobProviderMenu
                startActivity(new Intent(getApplicationContext(), ProfileMenuActivity.class));
            }
        });
    }
}
