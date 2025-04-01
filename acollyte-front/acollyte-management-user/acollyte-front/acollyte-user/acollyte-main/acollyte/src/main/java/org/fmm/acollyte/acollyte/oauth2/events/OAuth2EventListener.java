package org.fmm.acollyte.acollyte.oauth2.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizedEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class OAuth2EventListener {
	private static final Logger logger = LoggerFactory.getLogger(OAuth2EventListener.class);

	//	@EventListener(value = AbstractAuthorizationEvent.class)
	@EventListener(value = AuthorizedEvent.class)
	public void onApplicationEvent(AuthorizedEvent event) {
		logger.info(event.getAuthentication().getName() + " at " + event.getTimestamp() + " with " + event.getAuthentication().getPrincipal());
	}
	
	@EventListener(value = AuthenticationSuccessEvent.class)
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		logger.info(event.getAuthentication().getName() + " at " + event.getTimestamp() + " with " + event.getAuthentication().getPrincipal());
	}
}
