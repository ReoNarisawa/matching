package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Companies;
import com.example.model.CompanyUpdateQuery;
import com.example.repository.CompaniesRepository;

@Service
public class CompanyService {

    @Autowired
    private CompaniesRepository companiesRepository;

    public Companies findByEmail(String email) {
        return companiesRepository.findByEmail(email);
    }
    
    public Companies findByCompanyName(String companyName) {
        return companiesRepository.findByCompanyName(companyName);
    }
    
    public Companies findById(Integer id) {
        return companiesRepository.findById(id.longValue()).orElse(null);
    }
    
    public Integer getCurrentCompanyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Companies company = companiesRepository.findByEmail(userDetails.getUsername());
            if (company != null) {
                return company.getId();
            }
        }
        return null;
    }

    public CompanyUpdateQuery getCompanyDto(Companies company) {
        CompanyUpdateQuery cuq = new CompanyUpdateQuery();
        cuq.setCompanyId(company.getId());
        cuq.setCompanyName(company.getCompanyName());
        cuq.setTel(company.getTel());
        cuq.setEmail(company.getEmail());
        cuq.setAddress(company.getAddress());
        cuq.setFacilityType(company.getFacilityType());
        // String型で受け取った値をDate型に変換
        cuq.setCompanyRegistered(company.getCompanyRegistered() != null ? new SimpleDateFormat("yyyy-MM-dd").format(company.getCompanyRegistered()) : null);
        return cuq;
    }

    @Transactional
    public void updateCompany(CompanyUpdateQuery cuq) {
        Companies company = companiesRepository.findByEmail(cuq.getEmail());
        if (company != null) {
            company.setCompanyName(cuq.getCompanyName());
            company.setTel(cuq.getTel());
            company.setEmail(cuq.getEmail());
            company.setAddress(cuq.getAddress());
            company.setFacilityType(cuq.getFacilityType());
            try {
                company.setCompanyRegistered(cuq.getCompanyRegistered() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(cuq.getCompanyRegistered()) : null);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            companiesRepository.save(company);
        }
    }
    
    public String findEmailById(Integer id) {
        Companies company = companiesRepository.findById(id.longValue()).orElse(null);
        return (company != null) ? company.getEmail() : null;
    }
}