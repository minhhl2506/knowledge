package com.example.knowledge.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.knowledge.configuration.AuthenticationProperties;
import com.example.knowledge.configuration.TokenProperties;
import com.example.knowledge.response.TokenResponse;
import com.example.knowledge.util.DateUtils;
import com.example.knowledge.util.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {
      
    private final TokenProperties tokenProperties;
    
    private final AuthenticationProperties properties;
    
	private Key getSigningKey() {
		byte[] keyBytes;
		
		String secretKey = this.properties.getSecretKey();
		
		keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

    public TokenResponse createAccessToken(String username) {
    	
    	Map<String, Object> claims = new HashMap<>();
    	
    	return generateAccessToken(claims, username);
    }

    public TokenResponse generateAccessToken(Map<String, Object> claims, String username) {
        
    	Date expiration = DateUtils.getDateAfterSecond(new Date(), this.tokenProperties.getToken());
    	
    	String accessToken = Jwts.builder()
        				 .setClaims(claims)
        				 .setSubject(username)
                         .setExpiration(expiration)
                         .signWith(getSigningKey()).compact();
    	
    	String refreshToken = Jwts.builder()
				 .setClaims(claims)
				 .setSubject(username)
                 .setExpiration(expiration)
                 .signWith(getSigningKey()).compact();
        
        TokenResponse tokenResponse = TokenResponse.builder()
        											.accessToken(accessToken)
        											.type(SecurityConstants.TokenType.ACCESS_TOKEN)
        											.refreshToken(refreshToken)
        											.type(SecurityConstants.TokenType.REFRESH_TOKEN)
        											.duration(this.tokenProperties.getToken())
        											.build();
        
        return tokenResponse;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }
	
}
