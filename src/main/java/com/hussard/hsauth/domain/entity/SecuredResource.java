package com.hussard.hsauth.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class SecuredResource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    private String pattern;

    private String type;

    private int sortOrder;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
