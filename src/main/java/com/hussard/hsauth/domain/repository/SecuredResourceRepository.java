package com.hussard.hsauth.domain.repository;

import com.hussard.hsauth.domain.entity.SecuredResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuredResourceRepository extends JpaRepository<SecuredResource, Long> {
}
