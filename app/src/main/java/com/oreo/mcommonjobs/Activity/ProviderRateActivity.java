package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.Controllers.JobSeekerRatingController;
import com.oreo.mcommonjobs.Models.JobProviderRating;
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * This is the class for the provider rating an employee Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderRateActivity extends AppCompatActivity {
    PersonSession personInstance = PersonSession.getInstance();
    JobSeekerRatingController jobSeekerRatingController = new JobSeekerRatingController();
    private RatingBar ratingBar1, ratingBar2, ratingBar3; //given abstract names for more flexibility in modifying the rating criteria
    EditText comment;
    private Button btnSubmit;
    String seekerEmail;
    Context appContext;

    /**
     * onCreate method initialize the ProviderRateActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_rate);
        appContext = this.getApplicationContext();

        addListenerOnButton();
    }

    /**
     *  addListenerOnButton method retrieves ratings' data and adds them to seeker's database
     */
    public void addListenerOnButton() {

        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        comment = (EditText) findViewById(R.id.comment);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String providerComment = comment.getText().toString();
                final int providerRating1 = Math.round(ratingBar1.getRating());
                final int providerRating2 = Math.round(ratingBar2.getRating());
                final int providerRating3 = Math.round(ratingBar3.getRating());
                seekerEmail = getIntent().getStringExtra("EMAIL_EXTRA");

                jobSeekerRatingController.registerJobSeekerRating(personInstance.getEmail(), seekerEmail, providerRating1, providerRating2, providerRating3, providerComment, appContext);
                Toast.makeText(ProviderRateActivity.this, "Rating Submitted", Toast.LENGTH_LONG).show();
                Intent submitRatingIntent = new Intent(getApplicationContext(), ProviderRatingMenuActivity.class);
                startActivity(submitRatingIntent);
                finish();
            }
        });
    }
}

