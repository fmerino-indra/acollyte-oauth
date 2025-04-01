package org.fmm.oauth.springsocial.security;

import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.fmm.common.oauth2.config.OAuth2ClientServerProperties;
import org.fmm.common.oauth2.config.OAuth2ClientServerProperties.ProvidersConfigProperties;
import org.fmm.common.oauth2.crypto.store.JWSKeyStoreWrapper;
import org.fmm.common.oauth2.crypto.store.RSAPGPPairKeyStore;
import org.fmm.common.oauth2.crypto.store.TriFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

import jakarta.annotation.PostConstruct;

@Service
public class TokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
	private OAuth2ClientServerProperties appProperties;
	
	private Map<String,JWSKeyStoreWrapper> keyStoreWrapperMap = null;;
	private Map<String, RSAPublicKey> publicKeyMap = null;
	private Map<String, JWSVerifier> jwsVerifierMap = null;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	public TokenProvider(OAuth2ClientServerProperties appProperties) {
		this.appProperties = appProperties;
	}

	@Autowired
	private TriFunction<String, String, String, JWSKeyStoreWrapper> myJWSKeyStoreFactory;
	
	@Autowired
	private BiFunction<String, String, RSAPGPPairKeyStore> myPgpStoreFactory;
	
	@PostConstruct
	public void init() throws KeyStoreException {
		ProvidersConfigProperties providerCertificate = null;
		Map<String, ProvidersConfigProperties> providerCertificates = null;
		
		JWSKeyStoreWrapper keyStoreWrapper = null;
		RSAPublicKey publicKey = null;
		JWSVerifier rsaVerifier = null;
		
		RSAPGPPairKeyStore rsaPgpPairKeyStore = null;
		
		keyStoreWrapperMap = new HashMap<>();
		publicKeyMap = new HashMap<>();
		jwsVerifierMap = new HashMap<>();
		
		for (String key: appProperties.getProviderMapProperties().keySet()) {
			providerCertificates = appProperties.getProviderMapProperties().get(key);
			for (String providerCertificateKey : providerCertificates.keySet()) {
				providerCertificate = providerCertificates.get(providerCertificateKey);
			
				if (providerCertificate.getjKSConfigProperties() != null) {
					keyStoreWrapper = myJWSKeyStoreFactory.apply(providerCertificate.getjKSConfigProperties().getKeystore(), providerCertificate.getjKSConfigProperties().getPassword(), providerCertificate.getjKSConfigProperties().getAlias());
					keyStoreWrapperMap.put(providerCertificate.getjKSConfigProperties().getKid(), keyStoreWrapper);
					
					publicKey = (RSAPublicKey)keyStoreWrapper.getPublicKey(providerCertificate.getjKSConfigProperties().getAlias());
					publicKeyMap.put(providerCertificate.getjKSConfigProperties().getKid(), publicKey);
					
					rsaVerifier= new RSASSAVerifier((RSAPublicKey) publicKey);
					jwsVerifierMap.put(providerCertificate.getjKSConfigProperties().getKid(), rsaVerifier);
					
				} else if (providerCertificate.getPubCertConfigProperties() != null) {
					rsaPgpPairKeyStore = myPgpStoreFactory.apply(providerCertificate.getPubCertConfigProperties().getPubCertFile(), null);
	
					publicKey = rsaPgpPairKeyStore.getPublicKey();
					publicKeyMap.put(providerCertificate.getPubCertConfigProperties().getKid(), publicKey);
					
					rsaVerifier= new RSASSAVerifier((RSAPublicKey) publicKey);
					jwsVerifierMap.put(providerCertificate.getPubCertConfigProperties().getKid(), rsaVerifier);
				} else if (providerCertificate.getjWKConfigProperties() != null) {
				}
			}
		}
	}
	
	public String createToken(Authentication authentication) {
		return extractToken(authentication);
	}
	
	public String extractToken(Authentication authentication) {
		OidcUser userPrincipal = (OidcUser) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
		return userPrincipal.getIdToken().getTokenValue();
	}
	
	public Long getUserIdFromToken(String token) {
		return 1l;
	}
	
	public String getUserNameFromToken(String authToken) throws ParseException {
		SignedJWT jwt = null;
		jwt = parseToken(authToken);
		return jwt.getJWTClaimsSet().getSubject();
	}

	public String getEmailFromToken(String authToken) throws ParseException {
		SignedJWT jwt = null;
		jwt = parseToken(authToken);
		return (String)jwt.getJWTClaimsSet().getClaim("email");
	}

	// Ahora está fallando por los cambios realizados en el user
	public boolean validateToken(String authToken) {
		boolean verifiedSignature = false;
		try {
//			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			PublicKey publicKey = null;
			appProperties.getAuth().getTokenSecret();

			SignedJWT jwt = null;
			JWSHeader headers = null;
			String kid = null;
			JWSVerifier rsaVerifier = null;
			String alias = null; 
			
			jwt = parseToken(authToken);
			headers=jwt.getHeader();
			kid=headers.getKeyID();
//			alias=appProperties.getProviderPropertiesByKid().get(kid).getAlias();
//			System.out.println(alias + "->" +publicKey);
//			logger.debug("kid:{} -> alias:{} -> publicKey:{}",kid,alias,publicKey);
			
			if (!jwsVerifierMap.containsKey(kid)) {
				logger.error("No public key for validate signature. Alias: {}", kid);
				return false;
			}

			rsaVerifier = jwsVerifierMap.get(kid);
			verifiedSignature = jwt.verify(rsaVerifier);
			return verifiedSignature;
			
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty");
		} catch (ParseException e) {
			logger.error("Invalid JWT. ParseException");
		} catch (JOSEException e) {
			logger.error("Invalid JWT -signature not validated-. JOSEException");
		} 
		
		return false;
	}
	
	private SignedJWT parseToken(String authToken) throws ParseException {
		SignedJWT jwt = null;
		jwt = SignedJWT.parse(authToken);
		return jwt;
	}
	
}
