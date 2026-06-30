package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.xlauncher.minecraft.data.models.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountManager {
    private static final String PREFS_NAME = "xl_accounts";
    private static final String ACTIVE_ACCOUNT = "active_account";
    private static final String ACCOUNTS_LIST = "accounts_list";

    private SharedPreferences prefs;
    private Gson gson;
    private List<Account> accountsList;

    public AccountManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        this.accountsList = new ArrayList<>();
        loadAccounts();
    }

    public void addAccount(Account account) {
        accountsList.add(account);
        saveAccounts();
    }

    public void removeAccount(String accountId) {
        accountsList.removeIf(acc -> acc.getId().equals(accountId));
        saveAccounts();
    }

    public Account getActiveAccount() {
        String activeId = prefs.getString(ACTIVE_ACCOUNT, null);
        if (activeId != null) {
            for (Account account : accountsList) {
                if (account.getId().equals(activeId)) {
                    return account;
                }
            }
        }
        return accountsList.isEmpty() ? null : accountsList.get(0);
    }

    public void setActiveAccount(String accountId) {
        prefs.edit().putString(ACTIVE_ACCOUNT, accountId).apply();
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountsList);
    }

    private void saveAccounts() {
        String json = gson.toJson(accountsList);
        prefs.edit().putString(ACCOUNTS_LIST, json).apply();
    }

    private void loadAccounts() {
        String json = prefs.getString(ACCOUNTS_LIST, null);
        if (json != null) {
            Account[] accounts = gson.fromJson(json, Account[].class);
            accountsList.addAll(Arrays.asList(accounts));
        }
    }

    public void updateAccount(Account account) {
        for (int i = 0; i < accountsList.size(); i++) {
            if (accountsList.get(i).getId().equals(account.getId())) {
                accountsList.set(i, account);
                saveAccounts();
                return;
            }
        }
    }

    public boolean accountExists(String username, String type) {
        return accountsList.stream()
                .anyMatch(acc -> acc.getUsername().equals(username) && acc.getAccountType().equals(type));
    }
}
