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
        signInButton.setColorScheme(SignInButton.COLOR_DARK);
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
     * Method passes the sign in credentials from user to the signin method.
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
     * Method connects to the Google signin API and passes the intent.
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     *
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /**
     * Method handles passing the information retrieved from Google
     * Sets the Person Session and passes info to user controller to check
     * if the user exists.
     *
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result) {
        UserController userController = new UserController();
        PersonSession personInstance = PersonSession.getInstance();

        if (result.isSuccess()) {
            GoogleSignInAccount googleAccount = result.getSignInAccount();
            personInstance.setEmail(googleAccount.getEmail());
            personInstance.setFirstName(googleAccount.getGivenName());
            personInstance.setLastName(googleAccount.getFamilyName());

            userController.checkIfExists(personInstance.getEmail(), this.getApplicationContext());

            finish();

            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    /**
     * Method checks if the signin worked and removes the signin button from the UI.
     * Else the button remains on the page.
     * @param signedIn
     */
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }
    }

    /**
     * Method handles if the connection fails.
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // log connection failed
    }
}
