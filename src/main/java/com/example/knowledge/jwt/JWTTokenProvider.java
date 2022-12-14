package com.example.knowledge.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.knowledge.configuration.AuthenticationProperties;
import com.example.knowledge.configuration.TokenProperties;
import com.example.knowledge.service.TokenService;
import com.example.knowledge.util.DateUtils;
import com.example.knowledge.util.SecurityConstants;
import com.example.knowledge.util.Validator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

	private final TokenProperties tokenProperties;

	private final AuthenticationProperties properties;

	private final TokenService tokenService;

	private Key getSigningKey() {
		byte[] keyBytes;

		String secretKey = this.properties.getSecretKey();

		keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String createAccessToken(String username) {

		String accessToken = this.createAccessToken(username, SecurityConstants.TokenType.ACCESS_TOKEN);

		return accessToken;
	}

	public String createAccessToken(String username, String tokenType) {

		Map<String, Object> claims = new HashMap<>();

		claims.put(SecurityConstants.TokenType.TOKEN, tokenType);

		return this.createAccessToken(claims, username);
	}

	public String createAccessToken(Map<String, Object> claims, String username) {

		Date expiration = DateUtils.getDateAfterSecond(new Date(), this.tokenProperties.getToken());

		String accessToken = Jwts.builder().addClaims(claims).setSubject(username).setIssuedAt(new Date())
				.setExpiration(expiration).signWith(getSigningKey()).compact();

		return accessToken;
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);

		return expiration.before(new Date());
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

	public String createRefreshToken(String username) {
		String refreshToken = this.createRefreshToken(username, SecurityConstants.TokenType.REFRESH_TOKEN);

		this.tokenService.saveRefreshToken(username, refreshToken);

		return refreshToken;
	}

	private String createRefreshToken(String username, String tokenType) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(SecurityConstants.TokenType.TOKEN, tokenType);

		return this.createRefreshToken(username, claims);
	}

	private String createRefreshToken(String username, Map<String, Object> claims) {
		String refreshToken = Jwts.builder().addClaims(claims).setSubject(username).setIssuedAt(new Date())
				.signWith(getSigningKey()).compact();

		return refreshToken;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		
		return (Validator.equals(userDetails.getUsername(), username) && !this.isTokenExpired(token));
	}
}
