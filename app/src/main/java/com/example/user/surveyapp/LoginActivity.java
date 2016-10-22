package com.example.user.surveyapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    debugLoger.log("onAuthStateChanged:signed_in:" + user.getUid());
                    debugLoger.log("name: "+user.getDisplayName());
                    debugLoger.log("email:" + user.getEmail());
                } else {
                    // User is signed out
                    debugLoger.log( "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button22);

        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_birthday");
        loginButton.setReadPermissions("user_location");
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("public_profile");

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "tal3898@walla.com";
                String password = "123456";
                firebaseAuth.createUserWithEmailAndPassword(email,password);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                //txt.setText("11111111111111111111");


                debugLoger.log("1111111111111!!!!!!!!!!!");
                String accessToken = loginResult.getAccessToken().getToken();

                AuthCredential credential = FacebookAuthProvider.getCredential(accessToken);
                firebaseAuth.signInWithCredential(credential);
                       /*
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                debugLoger.log("completed sing in");

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    debugLoger.log("error signin");
                                   // Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                     //       Toast.LENGTH_SHORT).show();
                                }

                                // [START_EXCLUDE]

                                // [END_EXCLUDE]
                            }
                        });
                */



                /*
                Boolean isNewUser = SocketCommunication.signIn(loginResult.getAccessToken());

                if (isNewUser) {
                    debugLoger.log("new user");
                    Intent myIntent = new Intent(loginButton.getContext(), RegisterActivity.class);
                    startActivityForResult(myIntent, 0);
                } else {
                    debugLoger.log("member");
                    Intent intent = new Intent(loginButton.getContext(), MainAppActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // call this to finish the current activity
                }
                */



                //Profile profile = Profile.getCurrentProfile();
                //String s = loginResult.getAccessToken().getUserId()+","+profile.getLinkUri()+","+profile.getLastName()+","+profile.getName()+","+profile.toString();
                //txt.setText("12354");

                //  SocketCommunication.sendAndReciveMessage("signin!"+accessToken.getToken());

                //      debugLoger.log(dd);
            }

            @Override
            public void onCancel() {
                // App code
                debugLoger.log("2222222222222222222");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                debugLoger.log("3333333333333333333");

            }
        });
    }
}
