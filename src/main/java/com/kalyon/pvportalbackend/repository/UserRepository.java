package com.kalyon.pvportalbackend.repository;

import com.kalyon.pvportalbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUserName(String username);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
