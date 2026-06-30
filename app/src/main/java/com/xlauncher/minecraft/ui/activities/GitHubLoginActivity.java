package com.xlauncher.minecraft.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.managers.GitHubAuthManager;

public class GitHubLoginActivity extends AppCompatActivity {

    private EditText tokenInput;
    private MaterialButton loginBtn;
    private MaterialButton backBtn;
    private GitHubAuthManager gitHubAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_login);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        tokenInput = findViewById(R.id.github_token_input);
        loginBtn = findViewById(R.id.github_login_btn);
        backBtn = findViewById(R.id.back_button);
        gitHubAuthManager = new GitHubAuthManager(this, null); // Pass NetworkManager if needed
    }

    private void setupListeners() {
        loginBtn.setOnClickListener(v -> performLogin());
        backBtn.setOnClickListener(v -> finish());
    }

    private void performLogin() {
        String token = tokenInput.getText().toString().trim();
        if (token.isEmpty()) {
            Toast.makeText(this, "Please enter GitHub token", Toast.LENGTH_SHORT).show();
            return;
        }

        gitHubAuthManager.authenticateWithToken(token, new GitHubAuthManager.AuthCallback() {
            @Override
            public void onSuccess(com.xlauncher.minecraft.data.models.Account account) {
                Toast.makeText(GitHubLoginActivity.this, "Logged in as " + account.getUsername(), Toast.LENGTH_SHORT).show();
                // TODO: Save account and return to main activity
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(GitHubLoginActivity.this, "Login failed: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
