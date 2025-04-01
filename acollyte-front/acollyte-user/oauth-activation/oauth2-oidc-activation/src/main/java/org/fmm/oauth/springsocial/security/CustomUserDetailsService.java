package org.fmm.oauth.springsocial.security;


import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.fmm.oauth.springsocial.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	SocialUserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SocialUser user = userRepository.findByEmail(email)
				.orElseThrow(() ->
					new UsernameNotFoundException("User not found with email: "+email)
		);
		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Integer id) {
		SocialUser user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", id)
		);
		return UserPrincipal.create(user);
	}
}
