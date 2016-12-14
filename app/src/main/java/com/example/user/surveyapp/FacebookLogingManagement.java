package com.example.user.surveyapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 02/12/2016.
 */
public class FacebookLogingManagement {

    final static Firebase mref = new Firebase("https://survey-project-8d072.firebaseio.com");

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;

    public static void LogInWithFacebook(AccessToken accessToken, final Context context){
        //initialize firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //region authListener (doesnt use it)
        /*
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
                // ...
            }
        };
        firebaseAuth.addAuthStateListener(mAuthListener);
        */
        //endregion

        //sign in
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential);

        //checking if is new user. we will do it by check in the user list the userId.
        final String userId = firebaseAuth.getCurrentUser().getUid();



        mref.child("users").child(userId).addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent intent;
                boolean isProfileExist = dataSnapshot.exists();
                if(isProfileExist) {
                    //read the profile data
                    //AppProfile p = dataSnapshot.getValue(AppProfile.class);
                    //
                    // debugLoger.log(p.name);
                    debugLoger.log("going to main app activity");
                    intent = new Intent(context,MainAppActivity.class);
                    context.startActivity(intent);
                } else {
                    //go to register with facebook layout
                    intent = new Intent(context,RegisterWithFacebookActivity.class);
                    context.startActivity(intent);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                debugLoger.log(firebaseError.getMessage());
            }
        });
    }

    public static void CreateNewFacebookUser(final String username, AccessToken accessToken){

        debugLoger.log("the function start");

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        debugLoger.log("jkljkljkl : "+object.toString());
                        try {
                           // String date = object.getString("birthday");
                            AppProfile newProfile = new AppProfile();
                            newProfile.username = username;
                            newProfile.name =  object.getString("name");
                            newProfile.location =  object.getJSONObject("location").getString("name");
                            if(object.getString("gender") == "male")
                                newProfile.gender = 1;
                            else
                                newProfile.gender = 2;
                            mref.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(newProfile);
                        } catch (JSONException e) {
                           debugLoger.log(e.getMessage());
                        }
                        debugLoger.log("finished getting the fields");
                        // Insert your code here
                       // debugLoger.log("creating the profile");

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,id,birthday,gender,location,name,picture");
        request.setParameters(parameters);
        request.executeAsync();

    }
}
