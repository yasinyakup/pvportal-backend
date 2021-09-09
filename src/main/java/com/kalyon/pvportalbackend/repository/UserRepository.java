package com.kalyon.pvportalbackend.repository;

import com.kalyon.pvportalbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String username);
}
