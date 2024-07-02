package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.LoginData;
import com.example.entity.LoginDataDto;
import com.example.service.LoginDataService;

@Controller
public class RegisterController {
	
	@Autowired
    private LoginDataService loginDataService;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new LoginDataDto());
		return "register"; 
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") LoginDataDto dataDto, Model model, RedirectAttributes redirectAttributes) {
		LoginData existing = loginDataService.findByEmail(dataDto.getEmail()); // メールで既存のユーザーを検索
		if(existing != null) {
			// ユーザーが既に存在する場合
			model.addAttribute("emailExists", true);
            return "register"; // ユーザーが存在するため、再度登録画面を表示
		}
		loginDataService.save(dataDto); //　ユーザーが存在しない場合は、新しいユーザーを保存
		redirectAttributes.addFlashAttribute("message", "登録が完了しました"); // 成功メッセージをフラッシュ属性として追加
		return "redirect:/login"; //登録が成功した場合はログイン画面を表示
	}
}
