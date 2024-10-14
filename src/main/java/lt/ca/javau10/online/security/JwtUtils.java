package lt.ca.javau10.online.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.services.CustomerService;


@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  
  @Autowired
  CustomerService customerService;

  @Value("${javau10.app.jwtSecret}")
  private String jwtSecret;

  @Value("${javau10.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  
  public void setSecretKey(String key) {
	  jwtSecret = key;
  }
  
  public void setExpirationMs(int ms) {
	  jwtExpirationMs = ms;
  }
  

  
    public String generateJwtToken(Authentication authentication) {
        CustomerDto customerPrincipal = (CustomerDto) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(customerPrincipal.getUsername())  // Username as subject
                .setIssuedAt(new Date())  // Issue date
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))  // Expiration date
                .signWith(key() , SignatureAlgorithm.HS256)  // Correct: key + SignatureAlgorithm
                .compact();
      }

      public Key toKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
      }

      private Key key() {
        return toKey(jwtSecret);  // Converts secret to HMAC key
      }

      public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())  // Validate using the signing key
                .build()
                .parseClaimsJws(token)  // Parse the claims
                .getBody()
                .getSubject();  // Extract the username (subject)
      }
     

      public boolean validateJwtToken(String authToken) {
        try {
          logger.debug("Trying to validate JWT token");
          Jwts.parserBuilder()
              .setSigningKey(key())  // Validate using the signing key
              .build()
              .parseClaimsJws(authToken);  // Parse and validate the token
          logger.debug("JWT token is valid: \n" + authToken);
          return true;
        } catch (MalformedJwtException e) {
          logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
          logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
          logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
          logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
      }

      public Authentication getAuthentication(String token) {
        logger.trace("The method worked getAuthentication");

        String username = getUserNameFromJwtToken(token);
        UserDetails userDetails = customerService.loadUserByUsername(username);  // Load user from DB/service
        logger.debug("UserDetails loaded: " + userDetails.getUsername() + ", Roles: " + userDetails.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());  // Create authentication object
      }
  
}