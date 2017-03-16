package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oreo.mcommonjobs.Controllers.UserController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * SignInActivity connects to Google API to allow a user to sign in.
 *
 * @author kimcodes
 */
public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient mGoogleApiClient;
    static final int RC_SIGN_IN = 45798;
    private TextView mStatusTextView;
    private static Context mContext;

    /**
     * Method initializes SignInActivity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatusTextView = (TextView) findViewById(R.id.status);
        mContext = getApplicationContext();

        initializeGoogleSignInConnection();
        setupSignInBtn();
    }

    /**
     * Method configures Google API SignIn and builds the API client
     * required to verify and retrieve credentials.
     */
    private void initializeGoogleSignInConnection(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }

    /**
     * Method finds the sign in button in the UI
     * Sets it's dimensions and sets its onClickListener
     */
    private void setupSignInBtn(){
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    /**
     * Getter method for StartActivity context.
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }


    /**
     * Method for the signin onClick 
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    /**
     *
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //Intent intent = new Intent(getApplicationContext(),TwilioAuthentication.class);
        //startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            mStatusTextView.setText(acct.getDisplayName());
            // grab information
            String user_email = acct.getEmail();
            String user_first_name = acct.getGivenName();
            String user_last_name = acct.getFamilyName();
            // Uri user_photo_url = acct.getPhotoUrl(); -- can give NULL
            Bundle bundle = new Bundle();
            // add data to bundle
            bundle.putString("user_email", user_email);
            bundle.putString("user_first_name ", user_first_name);
            bundle.putString("user_last_name", user_last_name);


            UserController userController = new UserController(); // controller initialized
            PersonSession instance = PersonSession.getInstance(); // class for handling persistent data initialized

           // setting session values
            instance.setEmail(user_email);
            instance.setFirstName(user_first_name);
            instance.setLastName(user_last_name);

            userController.checkifExsists(instance.getEmail(), this.getApplicationContext()); // passing email to controller which will handle checking the users prescence on database

            finish();

            // pass content to the next activity
            // updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);

        } else {
            mStatusTextView.setText("signed_Out");


        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
