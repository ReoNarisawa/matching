package com.example.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies") //"companies"テーブルに対応
public class Companies {

    @Id // 各企業を一意に識別するためのID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDは自動的に増加
    @Column(name = "id")
    private Integer id;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email", unique = true) // 同じメールアドレスの企業は存在できない
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "facilitytype")
    private String facilityType;

    @Column(name = "company_registered")
    private Date companyRegistered;

    // 以下ゲッター・セッター
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    public String getFacilityType() {
        return facilityType;
    }
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }
    public Date getCompanyRegistered() {
        return companyRegistered;
    }
    public void setCompanyRegistered(Date companyRegistered) {
        this.companyRegistered = companyRegistered;
    }
}
