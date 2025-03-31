package org.fmm.oauth.springsocial.security.oauth2;

import java.util.Optional;

import org.fmm.oauth.springsocial.exception.OAuth2AuthenticationProcessingException;
//import org.fmm.oauth.springsocial.model.AuthProvider;
//import org.fmm.oauth.springsocial.model.User;
//import org.fmm.oauth.springsocial.repository.UserRepository;
import org.fmm.common.oauth2.adhoc.model.entity.AuthProvider;
import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.fmm.oauth.springsocial.security.UserPrincipal;
import org.fmm.oauth.springsocial.security.oauth2.user.OAuth2UserInfo;
import org.fmm.oauth.springsocial.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomOidcUserService extends OidcUserService {

	@Autowired
	private SocialUserRepository userRepository;
//Ver super clase y en concreto atributo oauth2UserService que invoca a /userinfo
	@Override
	public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = null;
		oidcUser=super.loadUser(oidcUserRequest);
		
		processOAuth2User(oidcUserRequest, oidcUser);
		return oidcUser;
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		Optional<SocialUser> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		SocialUser user;
		
		if(userOptional.isPresent()) {
			user = userOptional.get();
			if (!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
						user.getProvider() + " account. Please user you " + user.getProvider() +
						" account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private SocialUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		SocialUser user = new SocialUser();
		
		user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		user.setProviderId(oAuth2UserInfo.getId());
		user.setName(oAuth2UserInfo.getName());
		user.setEmail(oAuth2UserInfo.getEmail());
		user.setEmailVerified(oAuth2UserInfo.isEmailVerified());
		user.setImageUrl(oAuth2UserInfo.getImageUrl());
		return userRepository.save(user);
	}

	private SocialUser updateExistingUser(SocialUser existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setName(oAuth2UserInfo.getName());
		existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
		existingUser.setEmailVerified(oAuth2UserInfo.isEmailVerified());
		return userRepository.save(existingUser);
	}
}
