package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.JobSession;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 *  JobInfoActivity class for viewing a Job in detail.
 *
 * @author Rameen 3/18/2017.
 */
public class JobInfoActivity extends AppCompatActivity{

     JobSession jobSession = JobSession.getInstance();
     PersonSession personSession = PersonSession.getInstance();
     Context context;
     private String shareEmail;

    /**
     * Initialize the activity.
     * Grabs the job details and displays the content on the UI.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        TextView jobTitle = (TextView) findViewById(R.id.jobtitle);
        TextView description = (TextView) findViewById(R.id.jobdescription);


        Button apply = (Button) findViewById(R.id.btnApply);
        Button share = (Button) findViewById(R.id.btnShare);
        jobTitle.setText(jobSession.getTypeOfJob());
        description.setText(jobSession.getDescription());

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JobInfoActivity.this);
                builder.setTitle("Share this job to a user (enter email):");


                final EditText input = new EditText(JobInfoActivity.this);

                builder.setView(input);


                builder.setPositiveButton("SHARE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shareEmail = input.getText().toString();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog shareAlert = builder.create();

                shareAlert.show();
                Button cancelButton = shareAlert.getButton(DialogInterface.BUTTON_NEGATIVE);
                cancelButton.setTextColor(Color.BLACK);
                Button sharebutton = shareAlert.getButton(DialogInterface.BUTTON_POSITIVE);
                sharebutton.setTextColor(Color.BLACK);
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent (getApplicationContext(), ApplicationQuestionsActivity.class);
                startActivity(i);
                
            finish();
            }
        });

    }
}
