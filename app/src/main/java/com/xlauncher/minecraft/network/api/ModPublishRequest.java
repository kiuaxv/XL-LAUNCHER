package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModPublishRequest {
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
    
    @SerializedName("dependencies")
    public List<String> dependencies;
    
    @SerializedName("category")
    public String category;
    
    @SerializedName("sourceUrl")
    public String sourceUrl; // GitHub URL for GitHub mods
}
