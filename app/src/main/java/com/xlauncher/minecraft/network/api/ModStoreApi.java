package com.xlauncher.minecraft.network.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;
import retrofit2.http.Body;

public interface ModStoreApi {

    @GET("/mods/search")
    Call<ModStoreResponse> searchMods(
            @Query("query") String query,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("/mods/trending")
    Call<ModStoreResponse> getTrendingMods(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("/mods/best")
    Call<ModStoreResponse> getBestMods(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("/mods/category/{category}")
    Call<ModStoreResponse> getModsByCategory(
            @Path("category") String category,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("/mods/{id}")
    Call<ModStoreItem> getModDetails(@Path("id") String id);

    @GET("/mods/{id}/similar")
    Call<ModStoreResponse> getSimilarMods(
            @Path("id") String id,
            @Query("limit") int limit
    );

    @POST("/mods/publish")
    Call<PublishResponse> publishMod(
            @Header("Authorization") String token,
            @Body ModPublishRequest request
    );

    @GET("/mods/github/{owner}/{repo}")
    Call<ModStoreItem> getGitHubModInfo(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
