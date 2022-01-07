package com.maitri.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/*
 * This class implement all the necessary functions for JWT token.
 */
@Component
public class JwtUtil {
		//Defines secret key and token validity of JWT token.
		private static final String SECRET_KEY = "learn_programming_yourself";
	    private static final int TOKEN_VALIDITY = 3600 * 5;
	    
	    //Extracts Username from JWT token. 
	    
	    public String getUsernameFromToken(String token) {
	        return getClaimFromToken(token, Claims::getSubject);
	    }
	    
	    //Extracts the signature from JWT token.
	    
	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }
	    
	    //Extracts all the payload from JWT token.
	    
	    private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	    }
	    
	    //Validate the JWT token.

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    //Checks weather token is expired or not.
	    
	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }
	    
	    //Extract expiration date from token.

	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    //With all the given values,applies algorithm(HS512) on it,and generates JWT token.
	    
	    public String generateToken(UserDetails userDetails) {
	    	Map<String, Object> claims = new HashMap<>();
	    	return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
	                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	                .compact();
	    }
	}
