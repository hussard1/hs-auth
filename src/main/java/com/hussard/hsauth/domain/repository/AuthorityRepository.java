package com.hussard.hsauth.domain.repository;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.enums.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByAuthorityType(AuthorityType authorityType);
}
