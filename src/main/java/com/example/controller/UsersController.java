/**
 * companyIndex画面で企業をカード形式で表示するために使用
 */

package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.repository.UsersRepository;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepo;

    @GetMapping("/companyIndex")
    public String companyIndex(Model model) {
        model.addAttribute("users", usersRepo.findAll());
        return "companyIndex";
    }
}