package com.xlauncher.minecraft.managers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface MicrosoftAuthApi {

    @POST("/oauth2/v2.0/token")
    Call<MicrosoftTokenResponse> getToken(@Body MicrosoftTokenRequest request);

    @GET("/me")
    Call<MicrosoftUserProfile> getUserProfile(@Header("Authorization") String token);
}
