package org.fmm.oauth.springsocial.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class UserPrincipal implements OidcUser, UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 371560060945584930L;
	private Integer id;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	
	public UserPrincipal(Integer id, String email, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(SocialUser user) {
		List<GrantedAuthority> authorities = Collections.
				singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new UserPrincipal(
				user.getId(),
				user.getEmail(),

				authorities
		);
	}
	
	public static UserPrincipal create(SocialUser user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		
		return userPrincipal;
	}
	
	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	private void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return getUsername();
	}

	@Override
	public Map<String, Object> getClaims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OidcIdToken getIdToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
