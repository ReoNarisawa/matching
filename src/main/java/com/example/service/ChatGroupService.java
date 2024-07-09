package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ChatGroup;
import com.example.repository.ChatGroupRepository;

@Service
public class ChatGroupService {
    @Autowired
    private ChatGroupRepository chatGroupRepository;

    public ChatGroup findOrCreateChatGroup(Integer userId, Integer companyId) {
        return chatGroupRepository.findByUserIdAndCompanyId(userId, companyId)
                .orElseGet(() -> createChatGroup(userId, companyId));
    }

    private ChatGroup createChatGroup(Integer userId, Integer companyId) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setUserId(userId);
        chatGroup.setCompanyId(companyId);
        return chatGroupRepository.save(chatGroup);
    }
    
    public List<ChatGroup> findByCompanyId(Integer companyId) {
        return chatGroupRepository.findByCompanyId(companyId);
    }

    public List<ChatGroup> findByUserId(Integer userId) {
        return chatGroupRepository.findByUserId(userId);
    }
    
    public ChatGroup findById(Integer id) {
        return chatGroupRepository.findById(id).orElse(null);
    }
}
