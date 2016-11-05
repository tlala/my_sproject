package com.example.user.surveyapp;

import android.app.Fragment;
import android.opengl.EGLDisplay;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 03/09/2016.
 */
public class CreateSurveyFragment extends Fragment {

    EditText ques;
    EditText ans1;
    EditText ans2;
    EditText ans3;
    EditText ans4;

    //private DatabaseReference mDatabase;
    private Firebase mRootRef;
    private Firebase messageRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_survey_fragment,container,false);
        ques = (EditText)view.findViewById(R.id.questionET);
        ans1 = (EditText)view.findViewById(R.id.ans1ET);
        ans2 = (EditText)view.findViewById(R.id.ans2ET);
        ans3 = (EditText)view.findViewById(R.id.ans3ET);
        ans4 = (EditText)view.findViewById(R.id.ans4ET);
        Button btn = (Button)view.findViewById(R.id.sendSurveybtn);

        /*
        debugLoger.log("0");
        mRootRef = new Firebase("https://survey-project-8d072.firebaseio.com/");
        debugLoger.log("1");
        messageRef = mRootRef.child("ksdf1");
        debugLoger.log("2");

       // mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String dat = dataSnapshot.getValue(String.class);
                debugLoger.log("data is : " + dat);
                //Post post = dataSnapshot.getValue(Post.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        debugLoger.log("3");
        debugLoger.log("cheking");
       messageRef.addListenerForSingleValueEvent((com.firebase.client.ValueEventListener) postListener);
        debugLoger.log("4");
*/

        // mDatabase.addListenerForSingleValueEvent(postListener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mDatabase.child("users").child("123").child("username").setValue("tal ben yosef");
                //SocketCommunication.sendNewSurvey(AccessToken.getCurrentAccessToken(),getSurvey());
            }
        });
        return view;
    }



    public List<String> getSurvey(){
        List<String> survey = new ArrayList<String>();
        survey.add(ques.getText().toString());
        survey.add(ans1.getText().toString());
        debugLoger.log(Integer.toString(ans3.getText().toString().length()));
        survey.add(ans2.getText().toString());
        if(ans3.getText().toString().length() != 0)
            survey.add(ans3.getText().toString());
        if(ans4.getText().toString().length() != 0)
            survey.add(ans4.getText().toString());
        return  survey;
    }
}
