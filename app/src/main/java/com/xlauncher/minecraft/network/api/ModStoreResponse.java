package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModStoreResponse {
    @SerializedName("mods")
    public List<ModStoreItem> mods;
    
    @SerializedName("total")
    public int total;
    
    @SerializedName("page")
    public int page;
    
    @SerializedName("pageSize")
    public int pageSize;
}
