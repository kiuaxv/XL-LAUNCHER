package com.xlauncher.minecraft.managers;

import com.google.gson.annotations.SerializedName;

public class MicrosoftTokenResponse {
    @SerializedName("access_token")
    public String accessToken;
    
    @SerializedName("refresh_token")
    public String refreshToken;
    
    @SerializedName("expires_in")
    public long expiresIn;
    
    @SerializedName("token_type")
    public String tokenType;
}
