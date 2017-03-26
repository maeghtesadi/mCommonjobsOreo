package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

public class UserTypeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type_selection);
        Button seekerBt = (Button) findViewById(R.id.seeker_bt);
        Button providerBt = (Button) findViewById(R.id.provider_bt);

        seekerBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileMenuActivity.class));
            }
        });

        providerBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileMenuActivity.class));
            }
        });
    }
}
