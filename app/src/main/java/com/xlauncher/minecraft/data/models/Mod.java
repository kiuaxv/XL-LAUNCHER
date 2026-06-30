package com.xlauncher.minecraft.data.models;

public class Mod {
    private String id;
    private String name;
    private String description;
    private String author;
    private String version;
    private String downloadUrl;
    private String imageUrl;
    private long downloadCount;
    private double rating;
    private String minecraftVersion;
    private String modLoader; // Fabric, Forge, NeoForge
    private double fileSizeInMB;
    private boolean hasOptionalDependencies;
    private String[] dependencies; // Auto Dependency Download

    public Mod(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getMinecraftVersion() {
        return minecraftVersion;
    }

    public void setMinecraftVersion(String minecraftVersion) {
        this.minecraftVersion = minecraftVersion;
    }

    public String getModLoader() {
        return modLoader;
    }

    public void setModLoader(String modLoader) {
        this.modLoader = modLoader;
    }

    public double getFileSizeInMB() {
        return fileSizeInMB;
    }

    public void setFileSizeInMB(double fileSizeInMB) {
        this.fileSizeInMB = fileSizeInMB;
    }

    public boolean hasOptionalDependencies() {
        return hasOptionalDependencies;
    }

    public void setHasOptionalDependencies(boolean hasOptionalDependencies) {
        this.hasOptionalDependencies = hasOptionalDependencies;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(String[] dependencies) {
        this.dependencies = dependencies;
    }
}
