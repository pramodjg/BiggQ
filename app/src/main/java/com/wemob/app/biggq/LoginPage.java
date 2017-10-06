package com.wemob.app.biggq;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.User;
import com.wemob.app.biggq.utils.CircleButton;
import com.wemob.app.biggq.utils.ProgressGenerator;
import com.wemob.app.biggq.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

/**
 * Created by admin on 8/21/2017.
 */

public class LoginPage extends AppCompatActivity implements ProgressGenerator.OnCompleteListener,View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    ActionProcessButton btnlogin;
    TwitterLoginButton button_twitter_login;
    CircleButton btnTwitter,btnGoogle,btnFacebook;
    private String default_username="pramod.jgeorge@gmail.com";
    private String default_password="pp";
    ProgressGenerator progressGenerator;
    CallbackManager callbackManager;
    Profile profile;
    boolean twitter,fbook,google;
    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    SignInButton signInButton;
    int RC_SIGN_IN=1003;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
         /*Firebase Auth*/
        mAuth = FirebaseAuth.getInstance();
        /* Google Login */

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btnTwitter=(CircleButton)findViewById(R.id.btnTwitter);
        btnFacebook=(CircleButton)findViewById(R.id.btnFB);
        btnGoogle=(CircleButton)findViewById(R.id.btnGoogle);
        btnlogin=(ActionProcessButton)findViewById(R.id.btnlogin);
// Set the dimensions of the sign-in button.
         signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        /* Twitter Login */
        button_twitter_login=(TwitterLoginButton)findViewById(R.id.button_twitter_login);
        button_twitter_login.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
               // Log.d(TAG, "twitterLogin:success" + result);
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
              //  Log.w(TAG, "twitterLogin:failure", exception);

            }
        });
        btnTwitter.setOnClickListener(this);

        btnFacebook.setOnClickListener(this);

        btnGoogle.setOnClickListener(this);

         progressGenerator = new ProgressGenerator(this);

        btnlogin.setMode(ActionProcessButton.Mode.ENDLESS);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressGenerator.start(btnlogin);
                btnlogin.setEnabled(false);
                callLoginLink();

            }
        });

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                String accessToken = loginResult.getAccessToken()
                        .getToken();


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback()
                       {@Override
                        public void onCompleted(JSONObject object,
                            GraphResponse response) {


                            try {

                                callFacebookResponse(object);

                            } catch (Exception e) {
                                e.printStackTrace();
                                showexception(e);
                            }
                        }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields",
                        "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
                cancelled();
            }

            @Override
            public void onError(FacebookException exception) {
                showError(exception);
            }
        });

    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    private void handleTwitterSession(TwitterSession session) {
       // Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            User CurrentUser=new User();
                            CurrentUser.setId(user.getEmail());
                            CurrentUser.setProfilePic(user.getPhotoUrl().toString());
                            CurrentUser.setName(user.getDisplayName());
                            CurrentUser.setEmail(user.getEmail());
                            CurrentUser.setLogin_method(1);
                            BiggQ bigqqapp=(BiggQ)getApplication();
                            bigqqapp.setCurrentUser(CurrentUser);
                            callHomePage();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                          //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void requestEmail(TwitterSession session) {
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the email address
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

    private void showexception(Exception e) {
        Utils.createSnackBar(btnFacebook,e.getMessage(),Snackbar.LENGTH_LONG).show();
    }

    private void cancelled() {
        Utils.createSnackBar(btnFacebook,"Cancelled",Snackbar.LENGTH_LONG).show();
    }

    private void showError(FacebookException exception) {
        Utils.createSnackBar(btnFacebook,exception.getMessage(),Snackbar.LENGTH_LONG).show();
    }

    private void callFacebookResponse(JSONObject object) throws Exception {
        String id = object.getString("id");
        String profile_pic_link="http://graph.facebook.com/" + id + "/picture?type=large";
        try {
            URL profile_pic = new URL(
                    "http://graph.facebook.com/" + id + "/picture?type=large");


        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = object.getString("name");
        String email = object.getString("email");
        String gender = object.getString("gender");
        String birthday = object.getString("birthday");
        User currentuser=new User();
        currentuser.setName(name);
        currentuser.setEmail(email);
        currentuser.setId(email);
        currentuser.setProfilePic(profile_pic_link);
        currentuser.setLogin_method(1);
        BiggQ bigqqapp=(BiggQ)getApplication();
        bigqqapp.setCurrentUser(currentuser);
        callHomePage();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(fbook) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if(twitter)
        {
            button_twitter_login.onActivityResult(requestCode, resultCode, data);
        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void callHomePage() {
        Intent applogin=new Intent(this,HomePageWrapper.class);
        startActivity(applogin);
        finish();
    }
    private void handleSignInResult(GoogleSignInResult result) {
       // Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            User currentuser=new User();
            currentuser.setName(acct.getDisplayName());
            currentuser.setEmail(acct.getEmail());
            currentuser.setProfilePic(acct.getPhotoUrl().toString());
            currentuser.setId(acct.getEmail());
            navigateToHome(currentuser);
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
           // updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
          //  updateUI(false);
        }
    }

    private void navigateToHome(User currentuser) {

        BiggQ bigqqapp=(BiggQ)getApplication();
        bigqqapp.setCurrentUser(currentuser);
        callHomePage();
        finish();
    }

    @Override
    public void onComplete() {
        callHomePage();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnTwitter:
                twitter=true;
                fbook=false;
                google=false;
                button_twitter_login.performClick();
                break;
            case R.id.btnFB:
                twitter=false;
                fbook=true;
                google=false;
                profile = Profile.getCurrentProfile().getCurrentProfile();
                if (profile != null) {
                    LoginManager.getInstance().logOut();
                }

                LoginManager.getInstance().logInWithReadPermissions(
                        this,
                        Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
                );
                break;
            case R.id.btnGoogle:
                twitter=false;
                fbook=false;
                google=true;
                signInButton.performClick();
                break;
            case R.id.sign_in_button:
                signIn();
                break;

        }
    }

    private void callLoginLink() {
        AsyncHttpClient loginclient=new AsyncHttpClient();
        RequestParams loginparams=new RequestParams();
        loginparams.put("id",default_username);
        loginclient.get(ApiLinks.loginURL + "?id=" + default_username, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    processUser(responseBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    processResponse(responseBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void processUser(byte[] responseBody) throws Exception{
        JSONObject userjson=new JSONObject(new String(responseBody));
        User currentuser=new User();
        currentuser.setName(userjson.getString("name"));
        currentuser.setEmail(userjson.getString("email"));
        currentuser.setAbout(userjson.getString("about"));
        currentuser.setWalletAmount(userjson.getString("ewallet"));
        currentuser.setProfilePic(userjson.getString("routed_profile_pic"));
        currentuser.setId(userjson.getString("id"));
        currentuser.setLogin_method(0);
        BiggQ bigqqapp=(BiggQ)getApplication();
        bigqqapp.setCurrentUser(currentuser);
        callHomePage();
        finish();
    }

    private void processResponse(byte[] responseBody) throws Exception {
        Utils.createSnackBar(btnTwitter,new JSONObject(new String(responseBody)).getString("status"),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
