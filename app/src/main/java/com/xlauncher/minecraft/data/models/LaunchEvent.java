package com.xlauncher.minecraft.data.models;

import java.util.ArrayList;
import java.util.List;

public class LaunchEvent {
    private String profileId;
    private long launchTime;
    private long duration; // in milliseconds
    private boolean successful;
    private String errorMessage;
    private int fps;
    private long ramUsed; // in MB
    private int modsCount;
    private List<String> activeMods;

    public LaunchEvent(String profileId) {
        this.profileId = profileId;
        this.launchTime = System.currentTimeMillis();
        this.activeMods = new ArrayList<>();
    }

    // Getters and Setters
    public String getProfileId() {
        return profileId;
    }

    public long getLaunchTime() {
        return launchTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public long getRamUsed() {
        return ramUsed;
    }

    public void setRamUsed(long ramUsed) {
        this.ramUsed = ramUsed;
    }

    public int getModsCount() {
        return modsCount;
    }

    public void setModsCount(int modsCount) {
        this.modsCount = modsCount;
    }

    public List<String> getActiveMods() {
        return activeMods;
    }

    public void setActiveMods(List<String> activeMods) {
        this.activeMods = activeMods;
    }
}
