package org.fmm.common.oauth2.crypto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(OAuth2CommonCryptoProperties.class)
@PropertySource(value = "classpath:oauth2-common-activation-application.yaml", factory=YamlPropertySourceFactory.class)
public class OAuth2CommonCryptoConfig {
	@Autowired
	private OAuth2CommonCryptoProperties properties;

	public OAuth2CommonCryptoProperties getProperties() {
		return properties;
	}

	public void setProperties(OAuth2CommonCryptoProperties properties) {
		this.properties = properties;
	}

}
