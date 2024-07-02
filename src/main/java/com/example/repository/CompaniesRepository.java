package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Companies;

public interface CompaniesRepository extends JpaRepository<Companies, Long> {
	Companies findByEmail(String email); // メールで企業を検索するメソッド
}