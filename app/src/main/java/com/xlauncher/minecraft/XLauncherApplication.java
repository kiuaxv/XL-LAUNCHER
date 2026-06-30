package com.xlauncher.minecraft;

import android.app.Application;

import com.xlauncher.minecraft.managers.AccountManager;
import com.xlauncher.minecraft.managers.FileManager;
import com.xlauncher.minecraft.managers.GameProfileManager;
import com.xlauncher.minecraft.managers.NetworkManager;

public class XLauncherApplication extends Application {

    private static XLauncherApplication instance;
    private AccountManager accountManager;
    private FileManager fileManager;
    private GameProfileManager gameProfileManager;
    private NetworkManager networkManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeManagers();
    }

    private void initializeManagers() {
        accountManager = new AccountManager(this);
        fileManager = new FileManager(this);
        gameProfileManager = new GameProfileManager(this);
        networkManager = new NetworkManager();
    }

    public static XLauncherApplication getInstance() {
        return instance;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public GameProfileManager getGameProfileManager() {
        return gameProfileManager;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
