package org.fmm.acollyte.acollyte.roles;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventsHandler {
	@EventListener
	public void onSuccess(AuthenticationSuccessEvent success) {
		Authentication auth = success.getAuthentication();
	}
	
	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent failures) {
		Authentication auth = failures.getAuthentication();
	}
	
}
