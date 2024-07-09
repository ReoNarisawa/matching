package com.example.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.LoginData;

public class LoginDataPrincipal implements UserDetails {
	
	private LoginData data; //LoginDataオブジェクトを保持
	
	// コンストラクタでLoginDataオブジェクトを受け取り、dataにセット
	public LoginDataPrincipal(LoginData data) {
		this.data = data;
	}
	
	// ユーザーに与えられる権限を返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "USER"; // デフォルトはUSER
        if (data.getRole() == 2) {
            role = "COMPANY";
        } else if (data.getRole() == 1) {
            role = "ADMIN";
        }
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
	
	// LoginDataオブジェクトのパスワードを返す
	@Override
	public String getPassword() {
		return data.getPassword();
	}
	
	// LoginDataオブジェクトのEmailを返す
	public String getEmail() {
		return data.getEmail();
	}
	
	// アカウントが有効期限切れでないことを示すために、常にtrueを返す。
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないことを示すために、常にtrueを返す。
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格情報（ここではパスワード）が有効期限切れでないことを示すために、常にtrueを返す。
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // アカウントが有効であることを示すために、常にtrueを返す。
    @Override
    public boolean isEnabled() {
        return true;
    }

    
    //使用しないメソッド(オーバーライドで必要)
    @Override
    public String getUsername() {
        return getEmail(); // getUsername() メソッドで email を返す
    }
}