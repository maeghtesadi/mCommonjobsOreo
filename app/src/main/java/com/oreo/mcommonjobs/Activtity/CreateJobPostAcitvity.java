package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.oreo.mcommonjobs.R;
public class CreateJobPostAcitvity extends AppCompatActivity {

    Spinner dropdown_menu_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_post_acitvity);

        dropdown_menu_category = (Spinner)findViewById(R.id.spinner);
        String[] menu_items = new String[] { "CATEGORY 1", "CATEGORY 2", "CATEGORY 3" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, menu_items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_menu_category.setAdapter(adapter);
        //dropdown_menu_category.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

}
