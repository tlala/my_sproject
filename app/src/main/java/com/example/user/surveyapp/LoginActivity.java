package com.example.user.surveyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.AppGroupCreationContent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.utilities.PushIdGenerator;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    //Firebase mrootref;
    //Firebase messageRef;

    Firebase mreg;
    Firebase reff;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        firebaseAuth.addAuthStateListener(mAuthListener);


        //reading

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.surveyapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.surveyapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_layout);



        Firebase.setAndroidContext(this);

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // debugLoger.log("onAuthStateChanged:signed_in:" + user.getUid());
                    // debugLoger.log("name: "+user.getDisplayName());
                    // debugLoger.log("email:" + user.getEmail());
                } else {
                    // User is signed out
                    // debugLoger.log( "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        final Context c = this;

        final Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                debugLoger.log("signup by email button was pressed");
                String email = "tal3898@walla.com";
                String password = "123456";
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        debugLoger.log("yeyyyy");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        debugLoger.log(e.getMessage());
                    }
                });

            }
        });


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button22);

        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_birthday");
        loginButton.setReadPermissions("user_location");
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                debugLoger.log("1111111111111!!!!!!!!!!!");

                //---------
                class AsyncCaller extends AsyncTask<Void, Void, Void> {
                    ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                        //this method will be running on UI thread
                        pdLoading.setMessage("\tLoading...");
                        pdLoading.show();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {

                        //this method will be running on background thread so don't update UI frome here
                        //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
                        FacebookLogingManagement.LogInWithFacebook(loginResult.getAccessToken(), c);

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        super.onPostExecute(result);

                        //this method will be running on UI thread

                        pdLoading.dismiss();
                    }
                }
                //----------
                //AsyncCaller asyncCaller = new AsyncCaller();
                //asyncCaller.execute();
                //ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
                //pdLoading.setMessage("\tLoading...");
                //pdLoading.show();
                //debugLoger.log("pass loading");
                //FacebookLogingManagement.CreateNewFacebookUser("blabla",loginResult.getAccessToken());
                FacebookLogingManagement.LogInWithFacebook(loginResult.getAccessToken(),c);
                //region login with facebook acount
                /*
                String accessToken = loginResult.getAccessToken().getToken();

                AuthCredential credential = FacebookAuthProvider.getCredential(accessToken);
                firebaseAuth.signInWithCredential(credential);
                final String userId = firebaseAuth.getCurrentUser().getUid();

                // we need to check if the user is already exist


                final Firebase mref = new Firebase("https://survey-project-8d072.firebaseio.com/users");

                mref.child(userId).addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean isProfileExist = dataSnapshot.exists();
                        if(isProfileExist) {
                            AppProfile p = dataSnapshot.getValue(AppProfile.class);
                            debugLoger.log(p.name);
                        } else {
                            AppProfile p = new AppProfile();
                            p.name = "somename";
                            p.folllowing = 0;
                            p.followers = 0;
                            Firebase writingref= new Firebase("https://survey-project-8d072.firebaseio.com/");
                            writingref.child("users").child(userId).setValue(p);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        debugLoger.log(firebaseError.getMessage());
                    }
                });
*/
                //endregion
            }

            @Override
            public void onCancel() {
                // App code
                debugLoger.log("2222222222222222222 working?");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                debugLoger.log("3333333333333333333");

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
}
