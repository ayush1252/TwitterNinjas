package com.example.ayush.twitterninjas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class OAuthRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_request);
        OAuthClass oAuthClass= new OAuthClass();

        try {

            HashMap<String,String> body=new HashMap<>();
            body.put("status","Hello Ladies + Gentlemen, a signed OAuth request!");//parameters that go in a body of request

            String header=oAuthClass.setMethod("GET")
                            .setConsumersecret("fRnX3drFB6VULaUsL1Vp3XdY2GhS0iRI8fKrhu5eGn1eYIhLwp")
                            .setTokensecret("oUknniiE5EaSOLnWa1qmzEgMyZ2khVZl6CvFaLssJiNpK")
                            .setOauth_consumer_key("qkue9Tt4G1CIIdeJro3RtPuVB")
                            .setOauth_token("700965185549258752-l1eeY3iiQevaYbaNXUhxY8V5QxIEZ49")
                            .setOauth_signature_method("HMAC-SHA1")
                            .setOauth_version("1.0")

                            .setOauth_nonce("JC3BSd")//should be a random string everytime
                            .setOauth_timestamp("1490733418") //current epoch time
                            .setBody(null) //set to null if there is no request body
                            .setBaseurl("https://api.twitter.com/1.1/users/show.json")//Complete API endpoint which is being hit

                            .getAuthheader();

            Log.d("lala",header);

            /*Now this header string will be added as a header as {Authorization: header string} to each request
            Set the 4 keys ,request & signature method , version only once while making the oAuthObject or making the first request

            This whole header calculation can be done in the API interface of retrofit while using one oAuthClass object

            Refer to the link https://dev.twitter.com/oauth/overview/authorizing-requests for more details
            */

            Log.d("lala",oAuthClass.displaySignature());
            /*This function displays the signature that is generated internally using the oAuth header and other details

            Refer to https://dev.twitter.com/oauth/overview/creating-signatures for more details
            */

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
