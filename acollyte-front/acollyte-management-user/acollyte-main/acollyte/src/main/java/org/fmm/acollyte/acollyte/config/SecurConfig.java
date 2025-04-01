package org.fmm.acollyte.acollyte.config;

import org.fmm.acollyte.acollyte.roles.JwtAuthenticationConverterFMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  securedEnabled = true,
  jsr250Enabled = true)
public class SecurConfig {
	@Autowired
	public JwtAuthenticationConverterFMM jwtAuthenticationConcerterFMM;
//	@Bean
//	public JwtDecoder jwtDecoder() {
//	    return JwtDecoders.fromIssuerLocation("https://accounts.google.com");
//	}	
//	@Autowired
//	public JwtDecoder jwtDecoder;
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http
        .cors()
        	.and()
	    .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	    .csrf()
	        .disable()
        .authorizeRequests()
//        	.antMatchers("/user","/acollyte")
//        		.hasAuthority("SCOPE_USER")
	        .antMatchers("/",
	            "/error",
	            "/actuator/**",
	            "/favicon.ico",
	            "/**/*.png",
	            "/**/*.gif",
	            "/**/*.svg",
	            "/**/*.jpg",
	            "/**/*.html",
	            "/**/*.css",
	            "/**/*.js")
	            .permitAll()
	        .antMatchers("/auth/**", "/oauth2/**")
	            .permitAll()
	        .anyRequest()
	        	.authenticated()
	        	.and()
            .oauth2ResourceServer(oauth2 -> oauth2
            		.jwt(jwt -> jwt
            				.jwtAuthenticationConverter(jwtAuthenticationConcerterFMM)
            		)
            );
//            .oauth2ResourceServer().jwt();
            
        return http.build();
    }

}
