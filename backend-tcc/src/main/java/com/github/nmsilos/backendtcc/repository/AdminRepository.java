package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    UserDetails findByUsername(String username);
}
