package com.xlauncher.minecraft.managers;

import com.google.gson.annotations.SerializedName;

public class MicrosoftUserProfile {
    @SerializedName("id")
    public String id;
    
    @SerializedName("userPrincipalName")
    public String userPrincipalName;
    
    @SerializedName("givenName")
    public String givenName;
    
    @SerializedName("surname")
    public String surname;
    
    @SerializedName("mail")
    public String email;
}
