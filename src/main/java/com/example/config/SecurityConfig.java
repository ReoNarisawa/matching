package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Webセキュリティを有効にすることを示す
public class SecurityConfig {

    @Bean // このメソッドの返り値をSpringのBeanとして登録
    public PasswordEncoder passwordEncoder() { // パスワードエンコーダー（パスワードのハッシュ化）を提供するメソッド
        return new BCryptPasswordEncoder(); // パスワードをBCrypt方式でハッシュ化するエンコーダーを返す
    }

    @Bean // このメソッドの返り値をSpringのBeanとして登録
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // セキュリティフィルタチェーンを定義するメソッド
        http
        	.authorizeHttpRequests(authorizeRequests ->  // 認証リクエストを設定
                authorizeRequests
                    .requestMatchers("/login", "/register").permitAll() // "/login"と"/register"へのリクエストは認証なしで許可
                    .anyRequest().authenticated() // それ以外の全てのリクエストは認証が必要
            )
            .formLogin(formLogin ->  // フォームベースのログインを設定
                formLogin
                    .loginPage("/login") // ログインページのURLを設定
                    .permitAll() // ログインページは認証なしで許可
            )
            .logout(logout ->  // ログアウトを設定
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウトのリクエストURLを設定
            );

        return http.build(); // 上記の設定を反映してHttpSecurityオブジェクトをビルド
    }

    // 他のセキュリティ設定が必要な場合は、ここに追加
}