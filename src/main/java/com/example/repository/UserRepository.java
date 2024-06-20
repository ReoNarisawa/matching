package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.LoginUser;

@Repository
//UserRepositoryインターフェース、JpaRepositoryを拡張して、UserオブジェクトとそれらのIDとしてLong型を扱えるようにする
public interface UserRepository extends JpaRepository<LoginUser, Long> {

	// ユーザー名でユーザーを探すメソッド。ユーザー名をパラメータとして渡すと、そのユーザーをデータベースから探して返す。
    LoginUser findByUsername(String username);
}