package com.xlauncher.minecraft.managers;

import com.google.gson.annotations.SerializedName;

public class MicrosoftTokenRequest {
    @SerializedName("client_id")
    public String clientId;
    
    @SerializedName("scope")
    public String scope;
    
    @SerializedName("username")
    public String username;
    
    @SerializedName("password")
    public String password;
    
    @SerializedName("grant_type")
    public String grantType = "password";
}
