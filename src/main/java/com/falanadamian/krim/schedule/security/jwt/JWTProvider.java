package com.falanadamian.krim.schedule.security.jwt;

import com.falanadamian.krim.schedule.security.ModelUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "com.falana.krim")
public class JWTProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTProvider.class);

    private Key key;
    private static final String ROLES_KEY = "roles";
    private Long jwtExpirationTime = 3600L;
    private Long jwtRememberMeExpirationTime = 604800L;

    public JWTProvider() {
    }

    @PostConstruct
    public void initialize() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateJWT(Authentication authentication, boolean rememberMe) {
        ModelUserDetails user = (ModelUserDetails) authentication.getPrincipal();
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        Date expirationDate;
        Long currentDate = (new Date()).getTime();
        if (rememberMe)
            expirationDate = new Date(currentDate + 1000 * this.jwtRememberMeExpirationTime);
        else
            expirationDate = new Date(currentDate + 1000 * this.jwtExpirationTime);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim(ROLES_KEY, roles)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateJWT(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.warn("Invalid JWT signature, message: {}", e);
        } catch (ExpiredJwtException e) {
            LOGGER.warn("Expired JWT, message: {}", e);
        } catch (MalformedJwtException e) {
            LOGGER.warn("Invalid JWT, message: {}", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.warn("Unsupported JWT, message: {}", e);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("JWT illegal exception, message: {}", e);
        }
        return false;
    }

    public Authentication getAuthentication(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();

        Collection<? extends GrantedAuthority> roles =
                Arrays.stream(claims.get(ROLES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", roles);

        return new UsernamePasswordAuthenticationToken(user, jwt, roles);
    }


}
