package lt.ca.javau10.online.security;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lt.ca.javau10.online.security.jwt.AuthTokenFilter;



@Configuration
public class SecurityConfig {

	Logger logger = LogManager.getLogger(SecurityConfig.class);
	
		@Autowired
		@Qualifier("customerService")  // specific UserDetailsService is used
		UserDetailsService userDetailsService;
	
	 	@Autowired
	 	AuthenticationEntryPoint unauthorizedHandler;
	 
	
		@Bean
	 	AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
		}
	 
		@Bean
		DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
		}
	 
	 	@Bean
	 	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();	
	 	}
	
		@Bean
    	PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
		}
		
		@Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("http://localhost:3000"));
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(List.of("*"));
                return configuration;
            }))
	        
	        .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	            	.requestMatchers("/api/auth/**").permitAll()	
	            	.requestMatchers("/api/products/all").permitAll()
	                .requestMatchers("/api/products/all").hasAnyRole("USER", "ADMIN")	               
	                .requestMatchers("/api/products/**").hasAnyAuthority("ROLE_MODERATOR", "ROLE_ADMIN")
	                .requestMatchers("/api/customers/all").hasAnyAuthority("ROLE_MODERATOR", "ROLE_ADMIN")
	                .requestMatchers("/api/customers**").hasRole("ADMIN")
	                .requestMatchers("/api/customers/{id}/role").hasRole("ADMIN")
	                .requestMatchers("/api/test/*").permitAll() //.authenticated()
	                .anyRequest().authenticated()
	            
	            )
		         .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
		         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		     http.authenticationProvider(authenticationProvider());
		     http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		     return http.build();
	    }
	    
		
	       
	
	    
//	    @Bean
//		UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//		logger.info(passwordEncoder.encode("ABC"));	
//		 UserDetails user = User
//					.withUsername("user")
//					.password(passwordEncoder.encode("pass"))
//					.roles("USER")
//					.build();
//			UserDetails admin = User
//					.withUsername("admin")
//					.password(passwordEncoder.encode("admin"))
//					.roles("ADMIN")
//					.build();
//			return new InMemoryUserDetailsManager(user, admin);
//		}
	    
	    
	    
} 