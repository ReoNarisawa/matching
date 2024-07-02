package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_data") //"login_data"テーブルに対応
public class LoginData {
	
	@Id //各ユーザーを一意に識別するためのID
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDは自動的に増加
	@Column(name = "id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email", unique = true) //同じメールアドレスのユーザーは存在出来ない
	private String email;
	@Column(name = "role")
	private int role;
	
	
	//以下ゲッター・セッター
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
}
