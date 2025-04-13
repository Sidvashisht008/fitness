package com.fitworld.fitness.config.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "your-very-secure-and-long-secret-key";

	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token); // throws if invalid

        return true; // Token is valid

		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	
	public String extractUsername(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject(); // this is the email or username
    }
	
	public String generateToken(String emailId) {
		return Jwts.builder().setSubject(emailId).signWith(key).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)).compact();
	}
}
