package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

public class UserProfileSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_setup);

        Button continueBt = (Button) findViewById(R.id.continue_bt);

        continueBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfileSetupActivity.class));
            }
        });
    }
}
