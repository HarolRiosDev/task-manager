package com.hrv.taskmanager.repository;

import com.hrv.taskmanager.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndName(String email, String name);
}
