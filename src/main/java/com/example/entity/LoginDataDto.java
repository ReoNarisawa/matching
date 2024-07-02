package com.example.entity;


//入力チェックをするためのツールをインポート
import jakarta.validation.constraints.NotEmpty;

public class LoginDataDto {
	@NotEmpty //ユーザー名は空であってはならない
	private String username;
	
	@NotEmpty //パスワードは空であってはならない
	private String password;
	
	@NotEmpty //メールアドレスは空であってはならない
	private String email;

	@NotEmpty //roleは空であってはならない
	private String role;
	
	//以下ゲッター・セッター
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}