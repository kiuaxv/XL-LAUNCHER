package com.xlauncher.minecraft.data.models;

public class GameProfile {
    private String id;
    private String name;
    private String version; // 1.20.1, 1.19.2, etc
    private String modLoader; // VANILLA, FABRIC, FORGE, NEOFORGE
    private String[] mods;
    private String javaPath;
    private int javaMemory; // in MB
    private String javaArguments;
    private boolean isLaunched;
    private long lastPlayedTime;
    private long totalPlayTime;

    public GameProfile(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.modLoader = "VANILLA";
        this.lastPlayedTime = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModLoader() {
        return modLoader;
    }

    public void setModLoader(String modLoader) {
        this.modLoader = modLoader;
    }

    public String[] getMods() {
        return mods;
    }

    public void setMods(String[] mods) {
        this.mods = mods;
    }

    public String getJavaPath() {
        return javaPath;
    }

    public void setJavaPath(String javaPath) {
        this.javaPath = javaPath;
    }

    public int getJavaMemory() {
        return javaMemory;
    }

    public void setJavaMemory(int javaMemory) {
        this.javaMemory = javaMemory;
    }

    public String getJavaArguments() {
        return javaArguments;
    }

    public void setJavaArguments(String javaArguments) {
        this.javaArguments = javaArguments;
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public void setLaunched(boolean launched) {
        isLaunched = launched;
    }

    public long getLastPlayedTime() {
        return lastPlayedTime;
    }

    public void setLastPlayedTime(long lastPlayedTime) {
        this.lastPlayedTime = lastPlayedTime;
    }

    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(long totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }
}
