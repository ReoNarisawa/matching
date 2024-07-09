package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.ChatGroup;

public interface ChatGroupRepository extends JpaRepository<ChatGroup, Integer> {
    Optional<ChatGroup> findByUserIdAndCompanyId(Integer userId, Integer companyId);
    
    List<ChatGroup> findByCompanyId(Integer companyId);
    List<ChatGroup> findByUserId(Integer userId);
}