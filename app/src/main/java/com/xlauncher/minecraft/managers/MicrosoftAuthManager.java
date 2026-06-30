package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.xlauncher.minecraft.data.models.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MicrosoftAuthManager {
    private static final String PREFS_NAME = "xl_microsoft_auth";
    private static final String MICROSOFT_TOKEN = "microsoft_token";
    private static final String MICROSOFT_REFRESH = "microsoft_refresh";
    private static final String MICROSOFT_API_URL = "https://graph.microsoft.com/v1.0/";
    private static final String MICROSOFT_AUTH_URL = "https://login.microsoft.com/";
    private static final String CLIENT_ID = "YOUR_CLIENT_ID"; // TODO: Replace with actual

    private SharedPreferences prefs;
    private MicrosoftAuthApi authApi;

    public MicrosoftAuthManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        initializeMicrosoftApi();
    }

    private void initializeMicrosoftApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MICROSOFT_AUTH_URL)
                .addConverterFactory(com.google.gson.Gson.class)
                .build();
        this.authApi = retrofit.create(MicrosoftAuthApi.class);
    }

    public void authenticateWithCredentials(String email, String password, AuthCallback callback) {
        MicrosoftTokenRequest request = new MicrosoftTokenRequest();
        request.clientId = CLIENT_ID;
        request.username = email;
        request.password = password;
        request.scope = "openid profile offline_access";

        Call<MicrosoftTokenResponse> call = authApi.getToken(request);
        call.enqueue(new Callback<MicrosoftTokenResponse>() {
            @Override
            public void onResponse(Call<MicrosoftTokenResponse> call, Response<MicrosoftTokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MicrosoftTokenResponse tokenResponse = response.body();
                    saveTokens(tokenResponse);
                    
                    // Get user profile
                    fetchUserProfile("Bearer " + tokenResponse.accessToken, callback);
                } else {
                    callback.onError("Authentication failed");
                }
            }

            @Override
            public void onFailure(Call<MicrosoftTokenResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private void fetchUserProfile(String authHeader, AuthCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MICROSOFT_API_URL)
                .addConverterFactory(com.google.gson.Gson.class)
                .build();
        MicrosoftAuthApi graphApi = retrofit.create(MicrosoftAuthApi.class);

        Call<MicrosoftUserProfile> call = graphApi.getUserProfile(authHeader);
        call.enqueue(new Callback<MicrosoftUserProfile>() {
            @Override
            public void onResponse(Call<MicrosoftUserProfile> call, Response<MicrosoftUserProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MicrosoftUserProfile profile = response.body();
                    Account account = createAccountFromProfile(profile);
                    callback.onSuccess(account);
                } else {
                    callback.onError("Failed to fetch profile");
                }
            }

            @Override
            public void onFailure(Call<MicrosoftUserProfile> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private Account createAccountFromProfile(MicrosoftUserProfile profile) {
        String displayName = profile.givenName + " " + profile.surname;
        Account account = new Account(profile.id, displayName, "MICROSOFT");
        account.setEmail(profile.email);
        return account;
    }

    private void saveTokens(MicrosoftTokenResponse response) {
        prefs.edit()
                .putString(MICROSOFT_TOKEN, response.accessToken)
                .putString(MICROSOFT_REFRESH, response.refreshToken)
                .apply();
    }

    public String getStoredToken() {
        return prefs.getString(MICROSOFT_TOKEN, null);
    }

    public boolean isAuthenticated() {
        return getStoredToken() != null;
    }

    public void logout() {
        prefs.edit().remove(MICROSOFT_TOKEN).remove(MICROSOFT_REFRESH).apply();
    }

    public interface AuthCallback {
        void onSuccess(Account account);
        void onError(String error);
    }
}
