package com.xlauncher.minecraft.managers;

import android.os.Build;
import android.app.ActivityManager;
import android.content.Context;

public class PerformanceManager {
    private Context context;
    private ActivityManager activityManager;

    public PerformanceManager(Context context) {
        this.context = context;
        this.activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    public long getAvailableMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory()) / 1048576L;
    }

    public long getTotalMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.maxMemory() / 1048576L;
    }

    public int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public OptimalJavaSettings generateOptimalJavaSettings() {
        OptimalJavaSettings settings = new OptimalJavaSettings();
        long totalMemory = getTotalMemoryMB();
        
        // Allocate 50-70% of available memory to game
        if (totalMemory >= 8192) {
            settings.minMemory = 4096;
            settings.maxMemory = 6144;
        } else if (totalMemory >= 4096) {
            settings.minMemory = 2048;
            settings.maxMemory = 3072;
        } else {
            settings.minMemory = 1024;
            settings.maxMemory = 2048;
        }

        // Generate JVM arguments
        settings.jvmArgs = generateJvmArguments(settings.maxMemory);
        return settings;
    }

    private String generateJvmArguments(long maxMemory) {
        StringBuilder args = new StringBuilder();
        args.append("-Xmx").append(maxMemory).append("M ");
        args.append("-Xms512M ");
        args.append("-XX:+UseG1GC ");
        args.append("-XX:MaxGCPauseMillis=200 ");
        args.append("-XX:+UnlockExperimentalVMOptions ");
        args.append("-XX:G1NewCollectionPercentThreshold=30 ");
        args.append("-XX:G1MaxNewGenPercent=40 ");
        args.append("-XX:InitiatingHeapOccupancyPercent=35 ");
        args.append("-XX:+DisableExplicitGC ");
        args.append("-XX:-UseAdaptiveSizePolicy ");
        args.append("-XX:-OmitStackTraceInFastThrow");
        return args.toString();
    }

    public static class OptimalJavaSettings {
        public long minMemory;
        public long maxMemory;
        public String jvmArgs;
    }
}
