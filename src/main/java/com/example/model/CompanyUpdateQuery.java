package com.example.model;

public class CompanyUpdateQuery {
    private Integer companyId;
    private String companyName;
    private String tel;
    private String email;
    private String address;
    private String facilityType;
    private String companyRegistered;
    
    // Getters and Setters
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getCompanyRegistered() {
        return companyRegistered;
    }

    public void setCompanyRegistered(String companyRegistered) {
        this.companyRegistered = companyRegistered;
    }
}
