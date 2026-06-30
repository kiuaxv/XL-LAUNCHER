package com.xlauncher.minecraft.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Debug;

public class SystemUtils {

    public static long getAvailableMemory(Context context) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / 1048576L;
        long totalMemory = runtime.totalMemory() / 1048576L;
        long freeMemory = runtime.freeMemory() / 1048576L;
        return freeMemory;
    }

    public static long getTotalMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.maxMemory() / 1048576L;
    }

    public static int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static String getAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
