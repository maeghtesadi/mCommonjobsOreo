package com.oreo.mcommonjobs.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.oreo.mcommonjobs.R;

/**
 * Main Activity for the mCommonJobs application
 * Launches startActivity to begin application.
 *
 * @author Jason
 * @author Rameen
 * @author kimcodes
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method initializes main activity to launch the startActivity for a user to sign in.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SignInActivity.class));
    }

}



