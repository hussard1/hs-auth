package com.hussard.hsauth.domain.service;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findByAuthorities(Set<Authority> authorities) {
        return userRepository.findByAuthoritiesIn(authorities).collect(Collectors.toList());
    }
}
