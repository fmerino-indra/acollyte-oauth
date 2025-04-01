package org.fmm.common.oauth2.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@DependsOn("jwtAuthenticationConverter")
/**
 * Parece que la intención de esta clase era simplemente activar el proyecto que la incluyera el 
 * oauth2ResourceServer. 
 * Hay que investigar más
 */
public class SecurityConfiguration {
//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
//        http
//        .cors()
//        	.and()
//	    .sessionManagement()
//	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        .and()
//	    .csrf()
//	        .disable()
//        .authorizeRequests()
//        	.antMatchers("/user","/acollyte")
//        		.hasAuthority("SCOPE_USER")
//	        .antMatchers("/",
//	            "/error",
//	            "/actuator/**",
//	            "/favicon.ico",
//	            "/**/*.png",
//	            "/**/*.gif",
//	            "/**/*.svg",
//	            "/**/*.jpg",
//	            "/**/*.html",
//	            "/**/*.css",
//	            "/**/*.js")
//	            .permitAll()
//	        .antMatchers("/auth/**", "/oauth2/**")
//	            .permitAll()
//	        .anyRequest()
//	        	.authenticated()
//	        	.and()
//            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
            
// https://stackoverflow.com/questions/59873571/spring-boot-what-event-fired-during-oauth2-authorization-success
// No funciona, sólo funciona el authentication, no el authorization. Puede ser que haya que
// incluir permisos en los métodos para que se lance este evento, por lo que cara a Spring,
// OAuth2 Resource Server sólo autentica.
        
//            .oauth2ResourceServer().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//            	public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//            		fsi.setPublishAuthorizationSuccess(true);
//            		return fsi;
//            	}
//            });
        
        return http.build();
    }
//    @Bean
//    public JwtDecoder jwtDecoder() {
//    	return JwtDecoders.fromIssuerLocation("https://accounts.google.com");
//    }
}
