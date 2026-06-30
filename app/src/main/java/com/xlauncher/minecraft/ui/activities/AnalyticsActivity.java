package com.xlauncher.minecraft.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.managers.AnalyticsManager;

public class AnalyticsActivity extends AppCompatActivity {

    private AnalyticsManager analyticsManager;
    private TextView successRateText;
    private TextView totalLaunchesText;
    private TextView totalCrashesText;
    private TextView avgSessionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        initializeViews();
        loadAnalytics();
    }

    private void initializeViews() {
        analyticsManager = new AnalyticsManager(this);
        successRateText = findViewById(R.id.success_rate);
        totalLaunchesText = findViewById(R.id.total_launches);
        totalCrashesText = findViewById(R.id.total_crashes);
        avgSessionText = findViewById(R.id.avg_session);
    }

    private void loadAnalytics() {
        double successRate = analyticsManager.getSuccessRate();
        int totalLaunches = analyticsManager.getLaunchHistory().size();
        int totalCrashes = analyticsManager.getTotalCrashes();
        double avgSession = analyticsManager.getAverageSessionDuration();

        successRateText.setText(String.format("%.1f%%", successRate));
        totalLaunchesText.setText(String.valueOf(totalLaunches));
        totalCrashesText.setText(String.valueOf(totalCrashes));
        avgSessionText.setText(String.format("%.1f sec", avgSession));
    }
}
