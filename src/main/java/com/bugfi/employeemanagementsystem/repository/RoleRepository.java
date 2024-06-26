package com.bugfi.employeemanagementsystem.repository;

import com.bugfi.employeemanagementsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Override
    Optional<Role> findById(Long id);

    @Override
    Role save(Role role);
}
