package com.xlauncher.minecraft.managers;

import com.xlauncher.minecraft.data.models.Mod;
import com.xlauncher.minecraft.data.models.XAIMessage;

import java.util.ArrayList;
import java.util.List;

public class XAIAssistant {
    private List<XAIMessage> conversationHistory;
    private ModStoreManager modStoreManager;
    private PerformanceManager performanceManager;

    public XAIAssistant(ModStoreManager modStoreManager, PerformanceManager performanceManager) {
        this.modStoreManager = modStoreManager;
        this.performanceManager = performanceManager;
        this.conversationHistory = new ArrayList<>();
    }

    public void askQuestion(String question, XAICallback callback) {
        XAIMessage userMessage = new XAIMessage(question, true);
        conversationHistory.add(userMessage);

        // Analyze question and provide appropriate response
        String response = analyzeAndRespond(question);
        XAIMessage aiMessage = new XAIMessage(response, false);
        conversationHistory.add(aiMessage);

        callback.onResponse(aiMessage);
    }

    private String analyzeAndRespond(String question) {
        String lowerQuestion = question.toLowerCase();

        if (lowerQuestion.contains("performance") || lowerQuestion.contains("fps")) {
            return getPerformanceAdvice();
        } else if (lowerQuestion.contains("mod") && lowerQuestion.contains("recommend")) {
            return "What type of mods are you interested in? I can recommend Performance Mods, Utility Mods, or Content Mods.";
        } else if (lowerQuestion.contains("crash")) {
            return "Have you checked the crash logs? I can help analyze them. Can you share the error message?";
        } else if (lowerQuestion.contains("memory")) {
            long availableMemory = performanceManager.getAvailableMemoryMB();
            return "Your device has " + availableMemory + "MB available memory. I recommend allocating 2-3GB for Minecraft Java Edition.";
        } else {
            return "How can I help you with X-Launcher today? I can assist with mods, performance, crashes, and more!";
        }
    }

    private String getPerformanceAdvice() {
        int memoryPercentage = performanceManager.getMemoryPercentage();
        int cores = performanceManager.getCoreCount();

        StringBuilder advice = new StringBuilder();
        advice.append("Based on your device specs (\n");
        advice.append("Memory: ").append(memoryPercentage).append("% used\n");
        advice.append("Cores: ").append(cores).append("\n");
        advice.append("I recommend:\n");

        if (memoryPercentage > 70) {
            advice.append("- Close background apps to free up memory\n");
        }
        advice.append("- Use OptiFine for better FPS\n");
        advice.append("- Install Lithium mod for server optimization\n");

        return advice.toString();
    }

    public List<XAIMessage> getConversationHistory() {
        return new ArrayList<>(conversationHistory);
    }

    public void clearConversation() {
        conversationHistory.clear();
    }

    public interface XAICallback {
        void onResponse(XAIMessage message);
    }
}
