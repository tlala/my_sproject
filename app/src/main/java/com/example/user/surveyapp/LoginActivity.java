package com.example.user.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_layout);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button22);

        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_birthday");
        loginButton.setReadPermissions("user_location");
        loginButton.setReadPermissions("user_friends");

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                //txt.setText("11111111111111111111");



                String accessToken = loginResult.getAccessToken().getToken();
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
