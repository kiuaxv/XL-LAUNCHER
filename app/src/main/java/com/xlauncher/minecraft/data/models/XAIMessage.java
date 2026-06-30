package com.xlauncher.minecraft.data.models;

public class XAIMessage {
    private String id;
    private String content;
    private boolean isUserMessage;
    private long timestamp;
    private String messageType; // TEXT, SUGGESTION, WARNING, INFO

    public XAIMessage(String content, boolean isUserMessage) {
        this.id = java.util.UUID.randomUUID().toString();
        this.content = content;
        this.isUserMessage = isUserMessage;
        this.timestamp = System.currentTimeMillis();
        this.messageType = "TEXT";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isUserMessage() {
        return isUserMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
