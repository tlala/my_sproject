package com.example.user.surveyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

//    CallbackManager callbackManager;
//    LoginButton loginButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        /*
        final EditText pass = (EditText)findViewById(R.id.pssw);
        Button btn = (Button)findViewById(R.id.passbtn);
        final String password = "538675";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString() == password){
                    Intent intent;
                    if(Profile.getCurrentProfile() != null)
                        intent = new Intent(v.getContext(), MainAppActivity.class);
                    else
                        intent = new Intent(v.getContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // call this to finish the current activity

                }
            }
        });

         */

        
                Intent intent;
        if(Profile.getCurrentProfile() != null)
            intent = new Intent(this, MainAppActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // call this to finish the current activity




        //     if(Profile.getCurrentProfile() != null){
            //someone is conencted with facebook user

   //     }



    //    callbackManager = CallbackManager.Factory.create();
     //   loginButton = (LoginButton) findViewById(R.id.login_button);



  //      loginButton.setReadPermissions("email");
        // If using in a fragment
       // loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration


//        loginButton.setReadPermissions("email");
//        loginButton.setReadPermissions("user_birthday");
//        loginButton.setReadPermissions("user_location");
//        loginButton.setReadPermissions("user_friends");


   //     String xmlString = "<data><Response><IsNewUser>True</IsNewUser></Response></data>";
/*
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse( new InputSource( new StringReader( xmlString ) ) );
            Element e =   document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("data");
            debugLoger.log(e.getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
            debugLoger.log(e.getMessage());
        }
*/
       // debugLoger.log( r.getElementAttribute("requestType","Type"));



       // loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
       //     @Override
       //     public void onSuccess(final LoginResult loginResult) {
       //         // App code
       //         //txt.setText("11111111111111111111");



//                String accessToken = loginResult.getAccessToken().getToken();
//                Boolean isNewUser = SocketCommunication.signIn(loginResult.getAccessToken());
//
//                if (isNewUser) {
//                    debugLoger.log("new user");
//                    Intent myIntent = new Intent(loginButton.getContext(), RegisterActivity.class);
//                    startActivityForResult(myIntent, 0);
//                } else {
//                    debugLoger.log("member");
//                    Intent myIntent = new Intent(loginButton.getContext(), MainAppActivity.class);
//                    startActivityForResult(myIntent, 0);
//               }//




                //Profile profile = Profile.getCurrentProfile();
                //String s = loginResult.getAccessToken().getUserId()+","+profile.getLinkUri()+","+profile.getLastName()+","+profile.getName()+","+profile.toString();
                //txt.setText("12354");

              //  SocketCommunication.sendAndReciveMessage("signin!"+accessToken.getToken());

          //      debugLoger.log(dd);
//            }

//            @Override
//            public void onCancel() {
//                // App code
//                debugLoger.log("2222222222222222222");/
//            }

 //           @Override
 //           public void onError(FacebookException exception) {
 //               // App code
 //               debugLoger.log("3333333333333333333");

//            }
//        });




    }

    /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Profile p;

        printKeyHash(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    */

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
