package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class GitHubRelease {
    @SerializedName("id")
    public long id;
    
    @SerializedName("tag_name")
    public String tagName;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("draft")
    public boolean draft;
    
    @SerializedName("prerelease")
    public boolean prerelease;
    
    @SerializedName("created_at")
    public String createdAt;
    
    @SerializedName("published_at")
    public String publishedAt;
    
    @SerializedName("assets")
    public GitHubAsset[] assets;
    
    @SerializedName("body")
    public String description;
}
