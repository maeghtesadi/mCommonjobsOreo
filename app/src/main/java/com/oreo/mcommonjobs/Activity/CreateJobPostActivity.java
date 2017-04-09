package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Class for the CreateJobPost Activity
 */
public class CreateJobPostActivity extends AppCompatActivity {

    Spinner dropdown_menu_category;
    String jobSelected = "Painting";
    Button btn_submit;
    EditText jobDescription, jobAddress, jobDuration;
    JobProviderController jobProviderController = new JobProviderController();
    Context appContext;
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * onCreate method initialize the CreateJobPostActivity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_post_acitvity);

        appContext = this.getApplicationContext();

        dropdown_menu_category = (Spinner) findViewById(R.id.spinner);
      final  String[] menu_items = new String[]{"Painting", "Gardening", "Vehicle Repair", "Restaurant", "House Work", "Care"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, menu_items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_menu_category.setAdapter(adapter);

        dropdown_menu_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                jobSelected = menu_items[pos];

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_submit = (Button) findViewById(R.id.btn_continue);
        jobDescription = (EditText) findViewById(R.id.editText);
        jobAddress = (EditText) findViewById(R.id.job_address);
        jobDuration = (EditText) findViewById(R.id.job_duration);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String des = jobDescription.getText().toString();
                String address = jobAddress.getText().toString();
                String duration = jobDuration.getText().toString();

                jobProviderController.createPosting(jobSelected, des, address, duration, personInstance.getEmail() ,  appContext);
                finish();
            }
        });


    }


}
