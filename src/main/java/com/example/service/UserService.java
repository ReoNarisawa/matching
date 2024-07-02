package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
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
}