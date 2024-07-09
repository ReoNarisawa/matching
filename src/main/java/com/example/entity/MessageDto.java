package com.example.entity;

public class MessageDto {
    private String sender;
    private String recipient;
    private String content;
    private String timestamp;
    private Integer chatGroupId;

    // Getters and setters
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public Integer getChatGroupId() {
        return chatGroupId;
    }
    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }
}
