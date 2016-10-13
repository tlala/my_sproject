package com.example.user.surveyapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.widget.LoginButton;

/**
 * Created by User on 30/08/2016.
 */
public class MainAppActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app_layout);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final HomeFragment homeFragment = new HomeFragment();
        final CreateSurveyFragment createSurveyFragment = new CreateSurveyFragment();
        final GlobalFragment globalFragment = new GlobalFragment();
        fragmentTransaction.add(R.id.container, homeFragment);
        fragmentTransaction.commit();

        Button homebtn = (Button)findViewById(R.id.homebtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, homeFragment);
                fragmentTransaction.commit();
            }
        });

        Button globalbtn = (Button)findViewById(R.id.globalbtn);
        globalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, globalFragment);
                fragmentTransaction.commit();
            }
        });

        Button createSurveybtn = (Button)findViewById(R.id.plusbtn);
        createSurveybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, createSurveyFragment);
                fragmentTransaction.commit();
            }
        });


        /*
        Button btn = (Button)findViewById(R.id.homebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         */

        /*
            <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="home"
            android:id="@+id/homebtn"
            android:layout_alignParentBottom="true"
            android:layout_alignParentLeft="true"
            android:layout_alignParentStart="true" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="global"
            android:id="@+id/button5"
            android:layout_alignParentBottom="true"
            android:layout_toRightOf="@+id/homebtn"
            android:layout_toEndOf="@+id/homebtn" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="+"
            android:id="@+id/button2"
            android:layout_alignParentBottom="true"
            android:layout_toRightOf="@+id/button5"
            android:layout_toEndOf="@+id/button5" />
    </RelativeLayout>
         */
    }
}
