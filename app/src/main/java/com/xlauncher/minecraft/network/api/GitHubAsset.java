package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class GitHubAsset {
    @SerializedName("id")
    public long id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("label")
    public String label;
    
    @SerializedName("content_type")
    public String contentType;
    
    @SerializedName("state")
    public String state;
    
    @SerializedName("size")
    public long size;
    
    @SerializedName("download_count")
    public int downloadCount;
    
    @SerializedName("created_at")
    public String createdAt;
    
    @SerializedName("updated_at")
    public String updatedAt;
    
    @SerializedName("browser_download_url")
    public String downloadUrl;
}
