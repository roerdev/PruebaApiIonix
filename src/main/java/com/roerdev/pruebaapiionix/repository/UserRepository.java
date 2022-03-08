package com.roerdev.pruebaapiionix.repository;

import com.roerdev.pruebaapiionix.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u")
    Page<UserEntity> findAll(Pageable pageable);
}
