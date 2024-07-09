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
import com.example.model.CompanyUpdateQuery;
import com.example.service.ChatGroupService;
import com.example.service.CompanyService;
import com.example.service.LoginDataPrincipal;
import com.example.service.UserService;

@Controller
public class CompanyDetailController {

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private ChatGroupService chatGroupService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/companyDetail")
    public String companyDetails(@AuthenticationPrincipal LoginDataPrincipal company, Model model) {
        if (company != null) {
            Companies companyDetails = companyService.findByEmail(company.getEmail());
            if (companyDetails != null) {
                model.addAttribute("company", companyDetails);
                CompanyUpdateQuery cuq = companyService.getCompanyDto(companyDetails);
                model.addAttribute("companyUpdateQuery", cuq);

                // チャットリストを取得してモデルに追加
                List<ChatGroup> ongoingChats = chatGroupService.findByCompanyId(companyDetails.getId());
                List<UserChatInfo> userChatInfos = ongoingChats.stream()
                        .map(chatGroup -> {
                            Users user = userService.findById(chatGroup.getUserId());
                            return new UserChatInfo(user.getId(), user.getLastName() + " " + user.getFirstName());
                        })
                        .collect(Collectors.toList());
                model.addAttribute("ongoingChats", userChatInfos);
            } else {
                model.addAttribute("company", new Companies());
            }
        } else {
            return "redirect:/login"; // 企業が認証されていない場合はログインページにリダイレクト
        }
        return "companyDetail";
    }
    
    @PostMapping("/companyDetail/update")
    public String updateCompany(@ModelAttribute CompanyUpdateQuery companyUpdateQuery) {
        companyService.updateCompany(companyUpdateQuery);
        return "redirect:/companyDetail";
    }
    
    public static class UserChatInfo {
        private Integer userId;
        private String userName;

        public UserChatInfo(Integer userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }

        public Integer getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }
    }
}
