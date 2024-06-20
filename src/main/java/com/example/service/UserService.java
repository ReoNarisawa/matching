package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.LoginUser;
import com.example.entity.LoginUserDto;
import com.example.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
	
    @Autowired // Springが自動的にUserRepositoryの実装を注入
    private UserRepository userRepository;

    @Autowired // Springが自動的にPasswordEncoderの実装を注入
    private PasswordEncoder passwordEncoder;
    
    @Override // UserDetailsServiceインターフェースのメソッドを上書き
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = userRepository.findByUsername(username); // ユーザー名でユーザーを検索
        if (user == null) {
            throw new UsernameNotFoundException("User not found"); // ユーザーが見つからない場合、例外をスロー
        }
        return new UserPrincipal(user); // ユーザーが見つかった場合、UserPrincipalを作成し返す
    }

    //新たにメソッドを追加
    public LoginUser findByUsername(String username) {
        return userRepository.findByUsername(username); // ユーザー名でユーザーを検索し返す
    }

    @Transactional // トランザクションを開始します。メソッドが終了したらトランザクションがコミットされる。
    public void save(LoginUserDto userDto) {
        // UserDtoからUserへの変換
        LoginUser user = new LoginUser();
        user.setUsername(userDto.getUsername());
        // パスワードをハッシュ化してから保存
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        // データベースへの保存
        userRepository.save(user); // UserRepositoryを使ってユーザーをデータベースに保存
    }   
}