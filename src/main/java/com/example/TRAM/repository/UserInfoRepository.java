package com.example.TRAM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.TRAM.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);  // Find a user by username
}
