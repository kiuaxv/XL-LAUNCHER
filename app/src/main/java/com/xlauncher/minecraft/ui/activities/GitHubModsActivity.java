package com.xlauncher.minecraft.ui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.managers.GitHubAuthManager;
import com.xlauncher.minecraft.managers.GitHubModManager;
import com.xlauncher.minecraft.ui.adapters.GitHubModsAdapter;

public class GitHubModsActivity extends AppCompatActivity {

    private RecyclerView modsRecycler;
    private GitHubModsAdapter adapter;
    private GitHubModManager gitHubModManager;
    private GitHubAuthManager gitHubAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_mods);

        initializeViews();
        loadUserMods();
    }

    private void initializeViews() {
        modsRecycler = findViewById(R.id.github_mods_recycler);
        modsRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GitHubModsAdapter(this, new GitHubModsAdapter.OnModActionListener() {
            @Override
            public void onPublishClick(com.xlauncher.minecraft.data.models.GitHubMod mod) {
                publishModToStore(mod);
            }
        });
        modsRecycler.setAdapter(adapter);

        gitHubModManager = new GitHubModManager(this);
        gitHubAuthManager = new GitHubAuthManager(this, null);
    }

    private void loadUserMods() {
        String token = gitHubAuthManager.getStoredToken();
        if (token != null) {
            gitHubModManager.fetchUserMods(token, new GitHubModManager.ModsCallback() {
                @Override
                public void onSuccess(java.util.List<com.xlauncher.minecraft.data.models.GitHubMod> mods) {
                    adapter.setMods(mods);
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(GitHubModsActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Not authenticated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void publishModToStore(com.xlauncher.minecraft.data.models.GitHubMod mod) {
        gitHubModManager.publishModToSmartStore(mod, new GitHubModManager.PublishCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(GitHubModsActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(GitHubModsActivity.this, "Publish failed: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
