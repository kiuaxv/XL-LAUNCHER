package com.xlauncher.minecraft.managers;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

public class PerformanceManager {
    private Context context;
    private ActivityManager activityManager;
    private Runtime runtime;

    public PerformanceManager(Context context) {
        this.context = context;
        this.activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        this.runtime = Runtime.getRuntime();
    }

    public long getAvailableMemoryMB() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / (1024 * 1024);
    }

    public long getTotalMemoryMB() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem / (1024 * 1024);
    }

    public long getUsedMemoryMB() {
        return getTotalMemoryMB() - getAvailableMemoryMB();
    }

    public int getMemoryPercentage() {
        long used = getUsedMemoryMB();
        long total = getTotalMemoryMB();
        return (int) ((used * 100) / total);
    }

    public int getCoreCount() {
        return runtime.availableProcessors();
    }

    public String generateJavaArguments(int allocatedMemory) {
        // Generate optimal Java arguments based on device specs
        StringBuilder args = new StringBuilder();
        args.append("-Xmx").append(allocatedMemory).append("M ");
        args.append("-Xms").append(allocatedMemory / 2).append("M ");
        args.append("-XX:+UseG1GC ");
        args.append("-XX:MaxGCPauseMillis=200 ");
        args.append("-XX:+UnlockExperimentalVMOptions ");
        args.append("-XX:G1NewCollectionPercentThreshold=30 ");
        args.append("-XX:G1MaxNewGenPercent=40 ");
        return args.toString();
    }

    public void optimizePerformance() {
        // Clear app cache
        try {
            context.getCacheDir().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDeviceCapableOfRunning(String minecraftVersion) {
        // Check if device meets minimum requirements
        long availableMemory = getAvailableMemoryMB();
        int cores = getCoreCount();

        // Minimum requirements for Minecraft Java
        return availableMemory >= 512 && cores >= 2;
    }
}
