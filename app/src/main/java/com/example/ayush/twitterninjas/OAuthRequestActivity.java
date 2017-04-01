package com.example.ayush.twitterninjas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OAuthRequestActivity extends AppCompatActivity {
    static OAuthClass oAuthClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_request);
      oAuthClass=new OAuthClass();

        try {
            
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                                Request request = chain.request();
                            try {
                                request = request.newBuilder()
                                        .addHeader("Authorization", oAuthClass.getAuthheader())
                                        .addHeader("Content-Type","application/json")
                                        .build();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (SignatureException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            }
                            Response e =  chain.proceed(request);
                                return e;
                            }

                    })
                .build();

            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(" https://api.twitter.com/1.1/")
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                    .build();



            ApiInterface apiInterface = retrofit.create(ApiInterface.class);


            oAuthClass=oAuthClass
                            .setConsumersecret("GqGwo5fbAGxhrHFpFwcctuVfK7vWX5PQJD8c16TdslyhNFoDzX")
                            .setTokensecret("RwZWKwcq2i4OcGiU2Eh9MNiXuNN9SYnqg57GvkkDmV33l")
                            .setOauth_consumer_key("BkgJ2OWMLen9kBiaCE6zWL1Kd")
                            .setOauth_token("1612537346-y5C72VPrTD4VnO8Yw04gsw1XdTgVQvefxascm3A")
                            .setOauth_signature_method("HMAC-SHA1")
                            .setOauth_version("1.0");

            oAuthClass=oAuthClass.setMethod("GET")
                    .setOauth_nonce("0Iaq2Q")//should be a random string everytime
                    .setOauth_timestamp("1491030263") //current epoch time
                    .setBody(null) //set to null if there is no request body
                    .setBaseurl("https://api.twitter.com/1.1/account/settings.json")//Complete API endpoint which is being hit
                    .setQuery(null);//Query Paremeters for the request-set to null if not any

            Call<SettingsUser> showsettings = apiInterface.showsettings();
            showsettings.enqueue(new Callback<SettingsUser>() {
                @Override
                public void success(Result<SettingsUser> result) {
                    retrofit2.Response response = result.response;
                   SettingsUser user= (SettingsUser) response.body();
                    Log.d("lala",user.getDiscoverable_by_email()+user.getScreen_name());
                }

                @Override
                public void failure(TwitterException exception) {
                    Log.d("lala",exception.getMessage().toString());
                    Log.d("lala",exception.toString());

                }
            });



        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
