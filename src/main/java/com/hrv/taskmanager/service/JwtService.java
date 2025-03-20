package com.hrv.taskmanager.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ott.InvalidOneTimeTokenException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Service class for JWT related operations
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 24 horas

    /**
     * Method to get the signing key
     * @return the signing key
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Method to generate a JWT token
     * @param email the email to generate the token for
     * @return the generated token
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Method to validate a JWT token
     * @param token the token to validate
     * @param email the email to validate the token for
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    /**
     * Method to validate and verify a JWT token
     * @param token the token to validate and verify
     */
    public void validateAndVerifyToken(String token) {
        if(isTokenExpired(token)){
            throw new InvalidOneTimeTokenException("Token expired");
        }
        if (!isTokenValid(token)) {
            throw new InvalidOneTimeTokenException("Invalid token");
        }
    }

    /**
     * Method to extract the email from a JWT token
     * @param token the token to extract the email from
     * @return the extracted email
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    /**
     * Method to get the raw token from a JWT token
     * @param token the token to get the raw token from
     * @return the raw token
     */
    public String getRawToken(String token) {
        return token.substring(7);
    }

    /**
     * Method to extract the expiration date from a JWT token
     * @param token the token to extract the expiration date from
     * @return the extracted expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Method to check if a JWT token is expired
     * @param token the token to check
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Method to extract a claim from a JWT token
     * @param token the token to extract the claim from
     * @param claimsResolver the function to resolve the claim
     * @param <T> the type of the claim
     * @return the extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Jws<Claims> claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
        return claimsResolver.apply(claims.getPayload());
    }

    /**
     * Method to check if a JWT token is valid
     * @param token the token to check
     * @return true if the token is valid, false otherwise
     */
    private boolean isTokenValid(String token) {
        try {
                Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

