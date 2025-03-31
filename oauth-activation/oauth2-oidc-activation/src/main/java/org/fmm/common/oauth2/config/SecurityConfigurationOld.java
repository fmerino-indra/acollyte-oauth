package org.fmm.common.oauth2.config;

import org.fmm.oauth.springsocial.security.FMMEntryPoint;
import org.fmm.oauth.springsocial.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import org.fmm.oauth.springsocial.security.oauth2.OAuth2AuthenticationFailureHandler;
import org.fmm.oauth.springsocial.security.oauth2.OAuth2AuthenticationSuccessHandlerOld;
import org.fmm.oauth.springsocial.util.FMMRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  securedEnabled = true,
  jsr250Enabled = true)
*/
@Deprecated
public class SecurityConfigurationOld {
		/*
	    By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
	    the authorization request. But, since our service is stateless, we can't save it in
	    the session. We'll save the request in a Base64 encoded cookie instead.
	  */
	  @Bean
	  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
	      return new HttpCookieOAuth2AuthorizationRequestRepository();
	  }
	
	@Autowired
	private OAuth2AuthenticationSuccessHandlerOld oAuth2AuthenticationSuccessHandler;
	
	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	
	@Autowired
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
	
//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors()
        	.and()
	    .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	    .csrf()
	        .disable()
	    .authorizeHttpRequests(authorize -> authorize
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/error")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/**")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/oauth2/**")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/logout")).permitAll()

	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/actuator")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/favicon.ico")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.png")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.gif")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.svg")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.jpg")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.html")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.css")).permitAll()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/**/*.js")).permitAll()
	    		.requestMatchers(HttpMethod.OPTIONS, "/profile").permitAll()
	    		.requestMatchers("/profile").authenticated()
	    		.anyRequest().authenticated()
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/user")).hasAuthority("SCOPE_USER")
	    		.requestMatchers(AntPathRequestMatcher.antMatcher("/acollyte")).hasAuthority("SCOPE_USER")
	    )

		.oauth2Login(oauth2 -> oauth2
			.authorizationEndpoint(a -> a
				.authorizationRedirectStrategy(new FMMRedirectStrategy()))
	
			.successHandler(oAuth2AuthenticationSuccessHandler)
		)
		.exceptionHandling(e ->
			e.authenticationEntryPoint(new FMMEntryPoint(HttpStatus.UNAUTHORIZED))
		)
		;

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

	        	
//        .oauth2Login()
//          .authorizationEndpoint()
//              .baseUri("/oauth2/authorize")
//              .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//              .and()
              
//          .redirectionEndpoint()
//              .baseUri("/oauth2/callback/*")
//              .and()
//          .successHandler(oAuth2AuthenticationSuccessHandler)
//          .failureHandler(oAuth2AuthenticationFailureHandler);
        return http.build();
    }
}
