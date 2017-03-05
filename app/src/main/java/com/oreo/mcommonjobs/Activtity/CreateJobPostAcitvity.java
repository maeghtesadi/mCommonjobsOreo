package com.oreo.mcommonjobs.Activtity;

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

public class CreateJobPostAcitvity extends AppCompatActivity {

    Spinner dropdown_menu_category;


    String jobselected = "Painting Duties";
    Button b;
    EditText descption;
    //Addjob addjob = new Addjob(this);
    JobProviderController jobProviderController = new JobProviderController();
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_post_acitvity);

        c = this.getApplicationContext();

        dropdown_menu_category = (Spinner) findViewById(R.id.spinner);
        String[] menu_items = new String[]{"Painting Duties", "Gardening Duties", "Vehicle Repair Duties", "Restaurant Duties", "House Work Duties", "Care Duties"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, menu_items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_menu_category.setAdapter(adapter);
        //dropdown_menu_category.setOnItemSelectedListener(this);
        dropdown_menu_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0: // Painting Duties
                        jobselected = "Painting Duties";
                        break;
                    case 1: // Gardening Duties
                        jobselected = "Gardening Duties";
                        break;
                    case 2: // Vehicle Repair Duties
                        jobselected = "Vehicle Repair Duties";
                        break;
                    case 3: //Restaurant Duties
                        jobselected = "Restaurant Duties";
                        break;
                    case 4: // House Work Duties
                        jobselected = "House Work Duties";
                        break;
                    case 5: //Care Duties
                        jobselected = "Care Duties";
                        break;

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        b = (Button) findViewById(R.id.btn_continue);
        descption = (EditText) findViewById(R.id.editText);


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  String type = "addjob";
                String des = descption.getText().toString();

                // Usermapper usersssss = new user mapper

                //UserController user = new User
                // userssssss.addnewjob(type,jobsecled,des)
                // addjob.execute(type,jobselected,des);
                jobProviderController.createPosting(jobselected, des, c);

            }
        });


    }


}
