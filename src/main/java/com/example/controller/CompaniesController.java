/**
 * userIndex画面で企業をカード形式で表示するために使用
 */

package com.example.controller;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Companies;
import com.example.repository.CompaniesRepository;

@Controller
public class CompaniesController {

    @Autowired
    private CompaniesRepository companiesRepo;

    @GetMapping("/userIndex")
    public String userIndex(@RequestParam(value = "search", required = false) String query,
                            @RequestParam(value = "sortField", required = false) String sortField,
                            Model model) {
        List<Companies> companies;

        if (query != null && !query.isEmpty()) {
            // 企業名で検索
            companies = companiesRepo.findByCompanyNameContaining(query);
        } else {
            // 全企業を取得
            companies = companiesRepo.findAll();
        }

        // 日本語順のソートを定義
        Collator collator = Collator.getInstance(Locale.JAPANESE);
        Comparator<Companies> comparator = Comparator.comparing(company -> {
            if ("address".equals(sortField)) {
                return company.getAddress() != null ? company.getAddress() : "";
            } else if ("companyName".equals(sortField)) {
                return company.getCompanyName() != null ? company.getCompanyName() : "";
            } else {
                return company.getId().toString(); // デフォルトはIDでソート
            }
        }, collator);

        // ソートを適用
        if ("address".equals(sortField)) {
            comparator = comparator.reversed(); // 住所は降順ソート
        }

        companies.sort(comparator);

        model.addAttribute("companies", companies);
        return "userIndex";
    }

}