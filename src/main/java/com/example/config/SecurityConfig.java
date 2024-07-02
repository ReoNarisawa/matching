package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity //websecurityの有効化
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
                    .requestMatchers("/login", "/register", "/forgot", "/js/**", "css/**").permitAll() // リクエストは認証なしで許可
                    .requestMatchers("/userDetail/**", "/companyDetail/**").authenticated()
                    .anyRequest().authenticated() // それ以外の全てのリクエストは認証が必要
            )
            .formLogin(formLogin ->  // フォームベースのログインを設定
                formLogin
                    .loginPage("/login") // ログインページのURLを設定
                    .loginProcessingUrl("/login")
                    .successHandler(authenticationSuccessHandler()) // カスタム成功ハンドラーの追加
                    .permitAll() // ログインページは認証なしで許可
            )
            .logout(logout ->  // ログアウトを設定
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout")
                .permitAll()
                .invalidateHttpSession(true)
            );

        return http.build(); // 上記の設定を反映してHttpSecurityオブジェクトをビルド
    }
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}