package com.example.user.surveyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;

/**
 * Created by User on 30/08/2016.
 */
public class RegisterActivity extends Activity {


    public static final String forbiddenLetters = "!@#$%^&?;[]{}*(),'><=/\\ ";
    public final int minLetters = 6;

    public Boolean isContainForbiddebLetter(String username){
        for (int i = 0; i < forbiddenLetters.length(); i++) {
            char c = forbiddenLetters.charAt(i);
            if(username.indexOf(c) != -1)
                return true;
        }
        return  false;
    }

    public  Boolean isLegalUsername(String username){
        if (!isContainForbiddebLetter(username))
            if (username.length() >= minLetters)
                return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        final EditText editText = (EditText)findViewById(R.id.editText);
        final TextView txt = (TextView)findViewById(R.id.txtvalidation);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinateLayout);


        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText.getText().toString();
                if (username.length() < minLetters){
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,"This username must be at least 6 letters",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                if(isContainForbiddebLetter(username) && username.length() >= minLetters){
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,"Username cannot contain one of these letter: "+forbiddenLetters,Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                if(isLegalUsername(username)) {
                    XMLReader xmlReader = SocketCommunication.register(AccessToken.getCurrentAccessToken(), username);
                    Boolean isValidUsername = Boolean.parseBoolean(xmlReader.getElementContentByName("IsValidUsername"));
                    if (!isValidUsername) {
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "This username is already exsist. Please choose another username.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        startActivity(new Intent(RegisterActivity.this, MainAppActivity.class));
                    }
                }

            }
        });
    }
}
