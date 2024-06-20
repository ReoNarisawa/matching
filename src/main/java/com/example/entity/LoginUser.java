package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * login_userテーブルとjavaの変数を紐づける
 */
@Entity  // データベースのテーブル
@Table(name = "login_user")  // このクラスが対応するテーブルの名前は "login_user" 
public class LoginUser {
	
	@Id  // 各ユーザを一意に識別するためのID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // データベースに合わせてカラム名を修正
    private Integer id;
	
	@Column(name = "username", nullable = false, unique = false)
	private String username;
	
	@Column(name = "password", nullable = false)  // "password" カラム。ユーザーのパスワードを表します
    private String password;
	
	@Column(name = "email", nullable = false, unique = true)  // "email" カラム。ユーザーのメールアドレスを表します。同じメールアドレスのユーザーは存在できない
    private String email;
	
	// 以下は各値を取得するためのメソッド（ゲッター）
    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    // 以下は各値を設定するためのメソッド（セッター）
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}