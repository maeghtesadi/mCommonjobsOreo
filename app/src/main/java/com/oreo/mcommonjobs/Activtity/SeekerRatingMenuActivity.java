package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.R;

/**
 * This is the class for the seeker's rating menu Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class SeekerRatingMenuActivity extends AppCompatActivity {

    Button button_rate, button_view_rating;

    /**
     * onCreate method initialize the SeekerRatingMenuActivity
     * Main menu for navigation of the rating feature
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_rating_menu);
        button_rate = (Button)findViewById(R.id.btn_rate_provider);
        button_view_rating = (Button)findViewById(R.id.btn_view_my_ratings_seeker);
        button_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateSeekerIntent = new Intent(getApplicationContext(), SeekerEmployersListActivity.class);
                startActivity(rateSeekerIntent);
            }
        });
        button_view_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRatingIntent = new Intent(getApplicationContext(), SeekerRatingsListActivity.class);
                startActivity(viewRatingIntent);
            }
        });
    }
}
