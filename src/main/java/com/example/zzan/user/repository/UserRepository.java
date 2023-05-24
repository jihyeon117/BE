package com.example.zzan.user.repository;

import com.example.zzan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String userEmail);
    Optional<User> findByUsername(String username);
}
