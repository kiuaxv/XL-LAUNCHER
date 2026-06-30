package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.xlauncher.minecraft.data.models.Account;

import java.util.concurrent.CompletableFuture;

public class MicrosoftAuthManager {
    private static final String PREFS_NAME = "xl_microsoft_auth";
    private static final String MICROSOFT_TOKEN = "microsoft_token";
    private static final String MICROSOFT_REFRESH_TOKEN = "microsoft_refresh_token";
    private static final String MICROSOFT_USER_ID = "microsoft_user_id";
    private static final String XBOX_TOKEN = "xbox_token";

    private SharedPreferences prefs;

    public MicrosoftAuthManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void authenticate(String authCode, AuthCallback callback) {
        // TODO: Implement Microsoft OAuth2 flow
        // Steps:
        // 1. Exchange authCode for access token
        // 2. Get Xbox token from access token
        // 3. Get Minecraft profile
        // 4. Save tokens
        callback.onSuccess(new Account("microsoft_user", "Microsoft User", "MICROSOFT"));
    }

    public void refreshToken(AuthCallback callback) {
        String refreshToken = prefs.getString(MICROSOFT_REFRESH_TOKEN, null);
        if (refreshToken != null) {
            // TODO: Implement token refresh
            callback.onSuccess(new Account("microsoft_user", "Microsoft User", "MICROSOFT"));
        } else {
            callback.onError("No refresh token found");
        }
    }

    public void saveTokens(String accessToken, String refreshToken, String userId) {
        prefs.edit()
                .putString(MICROSOFT_TOKEN, accessToken)
                .putString(MICROSOFT_REFRESH_TOKEN, refreshToken)
                .putString(MICROSOFT_USER_ID, userId)
                .apply();
    }

    public String getAccessToken() {
        return prefs.getString(MICROSOFT_TOKEN, null);
    }

    public boolean isAuthenticated() {
        return getAccessToken() != null;
    }

    public void logout() {
        prefs.edit()
                .remove(MICROSOFT_TOKEN)
                .remove(MICROSOFT_REFRESH_TOKEN)
                .remove(MICROSOFT_USER_ID)
                .remove(XBOX_TOKEN)
                .apply();
    }

    public interface AuthCallback {
        void onSuccess(Account account);
        void onError(String error);
    }
}
