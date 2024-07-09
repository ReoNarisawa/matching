package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import com.example.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email); // メールでユーザーを検索
    List<Users> findByLastNameAndFirstName(String lastName, String firstName); // 姓と名でユーザーを検索する
    
    // ユーザー名で検索する
    List<Users> findByLastNameContainingOrFirstNameContaining(String lastName, String firstName);
    // ソート時
    List<Users> findByLastNameContainingOrFirstNameContaining(String lastName, String firstName, Sort sort);
    List<Users> findAll(Sort sort); // ソートを適用して全ユーザーを取得する
}