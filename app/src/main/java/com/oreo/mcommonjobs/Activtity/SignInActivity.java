package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.RequestController;
import com.oreo.mcommonjobs.URLPath;
import com.oreo.mcommonjobs.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * SignInActivity connects to Google API to allow a user to sign in.
 */
public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    static final int RC_SIGN_IN = 45798;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;

    /**
     * Method initializes SignInActivity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        // Initialize Google Sign In Connection
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                UserSession session = UserSession.getInstance();
                GoogleSignInAccount googleAccount = result.getSignInAccount();
                session.setEmail(googleAccount.getEmail());
                // session.setFirstName(googleAccount.getGivenName());
                // session.setLastName(googleAccount.getFamilyName());
                userExistsRequest(session.getEmail());
            }
        }
    }

    private void userExistsRequest(final String email){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.userExists, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(!response.has("error")){
                                JSONObject result = response.getJSONObject("result");
                                if(result.get("exists").equals("true")){
                                    // Determine type of user (JobSeeker or JobProvider)
                                    userTypeRequest(email);
                                }
                                else{
                                    // New user, start account setup process
                                    startActivity(new Intent(getApplicationContext(), UserNameSetupActivity.class));
                                }
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

    private void userTypeRequest(final String email){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.userType, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(!response.has("error")){
                                JSONObject result = response.getJSONObject("result");
                                String userType = (String) result.get("type");
                                if(userType.equals("JobSeeker")){
                                    // Jump to profile selection
                                    startActivity(new Intent(getApplicationContext(), ProfileMenuActivity.class));
                                }
                                else if(userType.equals("JobProvider")){
                                    // Jump to Main Menu for Job Providers
                                    startActivity(new Intent(getApplicationContext(), JobProviderNavigationMenuActivity.class));
                                }
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


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // log connection failed
    }
}