package com.xlauncher.minecraft.managers;

import android.content.Context;

import com.xlauncher.minecraft.data.models.GameProfile;

import java.util.ArrayList;
import java.util.List;

public class GameProfileManager {
    private Context context;
    private List<GameProfile> profiles;
    private GameProfile activeProfile;

    public GameProfileManager(Context context) {
        this.context = context;
        this.profiles = new ArrayList<>();
        loadProfiles();
    }

    public void createProfile(GameProfile profile) {
        profiles.add(profile);
        saveProfiles();
    }

    public void deleteProfile(String profileId) {
        profiles.removeIf(p -> p.getId().equals(profileId));
        saveProfiles();
    }

    public GameProfile getProfile(String profileId) {
        return profiles.stream()
                .filter(p -> p.getId().equals(profileId))
                .findFirst()
                .orElse(null);
    }

    public List<GameProfile> getAllProfiles() {
        return new ArrayList<>(profiles);
    }

    public GameProfile getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(String profileId) {
        activeProfile = getProfile(profileId);
    }

    public void updateProfile(GameProfile profile) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getId().equals(profile.getId())) {
                profiles.set(i, profile);
                saveProfiles();
                return;
            }
        }
    }

    private void saveProfiles() {
        // TODO: Implement save to local storage or database
    }

    private void loadProfiles() {
        // TODO: Implement load from local storage or database
    }
}
