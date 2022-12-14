package com.example.knowledge.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.knowledge.configuration.AuthenticationProperties;
import com.example.knowledge.configuration.TokenProperties;
import com.example.knowledge.security.AbstractUserPrincipal;
import com.example.knowledge.service.TokenService;
import com.example.knowledge.util.DateUtils;
import com.example.knowledge.util.SecurityConstants;
import com.example.knowledge.util.Validator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider<T extends AbstractUserPrincipal> {

	private final TokenProperties tokenProperties;

	private final AuthenticationProperties properties;
	
	private final UserDetailsService userDetailsService;

	private final TokenService tokenService;
	
	private final JwtParser jwtParser;

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

		String accessToken = Jwts.builder()
								 .addClaims(claims)
								 .setSubject(username)
								 .setIssuedAt(new Date())
								 .setExpiration(expiration)
								 .signWith(getSigningKey()).compact();

		return accessToken;
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
	
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        String username = claims.getSubject();

        T principal = (T) userDetailsService.loadUserByUsername(username);

        if (Validator.isNull(principal)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
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
		String refreshToken = Jwts.builder()
								  .addClaims(claims)
								  .setSubject(username)
								  .setIssuedAt(new Date())
								  .signWith(getSigningKey())
								  .compact();

		return refreshToken;
	}

}
