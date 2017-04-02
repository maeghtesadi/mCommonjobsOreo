package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

/**
 * Class/Activity description here
 *
 * @author Jason
 * @author kimcodes
 */
public class UserTypeSelectionActivity extends AppCompatActivity {

    Button btnJobSeeker, btnJobProvider;

    /**
     * Initializes the UserTypeSelectionActivity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type_selection_activity);

        /*
        btnJobSeeker = (Button) findViewById(R.id.btn_jobseeker);
        btnJobProvider = (Button) findViewById(R.id.btn_jobprovider);

        btnJobSeeker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        btnJobProvider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        */
    }
}
