package com.xlauncher.minecraft.network.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/user")
    Call<GitHubUser> getUser(@Header("Authorization") String token);

    @GET("/user/repos")
    Call<GitHubRepository[]> getUserRepositories(
            @Header("Authorization") String token,
            @Query("type") String type,
            @Query("sort") String sort,
            @Query("per_page") int perPage
    );

    @GET("/repos/{owner}/{repo}")
    Call<GitHubRepository> getRepository(
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Header("Authorization") String token
    );

    @GET("/repos/{owner}/{repo}/releases")
    Call<GitHubRelease[]> getRepositoryReleases(
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Header("Authorization") String token
    );

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Call<GitHubContent> getRepositoryContent(
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Path("path") String path,
            @Header("Authorization") String token
    );
}
