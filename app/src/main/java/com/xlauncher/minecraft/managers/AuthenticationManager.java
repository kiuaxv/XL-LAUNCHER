package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.os.Build;

import com.xlauncher.minecraft.data.models.Account;
import com.xlauncher.minecraft.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private Context context;
    private AccountManager accountManager;
    private GitHubAuthManager gitHubAuthManager;
    private Account currentAccount;
    private Map<String, String> authTokens;

    public AuthenticationManager(Context context, AccountManager accountManager, GitHubAuthManager gitHubAuthManager) {
        this.context = context;
        this.accountManager = accountManager;
        this.gitHubAuthManager = gitHubAuthManager;
        this.authTokens = new HashMap<>();
        this.currentAccount = accountManager.getActiveAccount();
    }

    public boolean loginOffline(String username) {
        Account account = new Account(username, username, Constants.ACCOUNT_TYPE_OFFLINE);
        accountManager.addAccount(account);
        accountManager.setActiveAccount(account.getId());
        currentAccount = account;
        return true;
    }

    public void loginGitHub(String token, AuthCallback callback) {
        gitHubAuthManager.authenticateWithToken(token, new GitHubAuthManager.AuthCallback() {
            @Override
            public void onSuccess(Account account) {
                accountManager.addAccount(account);
                accountManager.setActiveAccount(account.getId());
                currentAccount = account;
                authTokens.put(account.getId(), token);
                callback.onSuccess(account);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(String accountId) {
        accountManager.setActiveAccount(accountId);
        currentAccount = accountManager.getActiveAccount();
    }

    public String getAuthToken(String accountId) {
        return authTokens.get(accountId);
    }

    public boolean isAuthenticated() {
        return currentAccount != null;
    }

    public void logout() {
        if (currentAccount != null) {
            authTokens.remove(currentAccount.getId());
            if (Constants.ACCOUNT_TYPE_GITHUB.equals(currentAccount.getAccountType())) {
                gitHubAuthManager.logout();
            }
            currentAccount = null;
        }
    }

    public String getDeviceInfo() {
        return "Android " + Build.VERSION.RELEASE + " (" + Build.MODEL + ")";
    }

    public interface AuthCallback {
        void onSuccess(Account account);
        void onError(String error);
    }
}
