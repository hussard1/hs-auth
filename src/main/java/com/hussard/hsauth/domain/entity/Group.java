package com.hussard.hsauth.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String desc;

    @ManyToMany
    private Set<Authority> authorities;
}
