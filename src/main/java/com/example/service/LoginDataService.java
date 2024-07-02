package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Companies;
import com.example.entity.LoginData;
import com.example.entity.LoginDataDto;
import com.example.entity.Users;
import com.example.repository.CompaniesRepository;
import com.example.repository.LoginDataRepository;
import com.example.repository.UsersRepository;

import jakarta.transaction.Transactional;


@Service
public class LoginDataService implements UserDetailsService{
	
	@Autowired
    private LoginDataRepository loginDataRepo;
    
    @Autowired
    private UsersRepository userRepo;
    
    @Autowired
    private CompaniesRepository companyRepo;
	
	@Autowired
	private PasswordEncoder psEncoder;
	
	@Override // UserDetailsServiceインターフェースのメソッドを上書き
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginData data = loginDataRepo.findByEmail(email);
		if(data == null) {
			throw new UsernameNotFoundException(email + "not found");
		}
		return new LoginDataPrincipal(data);
	}
	
	public LoginData findByEmail(String email) {
        return loginDataRepo.findByEmail(email); // メールでユーザーを検索し返す
    }
	
	@Transactional // トランザクションを開始。メソッドが終了したらトランザクションがコミットされる。
    public void save(LoginDataDto dataDto) {
        // LoginDataDtoからLoginDataへの変換
        LoginData data = new LoginData();
        data.setUsername(dataDto.getUsername());
        // パスワードをハッシュ化してから保存
        data.setPassword(psEncoder.encode(dataDto.getPassword()));
        data.setEmail(dataDto.getEmail());
        if (dataDto.getRole().equalsIgnoreCase("company")) {
            data.setRole(2); // 企業
        } else {
            data.setRole(3); // 一般ユーザー
        }

        // loginDataデータベースへの保存
        loginDataRepo.save(data);
        
        // IDが生成された後、companysテーブル　or　usersテーブルに情報登録
        if (data.getRole() == 2) {
            Companies company = new Companies();
            company.setEmail(data.getEmail());
            company.setCompanyRegistered(new Date());
            companyRepo.save(company);
        } else {
            Users user = new Users();
            user.setEmail(data.getEmail());
            user.setUserRegistered(new Date());
            userRepo.save(user);
        }
    }
}