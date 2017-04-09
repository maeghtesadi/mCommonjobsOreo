package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.oreo.mcommonjobs.Controllers.JobProviderRatingController;
import com.oreo.mcommonjobs.Controllers.JobSeekerRatingController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * This is the class for the seeker rating a provider Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class SeekerRateActivity extends AppCompatActivity {

    PersonSession personInstance = PersonSession.getInstance();
    JobProviderRatingController jobProviderRatingController = new JobProviderRatingController();
    private RatingBar ratingBar1, ratingBar2, ratingBar3; //given abstract names for more flexibility in modifying the rating criteria
    EditText comment;
    private Button btnSubmit;
    String providerEmail;
    Context appContext;

    /**
     * onCreate method initialize the SeekerRateActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_rate);
        appContext = this.getApplicationContext();

        addListenerOnButton();
    }

    /**
     *  addListenerOnButton method retrieves ratings' data and adds them to job provider's database
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
                final String seekerComment = comment.getText().toString();
                final int seekerRating1 = Math.round(ratingBar1.getRating());
                final int seekerRating2 = Math.round(ratingBar2.getRating());
                final int seekerRating3 = Math.round(ratingBar3.getRating());
                providerEmail = getIntent().getStringExtra("EMAIL_EXTRA");

                jobProviderRatingController.registerJobProviderRating(personInstance.getEmail(), providerEmail, seekerRating1, seekerRating2, seekerRating3, seekerComment, appContext);
                Toast.makeText(SeekerRateActivity.this, "Rating Submitted", Toast.LENGTH_LONG).show();
                Intent submitRatingIntent = new Intent(getApplicationContext(), SeekerRatingMenuActivity.class);
                startActivity(submitRatingIntent);
                finish();
            }
        });
    }
}
