package com.example.entity;

//入力チェックをするためのツールをインポート
import jakarta.validation.constraints.NotEmpty;

/**
 * ユーザーによる入力データを保持するクラス
 */
public class LoginUserDto {
	@NotEmpty  // ユーザー名は空であってはならないというルール
    private String username;  // ユーザー名を保存するための場所

    @NotEmpty  // パスワードは空であってはならないというルール
    private String password;  // パスワードを保存するための場所

    @NotEmpty  // メールアドレスは空であってはならないというルール
    private String email;  // メールアドレスを保存するための場所

    // 以下は各値を取得するためのメソッド（ゲッター）です。
    public String getUsername() {
        return username;  // ユーザー名を返す
    }

    public void setUsername(String username) {
        this.username = username;  // ユーザー名を設定する
    }

    public String getPassword() {
        return password;  // パスワードを返す
    }

    public void setPassword(String password) {
        this.password = password;  // パスワードを設定する
    }

    public String getEmail() {
        return email;  // メールアドレスを返す
    }

    public void setEmail(String email) {
        this.email = email;  // メールアドレスを設定する
    }
}