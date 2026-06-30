package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModStoreItem {
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("author")
    public String author;
    
    @SerializedName("description")
    public String description;
    
    @SerializedName("version")
    public String version;
    
    @SerializedName("minecraftVersion")
    public String minecraftVersion;
    
    @SerializedName("modLoader")
    public String modLoader;
    
    @SerializedName("downloadUrl")
    public String downloadUrl;
    
    @SerializedName("imageUrl")
    public String imageUrl;
    
    @SerializedName("downloads")
    public long downloads;
    
    @SerializedName("rating")
    public double rating;
    
    @SerializedName("dependencies")
    public List<String> dependencies;
    
    @SerializedName("verified")
    public boolean verified;
    
    @SerializedName("category")
    public String category;
}
