package com.xlauncher.minecraft.data.models;

public class MinecraftVersion {
    private String id;
    private String type; // RELEASE, SNAPSHOT, OLD_RELEASE, OLD_BETA
    private String url;
    private long releaseTime;
    private long lastModified;
    private String sha1;
    private boolean canInstall;

    public MinecraftVersion(String id, String type) {
        this.id = id;
        this.type = type;
        this.canInstall = true;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public boolean canInstall() {
        return canInstall;
    }

    public void setCanInstall(boolean canInstall) {
        this.canInstall = canInstall;
    }
}
