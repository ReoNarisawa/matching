package com.example.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") //"users"テーブルに対応
public class Users {
	
	@Id //各ユーザーを一意に識別するためのID
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDは自動的に増加
	@Column(name = "id")
	private Integer id;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "birth")
	private Date birth;
	@Column(name = "sex")
	private String sex;
	@Column(name = "tel")
	private String tel;
	@Column(name = "email", unique = true) //同じメールアドレスのユーザーは存在出来ない
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "jobtype")
	private String jobtype;
	@Column(name = "user_registered")
	private Date userRegistered;
	
	
	// 以下ゲッター・セッター
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public Date getUserRegistered() {
		return userRegistered;
	}
	public void setUserRegistered(Date userRegistered) {
		this.userRegistered = userRegistered;
	}
}
