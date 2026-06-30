package com.xlauncher.minecraft.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.xlauncher.minecraft.R;

public class MainActivity extends AppCompatActivity {

    private ImageView xaiRobot;
    private ImageView smartStoreRobot;
    private MaterialButton playBtn;
    private MaterialButton editProfileBtn;
    private TextView versionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        xaiRobot = findViewById(R.id.xai_robot);
        playBtn = findViewById(R.id.play_btn);
        editProfileBtn = findViewById(R.id.edit_profile_btn);
        versionNumber = findViewById(R.id.version_number);
    }

    private void setupClickListeners() {
        xaiRobot.setOnClickListener(v -> openXAI());
        playBtn.setOnClickListener(v -> launchMinecraft());
        editProfileBtn.setOnClickListener(v -> openProfileEditor());
    }

    private void openXAI() {
        startActivity(new Intent(this, XAIActivity.class));
    }

    private void openSmartStore() {
        startActivity(new Intent(this, SmartStoreActivity.class));
    }

    private void launchMinecraft() {
        // TODO: Implement Minecraft launch logic
    }

    private void openProfileEditor() {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
