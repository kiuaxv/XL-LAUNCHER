package com.xlauncher.minecraft.managers;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final String MINECRAFT_API_URL = "https://launcher.mojang.com/";
    private static final String MOD_API_URL = "https://api.modstore.example.com/"; // TODO: Replace with actual API

    private OkHttpClient httpClient;
    private Retrofit minecraftRetrofit;
    private Retrofit modRetrofit;

    public NetworkManager() {
        initializeNetworkClients();
    }

    private void initializeNetworkClients() {
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("User-Agent", "X-Launcher/1.0")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        minecraftRetrofit = new Retrofit.Builder()
                .baseUrl(MINECRAFT_API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        modRetrofit = new Retrofit.Builder()
                .baseUrl(MOD_API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Retrofit getMinecraftRetrofit() {
        return minecraftRetrofit;
    }

    public Retrofit getModRetrofit() {
        return modRetrofit;
    }

    public String downloadFile(String url, String filePath) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            byte[] fileContent = response.body().bytes();
            // TODO: Save to file
            return filePath;
        }
        throw new IOException("Download failed");
    }
}
