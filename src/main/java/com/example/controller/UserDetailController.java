package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.ChatGroup;
import com.example.entity.Companies;
import com.example.entity.Users;
import com.example.model.UserUpdateQuery;
import com.example.service.ChatGroupService;
import com.example.service.CompanyService;
import com.example.service.LoginDataPrincipal;
import com.example.service.UserService;

@Controller
public class UserDetailController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ChatGroupService chatGroupService;
    
    @Autowired
    private CompanyService companyService;

    @GetMapping("/userDetail")
    public String userDetails(@AuthenticationPrincipal LoginDataPrincipal user, Model model) {
        if (user != null) {
            Users userDetails = userService.findByEmail(user.getEmail());
            if (userDetails != null) {
                model.addAttribute("user", userDetails);
                UserUpdateQuery uuq = userService.getUserDto(userDetails);
                model.addAttribute("userUpdateQuery", uuq);

                // チャットリストを取得してモデルに追加
                List<ChatGroup> ongoingChats = chatGroupService.findByUserId(userDetails.getId());
                List<CompanyChatInfo> companyChatInfos = ongoingChats.stream()
                        .map(chatGroup -> {
                            Companies company = companyService.findById(chatGroup.getCompanyId());
                            return new CompanyChatInfo(company.getId(), company.getCompanyName());
                        })
                        .collect(Collectors.toList());
                model.addAttribute("ongoingChats", companyChatInfos);
            } else {
                model.addAttribute("user", new Users());
            }
        } else {
            return "redirect:/login"; // ユーザーが認証されていない場合はログインページにリダイレクト
        }
        return "userDetail";
    }
    
    @PostMapping("/userDetail/update")
    public String updateUser(@ModelAttribute UserUpdateQuery userUpdateQuery) {
        userService.updateUser(userUpdateQuery);
        return "redirect:/userDetail";
    }
    
    public static class CompanyChatInfo {
        private Integer companyId;
        private String companyName;

        public CompanyChatInfo(Integer companyId, String companyName) {
            this.companyId = companyId;
            this.companyName = companyName;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public String getCompanyName() {
            return companyName;
        }
    }
}
