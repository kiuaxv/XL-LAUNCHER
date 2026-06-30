package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class GitHubContent {
    @SerializedName("name")
    public String name;
    
    @SerializedName("path")
    public String path;
    
    @SerializedName("type")
    public String type;
    
    @SerializedName("size")
    public long size;
    
    @SerializedName("sha")
    public String sha;
    
    @SerializedName("url")
    public String url;
    
    @SerializedName("download_url")
    public String downloadUrl;
}
