package com.example.ayush.twitterninjas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    //Consumer Keys:- This you will get when you make an app on twitter dev account
    private static final String TWITTER_KEY = "qkue9Tt4G1CIIdeJro3RtPuVB";
    private static final String TWITTER_SECRET = "fRnX3drFB6VULaUsL1Vp3XdY2GhS0iRI8fKrhu5eGn1eYIhLwp";
    private TwitterLoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialising Fabric Before the actvity layout is set because the login button needs to know the SDK location
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);


        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;

                //Code that can be used if you want access to user's email , but you should make sure
                //that the app has email permissions in the developer account section

                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                       Log.d("TAG",result.data);
                        Toast.makeText(MainActivity.this, result.data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                Log.d("TAG", token);
                Log.d("TAG", secret);
                Log.d("TAG", session.getUserName());
                Log.d("TAG", String.valueOf(session.getUserId()));
                Log.d("TAG", String.valueOf(session.getId()));


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TAG", "Login with Twitter failure", exception);

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button, this is where your results are passed after the user authenticate
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
