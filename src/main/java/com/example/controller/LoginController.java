package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login") //"/login"というURLに対するGETリクエストを処理
	public String login() {
		return "login";
	}
	
	@GetMapping("/") // ルートURL ("/") に対するGETリクエストを処理
	public String redirectToIndex() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //現在のユーザー認証情報を取得
		if(auth != null && auth.isAuthenticated()) {
            String role = auth.getAuthorities().iterator().next().getAuthority();
            if ("COMPANY".equals(role)) {
                return "redirect:/Matching/companyIndex";
            } else {
                return "redirect:/Matching/userIndex";
            }
        }
        return "redirect:/Matching/login";
	}
	
	@PostMapping("/login")
    public String loginProcessing() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String role = auth.getAuthorities().iterator().next().getAuthority();
            if ("COMPANY".equals(role)) {
                return "redirect:/Matching/companyIndex";
            } else {
                return "redirect:/Matching/userIndex";
            }
        }
        return "login";
    }
}