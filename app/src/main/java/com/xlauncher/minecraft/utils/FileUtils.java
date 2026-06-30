package com.xlauncher.minecraft.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

    public static void unzip(InputStream inputStream, String outputPath) throws IOException {
        ZipInputStream zis = new ZipInputStream(inputStream);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String filePath = outputPath + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zis, filePath);
            } else {
                new java.io.File(filePath).mkdir();
            }
            zis.closeEntry();
        }
        zis.close();
    }

    private static void extractFile(ZipInputStream zis, String filePath) throws IOException {
        OutputStream os = new java.io.FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = zis.read(buffer)) != -1) {
            os.write(buffer, 0, length);
        }
        os.close();
    }

    public static String formatFileSize(long bytes) {
        if (bytes <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.1f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    public static long calculateFileSize(java.io.File file) {
        if (!file.exists()) return 0;
        if (file.isFile()) return file.length();

        long size = 0;
        java.io.File[] files = file.listFiles();
        if (files != null) {
            for (java.io.File f : files) {
                size += calculateFileSize(f);
            }
        }
        return size;
    }
}
