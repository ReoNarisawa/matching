/**
 * companyIndex画面でユーザーをカード形式で表示するために使用
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

import com.example.entity.Users;
import com.example.repository.UsersRepository;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepo;

    @GetMapping("/companyIndex")
    public String companyIndex(@RequestParam(value = "search", required = false) String query,
                               @RequestParam(value = "sortField", required = false) String sortField,
                               Model model) {
        List<Users> users;

        if (query != null && !query.isEmpty()) {
            // ユーザー名で検索
            users = usersRepo.findByLastNameContainingOrFirstNameContaining(query, query);
        } else {
            // 全ユーザーを取得
            users = usersRepo.findAll();
        }

        // 日本語順のソートを定義
        Collator collator = Collator.getInstance(Locale.JAPANESE);
        Comparator<Users> comparator = Comparator.comparing(user -> {
            if ("address".equals(sortField)) {
                return user.getAddress() != null ? user.getAddress() : "";
            } else if ("firstName".equals(sortField)) {
                return user.getFirstName() != null ? user.getFirstName() : "";
            } else if ("lastName".equals(sortField)) {
                return user.getLastName() != null ? user.getLastName() : "";
            } else {
                return user.getId().toString(); // デフォルトはIDでソート
            }
        }, collator);

        // ソートを適用
        if ("address".equals(sortField)) {
            comparator = comparator.reversed(); // 住所は降順ソート
        }

        users.sort(comparator);

        model.addAttribute("users", users);
        return "companyIndex";
    }

}