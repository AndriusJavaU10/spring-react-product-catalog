//package lt.ca.javau10.configuration;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//	Logger logger = LogManager.getLogger(SecurityConfig.class);
//	
//	 @Bean
//	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(auth -> auth
//	            	.requestMatchers("/").permitAll()
//	                .requestMatchers("/admin").hasRole("ADMIN")
//	                .requestMatchers("/user").hasAnyRole("USER","ADMIN")
//	                .anyRequest().authenticated()
//	            )
//	            .formLogin(form -> form	                 
//	                .permitAll() // Permit all access to the login page
//	            )
//	            .logout(logout -> logout
//	                .permitAll() // Allow all to access the logout endpoint
//	            );
//
//	        return http.build();
//	    }
//	    
//	 @Bean
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
//	    
//	    @Bean
//	    PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	
//}
