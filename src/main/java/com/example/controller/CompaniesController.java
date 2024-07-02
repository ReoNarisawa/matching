/**
 * userIndex画面で企業をカード形式で表示するために使用
 */

package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.repository.CompaniesRepository;

@Controller
public class CompaniesController {

    @Autowired
    private CompaniesRepository companiesRepo;

    @GetMapping("/userIndex")
    public String userIndex(Model model) {
        model.addAttribute("companies", companiesRepo.findAll());
        return "userIndex";
    }
}