package com.hussard.hsauth.config.security.jwt;

import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.enums.AuthorityType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    @Value("${spring.jwt.tokenValidMilSec}")
    private long tokenValidMilSec;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authority", StringUtils.collectionToCommaDelimitedString(user.getAuthorities()));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilSec))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
    }

    // Jwt ???????????? ?????? ????????? ??????
    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(this.getUsername(token), "", this.getAuthorities(token));
    }

    // Jwt ???????????? ?????? ?????? ?????? ??????
    public String getUsername(String token) {
        return this.getClaims(token).getBody().getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        String authorities = this.getClaims(token).getBody().get("authority", String.class);
        return StringUtils.commaDelimitedListToSet(authorities).stream()
                .map(AuthorityType::of)
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toSet());
    }

    // Request??? Header?????? token ?????? : "X-AUTH-TOKEN: jwt??????"
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }

    // Jwt ????????? ????????? + ???????????? ??????
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}