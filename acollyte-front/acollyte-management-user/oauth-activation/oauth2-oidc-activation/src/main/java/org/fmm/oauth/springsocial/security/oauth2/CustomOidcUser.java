package org.fmm.oauth.springsocial.security.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUser implements OidcUser {

	private DefaultOidcUser user = null;
	private OAuth2AccessToken accessToken = null;
	private OAuth2RefreshToken refreshToken = null;
	
	public CustomOidcUser(DefaultOidcUser user, OidcUserRequest userRequest) {
		this.user = user;
		this.accessToken = userRequest.getAccessToken();
//		this.refreshToken = userRequest.get
		
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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
