package com.xlauncher.minecraft.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.xlauncher.minecraft.data.models.Mod;
import com.xlauncher.minecraft.network.api.ModStoreApi;
import com.xlauncher.minecraft.network.api.ModStoreItem;
import com.xlauncher.minecraft.network.api.ModStoreResponse;
import com.xlauncher.minecraft.network.api.ModPublishRequest;
import com.xlauncher.minecraft.network.api.PublishResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModStoreManager {
    private static final String MOD_STORE_API_URL = "https://api.modstore.example.com/"; // TODO: Replace with real API
    private static final String PREFS_NAME = "xl_mod_store";
    private static final String CACHE_KEY = "mod_cache";

    private ModStoreApi modStoreApi;
    private SharedPreferences prefs;
    private Gson gson;
    private List<Mod> cachedMods;

    public ModStoreManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        this.cachedMods = new ArrayList<>();
        initializeModStoreApi();
    }

    private void initializeModStoreApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOD_STORE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.modStoreApi = retrofit.create(ModStoreApi.class);
    }

    public void searchMods(String query, int page, SearchCallback callback) {
        Call<ModStoreResponse> call = modStoreApi.searchMods(query, page, 20);
        call.enqueue(new Callback<ModStoreResponse>() {
            @Override
            public void onResponse(Call<ModStoreResponse> call, Response<ModStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mod> mods = convertToMods(response.body().mods);
                    callback.onSuccess(mods);
                } else {
                    callback.onError("Search failed");
                }
            }

            @Override
            public void onFailure(Call<ModStoreResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getTrendingMods(int page, ModsCallback callback) {
        Call<ModStoreResponse> call = modStoreApi.getTrendingMods(page, 20);
        call.enqueue(new Callback<ModStoreResponse>() {
            @Override
            public void onResponse(Call<ModStoreResponse> call, Response<ModStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mod> mods = convertToMods(response.body().mods);
                    callback.onSuccess(mods);
                } else {
                    callback.onError("Failed to fetch trending mods");
                }
            }

            @Override
            public void onFailure(Call<ModStoreResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getBestRatedMods(int page, ModsCallback callback) {
        Call<ModStoreResponse> call = modStoreApi.getBestMods(page, 20);
        call.enqueue(new Callback<ModStoreResponse>() {
            @Override
            public void onResponse(Call<ModStoreResponse> call, Response<ModStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mod> mods = convertToMods(response.body().mods);
                    callback.onSuccess(mods);
                } else {
                    callback.onError("Failed to fetch best mods");
                }
            }

            @Override
            public void onFailure(Call<ModStoreResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getSimilarMods(String modId, SimilarModsCallback callback) {
        Call<ModStoreResponse> call = modStoreApi.getSimilarMods(modId, 10);
        call.enqueue(new Callback<ModStoreResponse>() {
            @Override
            public void onResponse(Call<ModStoreResponse> call, Response<ModStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mod> mods = convertToMods(response.body().mods);
                    callback.onSuccess(mods);
                } else {
                    callback.onError("Failed to fetch similar mods");
                }
            }

            @Override
            public void onFailure(Call<ModStoreResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void publishModFromGitHub(String token, String modName, String author, 
                                     String description, String version, 
                                     String minecraftVersion, String modLoader,
                                     String downloadUrl, String githubUrl,
                                     List<String> dependencies, PublishCallback callback) {
        ModPublishRequest request = new ModPublishRequest();
        request.name = modName;
        request.author = author;
        request.description = description;
        request.version = version;
        request.minecraftVersion = minecraftVersion;
        request.modLoader = modLoader;
        request.downloadUrl = downloadUrl;
        request.sourceUrl = githubUrl;
        request.dependencies = dependencies;
        request.category = "GitHub";

        Call<PublishResponse> call = modStoreApi.publishMod("Bearer " + token, request);
        call.enqueue(new Callback<PublishResponse>() {
            @Override
            public void onResponse(Call<PublishResponse> call, Response<PublishResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().message);
                } else {
                    callback.onError("Publish failed");
                }
            }

            @Override
            public void onFailure(Call<PublishResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private List<Mod> convertToMods(List<ModStoreItem> items) {
        List<Mod> mods = new ArrayList<>();
        for (ModStoreItem item : items) {
            Mod mod = new Mod(item.id, item.name, item.author);
            mod.setDescription(item.description);
            mod.setVersion(item.version);
            mod.setDownloadUrl(item.downloadUrl);
            mod.setImageUrl(item.imageUrl);
            mod.setDownloadCount(item.downloads);
            mod.setRating(item.rating);
            mod.setMinecraftVersion(item.minecraftVersion);
            mod.setModLoader(item.modLoader);
            mods.add(mod);
        }
        return mods;
    }

    public interface ModsCallback {
        void onSuccess(List<Mod> mods);
        void onError(String error);
    }

    public interface SearchCallback {
        void onSuccess(List<Mod> mods);
        void onError(String error);
    }

    public interface SimilarModsCallback {
        void onSuccess(List<Mod> mods);
        void onError(String error);
    }

    public interface PublishCallback {
        void onSuccess(String message);
        void onError(String error);
    }
}
