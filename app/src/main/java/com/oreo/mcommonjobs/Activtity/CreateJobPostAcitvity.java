package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.oreo.mcommonjobs.DatabaseTasks.Addjob;
import com.oreo.mcommonjobs.R;
public class CreateJobPostAcitvity extends AppCompatActivity {

    Spinner dropdown_menu_category;


    String jobselected= "Painting Duties";
    Button b;
    TextView descption;
    Addjob addjob = new Addjob(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_post_acitvity);

        dropdown_menu_category = (Spinner)findViewById(R.id.spinner);
        String[] menu_items = new String[] { "Painting Duties", "Gardening Duties", "Vehicle Repair Duties", "Restaurant Duties","House Work Duties","Care Duties" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, menu_items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_menu_category.setAdapter(adapter);
        //dropdown_menu_category.setOnItemSelectedListener(this);

        b=(Button) findViewById(R.id.btn_continue);
        descption=(TextView) findViewById(R.id.textView);



        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String type = "addjob";
                String des = descption.getText().toString();

                // Usermapper usersssss = new user mapper

                //Users user = new User
                // userssssss.addnewjob(type,jobsecled,des)
                addjob.execute(type,jobselected,des);


            }
        });



    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0: // Painting Duties
                this.jobselected="Painting Duties";
                break;
            case 1: // Gardening Duties
                this.jobselected="Gardening Duties";
                break;
            case 2: // Vehicle Repair Duties
                this.jobselected="Vehicle Repair Duties";
                break;
            case 3: //Restaurant Duties
                this.jobselected="Restaurant Duties";
                break;
            case 4: // House Work Duties
                this.jobselected="House Work Duties";
                break;
            case 5: //Care Duties
                this.jobselected="Care Duties";
                break;

        }
    }

}
