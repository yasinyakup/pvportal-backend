package com.kalyon.pvportalbackend.repository;

import com.kalyon.pvportalbackend.model.EnumRole;
import com.kalyon.pvportalbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(EnumRole roleUser);
}
