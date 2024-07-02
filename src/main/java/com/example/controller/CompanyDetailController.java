package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Companies;
import com.example.model.CompanyUpdateQuery;
import com.example.service.CompanyService;
import com.example.service.LoginDataPrincipal;


@Controller
public class CompanyDetailController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companyDetail")
    public String companyDetails(@AuthenticationPrincipal LoginDataPrincipal company, Model model) {
        if (company != null) {
            Companies companyDetails = companyService.findByEmail(company.getEmail());
            if (companyDetails != null) {
                model.addAttribute("company", companyDetails);
                CompanyUpdateQuery cuq = companyService.getCompanyDto(companyDetails);
                model.addAttribute("companyUpdateQuery", cuq);
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
}