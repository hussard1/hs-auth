package com.hussard.hsauth.domain.repository;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Stream<User> findByAuthoritiesIn(Set<Authority> authorities);
}
