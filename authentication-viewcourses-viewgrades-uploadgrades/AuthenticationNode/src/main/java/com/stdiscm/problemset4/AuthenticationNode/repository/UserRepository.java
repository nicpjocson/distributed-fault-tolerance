package com.stdiscm.problemset4.AuthenticationNode.repository;

import com.stdiscm.problemset4.AuthenticationNode.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
