package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.LoginUser;
import com.example.entity.LoginUserDto;
import com.example.service.UserService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

    // Spring が自動的に UserService の実装を注入
    @Autowired
    private UserService userService;

    @GetMapping("/register") // "/register"というURLに対するGETリクエストを処理
    public ModelAndView registerForm() {
        ModelAndView mav = new ModelAndView(); // ModelAndViewオブジェクトを作成
        mav.addObject("user", new LoginUserDto()); // 新しいUserDtoオブジェクトを"ユーザー"という名前で追加
        mav.setViewName("register"); // 表示するビュー（HTMLファイル）の名前を"register"に設定
        return mav; // ModelAndViewオブジェクトを返す
    }

    @PostMapping("/register") // "/register"というURLに対するPOSTリクエストを処理
    public String register(@Valid @ModelAttribute("user") LoginUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        LoginUser existing = userService.findByUsername(userDto.getUsername()); // ユーザー名で既存のユーザーを検索
        if(existing != null){
            // ユーザーが既に存在する場合の処理
            return "register"; // ユーザーが存在するため、再度登録画面を表示
        }
        userService.save(userDto); // ユーザーが存在しない場合、新しいユーザーを保存
        return "login"; // 登録が成功した場合、ログイン画面を表示
    }
}