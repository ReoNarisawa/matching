package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Users;
import com.example.model.UserUpdateQuery;
import com.example.repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
    
    public List<Users> findByLastNameAndFirstName(String lastName, String firstName) {
    	return usersRepository.findByLastNameAndFirstName(lastName, firstName);
    }
    
    public Users findById(Integer id) {
        return usersRepository.findById(id.longValue()).orElse(null);
    }
    
    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users user = usersRepository.findByEmail(userDetails.getUsername());
            if (user != null) {
                return user.getId();
            }
        }
        return null;
    }

    public UserUpdateQuery getUserDto(Users user) {
        UserUpdateQuery uuq = new UserUpdateQuery();
        uuq.setUserId(user.getId());
        uuq.setLastName(user.getLastName());
        uuq.setFirstName(user.getFirstName());
        uuq.setBirth(user.getBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").format(user.getBirth()) : null);
        uuq.setSex(user.getSex());
        uuq.setTel(user.getTel());
        uuq.setEmail(user.getEmail());
        uuq.setAddress(user.getAddress());
        uuq.setJobtype(user.getJobtype());
        uuq.setUserRegistered(user.getUserRegistered() != null ? new SimpleDateFormat("yyyy-MM-dd").format(user.getUserRegistered()) : null);
        return uuq;
    }

    @Transactional
    public void updateUser(UserUpdateQuery uuq) {
        Users user = usersRepository.findByEmail(uuq.getEmail());
        if (user != null) {
            user.setLastName(uuq.getLastName());
            user.setFirstName(uuq.getFirstName());
            try {
                user.setBirth(uuq.getBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(uuq.getBirth()) : null);
                user.setUserRegistered(uuq.getUserRegistered() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(uuq.getUserRegistered()) : null);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setSex(uuq.getSex());
            user.setTel(uuq.getTel());
            user.setEmail(uuq.getEmail());
            user.setAddress(uuq.getAddress());
            user.setJobtype(uuq.getJobtype());
            usersRepository.save(user);
        }
    }
    
    public String findEmailById(Integer id) {
        Users user = usersRepository.findById(id.longValue()).orElse(null);
        return (user != null) ? user.getEmail() : null;
    }
}