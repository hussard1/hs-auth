package com.hussard.hsauth.domain.repository;

import com.hussard.hsauth.domain.entity.SecuredResource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecuredResourceRepository extends JpaRepository<SecuredResource, Long> {
}
