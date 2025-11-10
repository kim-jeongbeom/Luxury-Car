package com.example.autoversebackend.user.repository;

import com.example.autoversebackend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByNickname(String nickname);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
}
