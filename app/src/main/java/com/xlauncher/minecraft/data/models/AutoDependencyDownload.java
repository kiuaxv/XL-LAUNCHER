package com.xlauncher.minecraft.data.models;

import java.util.ArrayList;
import java.util.List;

public class AutoDependencyDownload {
    private String modId;
    private String modName;
    private List<String> dependencies;
    private List<DependencyItem> dependencyItems;
    private boolean autoDownloadEnabled;
    private long totalSizeBytes;

    public AutoDependencyDownload(String modId, String modName) {
        this.modId = modId;
        this.modName = modName;
        this.dependencies = new ArrayList<>();
        this.dependencyItems = new ArrayList<>();
        this.autoDownloadEnabled = true;
    }

    public void addDependency(String depId, String depName, String version, long sizeBytes) {
        dependencies.add(depId);
        dependencyItems.add(new DependencyItem(depId, depName, version, sizeBytes));
        totalSizeBytes += sizeBytes;
    }

    public static class DependencyItem {
        public String id;
        public String name;
        public String version;
        public long sizeBytes;
        public boolean downloaded;

        public DependencyItem(String id, String name, String version, long sizeBytes) {
            this.id = id;
            this.name = name;
            this.version = version;
            this.sizeBytes = sizeBytes;
            this.downloaded = false;
        }
    }

    // Getters
    public String getModId() {
        return modId;
    }

    public String getModName() {
        return modName;
    }

    public List<String> getDependencies() {
        return new ArrayList<>(dependencies);
    }

    public List<DependencyItem> getDependencyItems() {
        return new ArrayList<>(dependencyItems);
    }

    public boolean isAutoDownloadEnabled() {
        return autoDownloadEnabled;
    }

    public void setAutoDownloadEnabled(boolean enabled) {
        this.autoDownloadEnabled = enabled;
    }

    public long getTotalSizeBytes() {
        return totalSizeBytes;
    }

    public int getDependencyCount() {
        return dependencies.size();
    }
}
