package com.hussard.hsauth.domain.entity;

import com.hussard.hsauth.domain.converter.HttpMethodConverter;
import lombok.Getter;
import org.springframework.http.HttpMethod;

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

    @Convert(converter = HttpMethodConverter.class)
    private Set<HttpMethod> httpMethods;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
