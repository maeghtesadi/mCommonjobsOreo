    package com.oreo.mcommonjobs.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

/**
 * This is the class for the Provider's rating menu Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderRatingMenuActivity extends AppCompatActivity {

    Button button_rate, button_view_rating;

    /**
     * onCreate method initialize the ProviderRatingMenuActivity
     * Main menu for navigation of the rating feature
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_rating_menu);
        button_rate = (Button)findViewById(R.id.btn_rate_seeker);
        button_view_rating = (Button)findViewById(R.id.btn_view_my_ratings_provider);
        button_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateSeekerIntent = new Intent(getApplicationContext(), ProviderEmployeesListActivity.class);
                startActivity(rateSeekerIntent);
            }
        });
        button_view_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRatingIntent = new Intent(getApplicationContext(), ProviderRatingsListActivity.class);
                startActivity(viewRatingIntent);
            }
        });
    }
}
