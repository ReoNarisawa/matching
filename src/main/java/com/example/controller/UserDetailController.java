package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Users;
import com.example.model.UserUpdateQuery;
import com.example.service.LoginDataPrincipal;
import com.example.service.UserService;

@Controller
public class UserDetailController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/userDetail")
    public String userDetails(@AuthenticationPrincipal LoginDataPrincipal user, Model model) {
        if (user != null) {
        	Users userDetails = userService.findByEmail(user.getEmail());
            if (userDetails != null) {
                model.addAttribute("user", userDetails);
                UserUpdateQuery uuq = userService.getUserDto(userDetails);
                model.addAttribute("userUpdateQuery", uuq);
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

}