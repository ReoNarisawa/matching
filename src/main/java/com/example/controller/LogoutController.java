package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class LogoutController {
	@GetMapping("/logout")
    public String logout(@RequestParam(value = "logoutMessage", required = false) String logoutMessage, RedirectAttributes redirectAttributes) {
    	redirectAttributes.addFlashAttribute("logoutMessage", "ログアウトしました");
		return "redirect:/login";
    }
}