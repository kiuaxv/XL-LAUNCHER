package com.xlauncher.minecraft.network.api;

import com.google.gson.annotations.SerializedName;

public class PublishResponse {
    @SerializedName("success")
    public boolean success;
    
    @SerializedName("message")
    public String message;
    
    @SerializedName("modId")
    public String modId;
}
