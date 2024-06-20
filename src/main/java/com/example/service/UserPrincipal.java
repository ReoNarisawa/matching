package com.example.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.LoginUser;

//UserDetailsインターフェースを実装したUserPrincipalというクラスを作成。Spring Securityでユーザー情報を扱うためのクラス。
public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L; // serialVersionUIDを追加

	private LoginUser user;  // Userオブジェクトを保持。
	// コンストラクタでLoginUserオブジェクトを受け取り、それをこのクラスのuserにセット。
	public UserPrincipal(LoginUser user) {
		this.user = user;
	}
	
	// ユーザーに与えられる権限を返す。ここでは全てのユーザーに"ROLE_USER"という権限付与。
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	// Userオブジェクトのパスワードを返す。
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	// Userオブジェクトのユーザー名を返す。
	@Override
	public String getUsername() {
		return user.getUsername();
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
}