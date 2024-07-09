package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_groupid")
    private Integer chatGroupId;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private String timestamp;
    
    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private String recipient;
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getChatGroupId() {
        return chatGroupId;
    }
    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
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
}
