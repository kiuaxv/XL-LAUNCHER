package com.xlauncher.minecraft.data.models;

import java.util.ArrayList;
import java.util.List;

public class GitHubMod extends Mod {
    private String repositoryUrl;
    private String repositoryOwner;
    private String repositoryName;
    private String releaseTag;
    private long downloads;
    private int stars;
    private String lastUpdate;
    private List<String> supportedVersions;
    private String modLoader;
    private boolean verified;

    public GitHubMod(String id, String name, String author) {
        super(id, name, author);
        this.supportedVersions = new ArrayList<>();
    }

    // Getters and Setters
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    public void setRepositoryOwner(String repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getReleaseTag() {
        return releaseTag;
    }

    public void setReleaseTag(String releaseTag) {
        this.releaseTag = releaseTag;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<String> getSupportedVersions() {
        return supportedVersions;
    }

    public void setSupportedVersions(List<String> supportedVersions) {
        this.supportedVersions = supportedVersions;
    }

    public String getModLoader() {
        return modLoader;
    }

    public void setModLoader(String modLoader) {
        this.modLoader = modLoader;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
