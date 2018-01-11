package ua.com.social.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Role;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenAuthenticationService {

    private static String TOKEN_PREFIX;
    private static long EXPIRATION_TIME;
    private static String SECRET;

    public static String createToken(Account account, Integer profileId) {

        String token = Jwts.builder()
                .setSubject(account.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", profileId)
                .claim("roles", Collections.singletonList("USER"))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return TOKEN_PREFIX + " " + token;
    }

    public static String refreshToken(Authentication authentication) {
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", ((UserProxy) authentication.getDetails()).getId())
                .claim("roles", authentication.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return TOKEN_PREFIX + " " + token;
    }

    public static String getAccountEmailFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();
        return username;
    }

    public static int getAccountIdFromToken(String token) {
        Number id = (Number) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("id");
        return id.intValue();
    }

    public static List<Role> getAccountRolesFromToken(String token) {
        List roles = (List) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("roles");
        return roles;
    }

    @Value("${security.tokenPrefix}")
    private void setTokenPrefix(String tokenPrefix) {
        TOKEN_PREFIX = tokenPrefix;
    }

    @Value("${security.expirationTime}")
    private void setExpirationTime(long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

    @Value("${security.secret}")
    private void setSecret(String secret) {
        SECRET = secret;
    }


}