package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Companies;

public interface CompaniesRepository extends JpaRepository<Companies, Long> {
	Companies findByEmail(String email); // メールで企業を検索するクエリ
	Companies findByCompanyName(String companyName); // 企業名で企業を検索するクエリ
	
	// 企業名で検索する時のクエリ
	List<Companies> findByCompanyNameContaining(String query);
	// ソート時
    List<Companies> findByCompanyNameContaining(String query, Sort sort);
    List<Companies> findAll(Sort sort); // ソートを適用して全企業を取得する
}