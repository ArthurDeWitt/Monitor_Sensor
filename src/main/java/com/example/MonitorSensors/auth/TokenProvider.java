package com.example.MonitorSensors.auth;

import com.example.MonitorSensors.entity.Role;
import com.example.MonitorSensors.entity.User;
import com.example.MonitorSensors.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public Optional<Authentication> getAuthentication(String token, HttpServletRequest request) {

        SecretKey key = Jwts.SIG.HS256.key().build();

        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        Long id = Long.valueOf(payload.getId());
        String username = payload.getSubject();
        String role = payload.get("role", String.class);

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(id)
                .username(username)
                .role(Role.valueOf(role))
                .build();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userPrincipal, payload, Set.of(new SimpleGrantedAuthority(String.format("ROLE_%s", role))));
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return Optional.of(usernamePasswordAuthenticationToken);
    }

    public String generateToken(User user) {
        SecretKey key = Jwts.SIG.HS256.key().build();

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, String> claims = new HashMap<>();

        claims.put("role", user.getRole().toString());

        JwtBuilder builder = Jwts.builder()
                .id(user.getId().toString())
                .issuedAt(now)
                .issuer(user.getUsername())
                .subject(user.getUsername())
                .claims(claims)
                .signWith(key);

        if (jwtProperties.getTtlMillis() > 0) {
            long expMillis = nowMillis + jwtProperties.getTtlMillis();
            Date exp = new Date(expMillis);
            builder.expiration(exp);
        }
        return builder.compact();
    }
}
