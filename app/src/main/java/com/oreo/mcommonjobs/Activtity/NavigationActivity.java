package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

public class NavigationActivity extends AppCompatActivity {

    Button btn_addjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        btn_addjob = (Button) findViewById(R.id.btn_addjob);

        btn_addjob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateJobPostAcitvity.class);
                startActivity(i);
            }
        });

    }


}
