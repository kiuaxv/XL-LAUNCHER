package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {
    @SerializedName("login")
    public String username;
    
    @SerializedName("id")
    public long id;
    
    @SerializedName("avatar_url")
    public String avatarUrl;
    
    @SerializedName("email")
    public String email;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("bio")
    public String bio;
    
    @SerializedName("public_repos")
    public int publicRepos;
    
    @SerializedName("followers")
    public int followers;
    
    @SerializedName("following")
    public int following;
    
    @SerializedName("created_at")
    public String createdAt;
    
    @SerializedName("updated_at")
    public String updatedAt;
}
