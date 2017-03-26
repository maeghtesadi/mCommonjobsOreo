package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.RequestController;
import com.oreo.mcommonjobs.URLPath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileSkillSelectionActivity extends AppCompatActivity {
    private ListView skillsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_skill_selection);
        Button finishBt = (Button) findViewById(R.id.finish_bt);
        skillsView = (ListView) findViewById(R.id.skillListView);

        finishBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // JobSeekerMenu or JobProviderMenu
                startActivity(new Intent(getApplicationContext(), ProfileMenuActivity.class));
            }
        });

        skillsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Save skill type for list view element
                // Go to ProfileMenuActivity
            }
        });

        skillCategoriesRequest();

    }

    private void skillCategoriesRequest(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.skillCategories, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(!response.has("error")){
                                JSONArray skillCategories = response.getJSONArray("result");
                                // Populate list view
                            }
                            else{
                                // Handle error
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        request.setShouldCache(false);
        RequestController.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
