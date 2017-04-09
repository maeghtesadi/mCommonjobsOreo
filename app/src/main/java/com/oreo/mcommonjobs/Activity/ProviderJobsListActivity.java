package com.oreo.mcommonjobs.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oreo.mcommonjobs.R;

/**
 * This is the class for displaying a list of the provider's jobs Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderJobsListActivity extends AppCompatActivity {

    /**
     * onCreate method initializes the ProviderJobsListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_jobs_list);
    }
}
