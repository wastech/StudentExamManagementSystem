package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.AppRole;
import com.example.StudentExamManagementSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);

}
