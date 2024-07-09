package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ChatMessage;
import com.example.repository.ChatMessageRepository;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
    
    public List<ChatMessage> findChatMessagesByChatGroupId(Integer chatGroupId) {
        return chatMessageRepository.findByChatGroupId(chatGroupId);
    }
}