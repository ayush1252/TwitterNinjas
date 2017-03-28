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

            String header=oAuthClass.setMethod("POST")
                            .setConsumersecret("kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw")
                            .setTokensecret("LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE")
                            .setOauth_consumer_key("xvz1evFS4wEEPTGEFPHBog")
                            .setOauth_token("370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb")
                            .setOauth_signature_method("HMAC-SHA1")
                            .setOauth_version("1.0")
                            .setInclude_entities("true")

                            .setOauth_nonce("kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg")//should be a random string everytime
                            .setOauth_timestamp("1318622958") //current epoch time
                            .setBody(body) //set to null if there is no request body
                            .setBaseurl("https://api.twitter.com/1/statuses/update.json")//Complete API endpoint which is being hit

                            .getAuthheader();

            Log.d("lala",header);
            //Now this header string will be added as a header as {Authorization: header string} to each request
            //Set the 4 keys , signature method , version and include entity only once
            //while making th oAuthObject or making the first request

            //This whole header calculation can be done in the API interface of retrofit while using one oAuthClass object

            //Refer to the link https://dev.twitter.com/oauth/overview/authorizing-requests for more details

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
