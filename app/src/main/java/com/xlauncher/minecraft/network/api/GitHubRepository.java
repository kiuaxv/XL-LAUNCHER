package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class GitHubRepository {
    @SerializedName("id")
    public long id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("full_name")
    public String fullName;
    
    @SerializedName("owner")
    public GitHubUser owner;
    
    @SerializedName("html_url")
    public String htmlUrl;
    
    @SerializedName("description")
    public String description;
    
    @SerializedName("url")
    public String url;
    
    @SerializedName("stargazers_count")
    public int starsCount;
    
    @SerializedName("watchers_count")
    public int watchersCount;
    
    @SerializedName("language")
    public String language;
    
    @SerializedName("created_at")
    public String createdAt;
    
    @SerializedName("updated_at")
    public String updatedAt;
    
    @SerializedName("pushed_at")
    public String pushedAt;
}
