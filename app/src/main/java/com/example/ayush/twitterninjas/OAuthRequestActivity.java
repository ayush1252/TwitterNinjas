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

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OAuthRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_request);

        try {
            String header=new GetHeader("https://api.twitter.com/1.1/account/settings.json","GET",null,null).header;

            ApiInterface apiInterface=ApiClient.getApiInterface();

            Call<SettingsUser> showsettings = apiInterface.showsettings(header);
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
            Log.d("lala",e.getMessage().toString());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
