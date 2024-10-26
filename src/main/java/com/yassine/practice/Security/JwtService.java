package com.yassine.practice.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;


    // is token valid :
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return !isTokenExpired(token) && username.equals(userDetails.getUsername());
    }

    // is token expired ?
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // extracting expiration :
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // extracting username :
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extracting a specific claim :
    private <T> T extractClaim
    (String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    // extracting claims :
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder().
                setSigningKey(generateSignInKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }

    // generating the token based on only on userDetails :
    private String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // generating the token :
    public String generateToken(Map<String, Object> claims,
                                 UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpiration);
    }

    // building the token :
    private String buildToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            long jwtExpiration
    ) {
        var authorities = userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                toList();
        return Jwts.builder().
                setClaims(claims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)).
                signWith(generateSignInKey()).
                claim("authorities", authorities).
                compact();
    }

    // sign in key :
    private Key generateSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
