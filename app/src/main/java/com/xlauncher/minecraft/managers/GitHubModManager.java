package com.xlauncher.minecraft.managers;

import android.content.Context;

import com.xlauncher.minecraft.data.models.GitHubMod;
import com.xlauncher.minecraft.network.api.GitHubApi;
import com.xlauncher.minecraft.network.api.GitHubAsset;
import com.xlauncher.minecraft.network.api.GitHubRelease;
import com.xlauncher.minecraft.network.api.GitHubRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubModManager {
    private static final String GITHUB_API_URL = "https://api.github.com/";
    private static final String MOD_MARKER = "mod-";
    private static final String JAR_EXTENSION = ".jar";

    private Context context;
    private GitHubApi gitHubApi;
    private List<GitHubMod> userMods;

    public GitHubModManager(Context context) {
        this.context = context;
        this.userMods = new ArrayList<>();
        initializeGitHubApi();
    }

    private void initializeGitHubApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_URL)
                .addConverterFactory(com.google.gson.Gson.class)
                .build();
        this.gitHubApi = retrofit.create(GitHubApi.class);
    }

    public void fetchUserMods(String token, ModsCallback callback) {
        String authHeader = "token " + token;
        Call<GitHubRepository[]> call = gitHubApi.getUserRepositories(
                authHeader,
                "all",
                "updated",
                100
        );

        call.enqueue(new Callback<GitHubRepository[]>() {
            @Override
            public void onResponse(Call<GitHubRepository[]> call, Response<GitHubRepository[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    processMods(response.body(), authHeader, callback);
                } else {
                    callback.onError("Failed to fetch repositories");
                }
            }

            @Override
            public void onFailure(Call<GitHubRepository[]> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private void processMods(GitHubRepository[] repositories, String authHeader, ModsCallback callback) {
        userMods.clear();
        List<Call<GitHubRelease[]>> releaseCalls = new ArrayList<>();

        for (GitHubRepository repo : repositories) {
            // Check if repository looks like a mod (contains mod marker or releases with jar)
            if (repo.name.toLowerCase().contains(MOD_MARKER) || repo.description != null && repo.description.toLowerCase().contains("mod")) {
                GitHubMod mod = new GitHubMod(
                        String.valueOf(repo.id),
                        repo.name,
                        repo.owner.username
                );
                mod.setRepositoryUrl(repo.htmlUrl);
                mod.setRepositoryOwner(repo.owner.username);
                mod.setRepositoryName(repo.name);
                mod.setDescription(repo.description);
                mod.setStars(repo.starsCount);
                mod.setLastUpdate(repo.updatedAt);

                // Fetch releases to get downloads
                Call<GitHubRelease[]> releaseCall = gitHubApi.getRepositoryReleases(
                        repo.owner.username,
                        repo.name,
                        authHeader
                );

                releaseCall.enqueue(new Callback<GitHubRelease[]>() {
                    @Override
                    public void onResponse(Call<GitHubRelease[]> call, Response<GitHubRelease[]> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            processReleases(mod, response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<GitHubRelease[]> call, Throwable t) {
                        // Continue with empty releases
                    }
                });

                userMods.add(mod);
            }
        }

        callback.onSuccess(userMods);
    }

    private void processReleases(GitHubMod mod, GitHubRelease[] releases) {
        long totalDownloads = 0;
        for (GitHubRelease release : releases) {
            if (release.assets != null) {
                for (GitHubAsset asset : release.assets) {
                    if (asset.name.endsWith(JAR_EXTENSION)) {
                        totalDownloads += asset.downloadCount;
                        if (mod.getDownloadUrl() == null) {
                            mod.setDownloadUrl(asset.downloadUrl);
                            mod.setVersion(release.tagName);
                            mod.setReleaseTag(release.tagName);
                        }
                    }
                }
            }
        }
        mod.setDownloads(totalDownloads);
    }

    public List<GitHubMod> getUserMods() {
        return new ArrayList<>(userMods);
    }

    public void publishModToSmartStore(GitHubMod mod, PublishCallback callback) {
        // TODO: Implement publishing to Smart Store
        // This would involve uploading mod metadata to your store database
        callback.onSuccess("Mod published successfully");
    }

    public interface ModsCallback {
        void onSuccess(List<GitHubMod> mods);
        void onError(String error);
    }

    public interface PublishCallback {
        void onSuccess(String message);
        void onError(String error);
    }
}
