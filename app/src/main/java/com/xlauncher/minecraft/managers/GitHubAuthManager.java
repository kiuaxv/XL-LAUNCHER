package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.xlauncher.minecraft.data.models.Account;
import com.xlauncher.minecraft.network.api.GitHubApi;
import com.xlauncher.minecraft.network.api.GitHubUser;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubAuthManager {
    private static final String PREFS_NAME = "xl_github_auth";
    private static final String GITHUB_TOKEN = "github_token";
    private static final String GITHUB_USER_ID = "github_user_id";
    private static final String GITHUB_API_URL = "https://api.github.com/";

    private SharedPreferences prefs;
    private GitHubApi gitHubApi;
    private Gson gson;

    public GitHubAuthManager(Context context, NetworkManager networkManager) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        initializeGitHubApi();
    }

    private void initializeGitHubApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.gitHubApi = retrofit.create(GitHubApi.class);
    }

    public void authenticateWithToken(String token, AuthCallback callback) {
        String authHeader = "token " + token;
        Call<GitHubUser> call = gitHubApi.getUser(authHeader);

        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GitHubUser user = response.body();
                    saveToken(token);
                    saveUserId(user.id);
                    callback.onSuccess(createAccountFromGitHubUser(user, token));
                } else {
                    callback.onError("Invalid token");
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void authenticateWithUsernamePassword(String username, String password, AuthCallback callback) {
        String credentials = username + ":" + password;
        String base64Credentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        String authHeader = "Basic " + base64Credentials;

        Call<GitHubUser> call = gitHubApi.getUser(authHeader);

        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GitHubUser user = response.body();
                    // Note: In production, you should use OAuth2 for GitHub
                    callback.onSuccess(createAccountFromGitHubUser(user, base64Credentials));
                } else {
                    callback.onError("Invalid credentials");
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private Account createAccountFromGitHubUser(GitHubUser user, String token) {
        Account account = new Account(String.valueOf(user.id), user.username, "GITHUB");
        account.setEmail(user.email);
        account.setProfilePictureUrl(user.avatarUrl);
        account.setToken(token);
        return account;
    }

    private void saveToken(String token) {
        prefs.edit().putString(GITHUB_TOKEN, token).apply();
    }

    private void saveUserId(long userId) {
        prefs.edit().putLong(GITHUB_USER_ID, userId).apply();
    }

    public String getStoredToken() {
        return prefs.getString(GITHUB_TOKEN, null);
    }

    public boolean isAuthenticated() {
        return getStoredToken() != null;
    }

    public void logout() {
        prefs.edit().remove(GITHUB_TOKEN).remove(GITHUB_USER_ID).apply();
    }

    public interface AuthCallback {
        void onSuccess(Account account);
        void onError(String error);
    }
}
