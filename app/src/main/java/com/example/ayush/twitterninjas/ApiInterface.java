package com.example.ayush.twitterninjas;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayush on 1/4/17.
 */

public interface ApiInterface {

    @GET("account/settings.json")
    Call<SettingsUser> showsettings();
}
