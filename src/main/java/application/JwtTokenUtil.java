package application;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Data
@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -3301605591108950415L;
	
	public final static String USER_ID = "userId";
	public final static String FIRST_NAME = "firstName";
	public final static String LAST_NAME = "lastName";
	public final static String EMAIL = "email";
	public final static String PHONE = "phone";
	public static final String ACCESS_TOKEN = "accessToken";
	public static final String REFRESH_TOKEN = "refreshToken";
	public static final String BEARER_PREFIX = "Bearer ";
	public static final String USER_TYPE = "userType";
	
	private String secret = "mySecretIAmGroot";
	
	@Value("${accessToken.expireTime}")
	private Long tokenExpMillis;

	@Value("${refreshToken.expireTime}")
	private Long refreshTokenExpMillis;
	
	public String generateToken(JwtUser user) {
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put(USER_ID, user.getUserId());
		claims.put(USER_TYPE, user.getUserType());
		claims.put(EMAIL, user.getEmail());
		//TODO add parameter refresh, THEN
		//If refresh, put access token too
		/*
		if (refresh) {
			claims.put(ACCESS_TOKEN, user.getToken());
		}
		*/
		long nowMillis = System.currentTimeMillis();
		//long expMillis = nowMillis + (refresh ? refreshTokenExpMillis * 60 * 1000 : tokenExpMillis * 60 * 1000);
		long expMillis = nowMillis + tokenExpMillis * 60 * 1000;
		Date exp = new Date(expMillis);
		claims.setExpiration(exp);
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public Boolean validateToken(String token, JwtUser jwtUser) {
		final String email = extractEmail(token);
		return (email.equals(jwtUser.getEmail()) && !isTokenExpired(token));
	}
	/*
	public String generateToken(AuthUserDto user, boolean refresh) {
		long nowMillis = System.currentTimeMillis();
		Claims claims = Jwts.claims().setSubject(user.getLogin());
		claims.put(CLIENT_ID, user.getClientId());
		claims.put(FIRST_NAME, user.getFirstName());
		claims.put(LAST_NAME, user.getLastName());
		claims.put(EMAIL, user.getLogin());
		claims.put(PHONE, user.getPhone());
		if (refresh) {
			claims.put(ACCESS_TOKEN, user.getToken());
		}
		long expMillis = nowMillis + (refresh ? refreshTokenExpMillis * 60 * 1000 : tokenExpMillis * 60 * 1000);
		Date exp = new Date(expMillis);
		claims.setExpiration(exp);
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	*/
}
