package com.sittichot.backendledger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sittichot.backendledger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPassword(String userId, String password);
}
