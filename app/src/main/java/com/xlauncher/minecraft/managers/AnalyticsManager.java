package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xlauncher.minecraft.data.models.LaunchEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyticsManager {
    private static final String PREFS_NAME = "xl_analytics";
    private static final String LAUNCH_HISTORY = "launch_history";
    private static final String CRASH_HISTORY = "crash_history";
    private static final int MAX_HISTORY = 100;

    private SharedPreferences prefs;
    private Gson gson;
    private List<LaunchEvent> launchHistory;
    private List<CrashEvent> crashHistory;

    public AnalyticsManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        this.launchHistory = new ArrayList<>();
        this.crashHistory = new ArrayList<>();
        loadHistory();
    }

    public void recordLaunch(LaunchEvent event) {
        launchHistory.add(0, event);
        if (launchHistory.size() > MAX_HISTORY) {
            launchHistory.remove(launchHistory.size() - 1);
        }
        saveHistory();
    }

    public void recordCrash(CrashEvent event) {
        crashHistory.add(0, event);
        if (crashHistory.size() > MAX_HISTORY) {
            crashHistory.remove(crashHistory.size() - 1);
        }
        saveHistory();
    }

    public List<LaunchEvent> getLaunchHistory() {
        return new ArrayList<>(launchHistory);
    }

    public List<CrashEvent> getCrashHistory() {
        return new ArrayList<>(crashHistory);
    }

    public double getAverageSessionDuration() {
        if (launchHistory.isEmpty()) return 0;
        long total = 0;
        int count = 0;
        for (LaunchEvent event : launchHistory) {
            if (event.isSuccessful()) {
                total += event.getDuration();
                count++;
            }
        }
        return count > 0 ? (total / count) / 1000.0 : 0; // Return in seconds
    }

    public double getSuccessRate() {
        if (launchHistory.isEmpty()) return 0;
        long successful = launchHistory.stream()
                .filter(LaunchEvent::isSuccessful)
                .count();
        return (successful * 100.0) / launchHistory.size();
    }

    public int getTotalCrashes() {
        return crashHistory.size();
    }

    private void saveHistory() {
        String launchJson = gson.toJson(launchHistory);
        String crashJson = gson.toJson(crashHistory);
        prefs.edit()
                .putString(LAUNCH_HISTORY, launchJson)
                .putString(CRASH_HISTORY, crashJson)
                .apply();
    }

    private void loadHistory() {
        String launchJson = prefs.getString(LAUNCH_HISTORY, null);
        String crashJson = prefs.getString(CRASH_HISTORY, null);

        if (launchJson != null) {
            Type type = new TypeToken<List<LaunchEvent>>() {}.getType();
            launchHistory = gson.fromJson(launchJson, type);
            if (launchHistory == null) launchHistory = new ArrayList<>();
        }

        if (crashJson != null) {
            Type type = new TypeToken<List<CrashEvent>>() {}.getType();
            crashHistory = gson.fromJson(crashJson, type);
            if (crashHistory == null) crashHistory = new ArrayList<>();
        }
    }

    public static class CrashEvent {
        public long timestamp;
        public String profileId;
        public String errorMessage;
        public String stackTrace;

        public CrashEvent(String profileId, String errorMessage, String stackTrace) {
            this.timestamp = System.currentTimeMillis();
            this.profileId = profileId;
            this.errorMessage = errorMessage;
            this.stackTrace = stackTrace;
        }
    }
}
