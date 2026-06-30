package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileManager {
    private static final String LAUNCHER_DIR = ".xlauncher";
    private static final String VERSIONS_DIR = "versions";
    private static final String MODS_DIR = "mods";
    private static final String RESOURCEPACKS_DIR = "resourcepacks";
    private static final String SCREENSHOTS_DIR = "screenshots";
    private static final String WORLDS_DIR = "worlds";

    private File launcherDir;
    private File versionsDir;
    private File modsDir;
    private File resourcePacksDir;
    private File screenshotsDir;
    private File worldsDir;

    public FileManager(Context context) {
        initializeDirectories();
    }

    private void initializeDirectories() {
        File externalFilesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        launcherDir = new File(externalFilesDir, LAUNCHER_DIR);
        
        if (!launcherDir.exists()) {
            launcherDir.mkdirs();
        }

        versionsDir = new File(launcherDir, VERSIONS_DIR);
        modsDir = new File(launcherDir, MODS_DIR);
        resourcePacksDir = new File(launcherDir, RESOURCEPACKS_DIR);
        screenshotsDir = new File(launcherDir, SCREENSHOTS_DIR);
        worldsDir = new File(launcherDir, WORLDS_DIR);

        createDirectoriesIfNotExist(versionsDir, modsDir, resourcePacksDir, screenshotsDir, worldsDir);
    }

    private void createDirectoriesIfNotExist(File... directories) {
        for (File dir : directories) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    public File getLauncherDir() {
        return launcherDir;
    }

    public File getVersionsDir() {
        return versionsDir;
    }

    public File getModsDir() {
        return modsDir;
    }

    public File getResourcePacksDir() {
        return resourcePacksDir;
    }

    public File getScreenshotsDir() {
        return screenshotsDir;
    }

    public File getWorldsDir() {
        return worldsDir;
    }

    public long getTotalLauncherSize() {
        return calculateDirSize(launcherDir);
    }

    private long calculateDirSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    size += calculateDirSize(f);
                }
            }
        } else {
            size = file.length();
        }
        return size;
    }

    public void clearModsCache() {
        deleteDir(modsDir);
        modsDir.mkdirs();
    }

    private void deleteDir(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }
}
