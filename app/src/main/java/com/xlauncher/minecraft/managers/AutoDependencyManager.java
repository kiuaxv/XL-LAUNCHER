package com.xlauncher.minecraft.managers;

import com.xlauncher.minecraft.data.models.AutoDependencyDownload;
import com.xlauncher.minecraft.data.models.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoDependencyManager {
    private Map<String, AutoDependencyDownload> dependencyMap;
    private List<Mod> allMods;

    public AutoDependencyManager() {
        this.dependencyMap = new HashMap<>();
        this.allMods = new ArrayList<>();
    }

    public void registerMods(List<Mod> mods) {
        this.allMods.addAll(mods);
    }

    public AutoDependencyDownload analyzeDependencies(Mod mod) {
        AutoDependencyDownload autoDep = new AutoDependencyDownload(mod.getId(), mod.getName());

        if (mod.getDependencies() != null) {
            for (String depId : mod.getDependencies()) {
                Mod depMod = findModById(depId);
                if (depMod != null) {
                    autoDep.addDependency(
                            depMod.getId(),
                            depMod.getName(),
                            depMod.getVersion(),
                            (long)(depMod.getFileSizeInMB() * 1024 * 1024)
                    );
                }
            }
        }

        dependencyMap.put(mod.getId(), autoDep);
        return autoDep;
    }

    public void downloadModWithDependencies(Mod mod, DownloadCallback callback) {
        AutoDependencyDownload autoDep = analyzeDependencies(mod);

        if (!autoDep.isAutoDownloadEnabled()) {
            callback.onError("Auto download is disabled");
            return;
        }

        // Download main mod
        downloadMod(mod, new DownloadCallback() {
            @Override
            public void onSuccess(String message) {
                // Download all dependencies
                downloadAllDependencies(autoDep, callback);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

    private void downloadAllDependencies(AutoDependencyDownload autoDep, DownloadCallback callback) {
        List<AutoDependencyDownload.DependencyItem> items = autoDep.getDependencyItems();
        if (items.isEmpty()) {
            callback.onSuccess("All dependencies downloaded");
            return;
        }

        int[] downloaded = {0};
        for (AutoDependencyDownload.DependencyItem item : items) {
            Mod depMod = findModById(item.id);
            if (depMod != null) {
                downloadMod(depMod, new DownloadCallback() {
                    @Override
                    public void onSuccess(String message) {
                        item.downloaded = true;
                        downloaded[0]++;
                        if (downloaded[0] == items.size()) {
                            callback.onSuccess("Mod and all dependencies downloaded");
                        }
                    }

                    @Override
                    public void onError(String error) {
                        callback.onError("Failed to download " + item.name + ": " + error);
                    }
                });
            }
        }
    }

    private void downloadMod(Mod mod, DownloadCallback callback) {
        // TODO: Implement actual download logic
        callback.onSuccess("Downloaded " + mod.getName());
    }

    private Mod findModById(String id) {
        for (Mod mod : allMods) {
            if (mod.getId().equals(id)) {
                return mod;
            }
        }
        return null;
    }

    public AutoDependencyDownload getDependencyInfo(String modId) {
        return dependencyMap.get(modId);
    }

    public interface DownloadCallback {
        void onSuccess(String message);
        void onError(String error);
    }
}
