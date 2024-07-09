package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ChatGroup;
import com.example.service.ChatGroupService;
import com.example.service.UserService;
import com.example.service.CompanyService;

@RestController
public class ChatGroupController {
    @Autowired
    private ChatGroupService chatGroupService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getChatGroupId")
    public Map<String, Integer> getChatGroupId(@RequestParam(value = "companyId", required = false) Integer companyId,
                                               @RequestParam(value = "userId", required = false) Integer userId) {
        Map<String, Integer> response = new HashMap<>();
        Integer currentUserId = userService.getCurrentUserId();
        Integer currentCompanyId = companyService.getCurrentCompanyId();

        if (currentUserId != null && companyId != null) {
            // User is logged in and companyId is provided
            ChatGroup chatGroup = chatGroupService.findOrCreateChatGroup(currentUserId, companyId);
            response.put("chatGroupId", chatGroup.getId());
        } else if (currentCompanyId != null && userId != null) {
            // Company is logged in and userId is provided
            ChatGroup chatGroup = chatGroupService.findOrCreateChatGroup(userId, currentCompanyId);
            response.put("chatGroupId", chatGroup.getId());
        } else {
            // Invalid request
            response.put("chatGroupId", -1);
        }
        return response;
    }
}