package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.LoginData;

//JpaRepositoryを拡張して、LoginDataオブジェクトとそれらのIDとしてLong型を扱えるようにする。
public interface LoginDataRepository extends JpaRepository<LoginData, Long> {
	// emailでユーザーを探す。
	LoginData findByEmail(String email);
}