package com.bridgelabz.bookstore.utility;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class Tokenutility {

/**
* purpose: this method will take an userId as input and generate a token using that userId.
* @param userId of user
* @return token generated
*/
public String createToken(Long userId) {
String token = Jwts.builder().setSubject(String.valueOf(userId)).signWith(SignatureAlgorithm.HS256, "userId")
.compact();
System.out.println(token);
return token;
}
/**
* purpose: this method will take token as input,parse it and returns an userId got after parsing
* @param token
* @return userId got after parsing
*/
public long getUserIdFromToken(String token) {
	System.out.println("11");
Claims claim = Jwts.parser().setSigningKey("userId").parseClaimsJws(token).getBody();
System.out.println("jwt11");
String userIdString = claim.getSubject();
long userId = Long.parseLong(userIdString);
System.out.println(userId);
return userId;
}

}