package org.fmm.common.oauth2.crypto.store;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;

import jakarta.annotation.PostConstruct;

@Component
public class JWSSignerWrapper {
	private RSAKey privateKeyJWK = null;
	private RSAKey publicKeyJWK = null;
	private JWSSigner signer = null; 
	private JWSVerifier verifier = null;
	

	@Value("${app.jwtProperties.file:#{null}}")
	private String keyStoreFile;

	@Value("${app.jwtProperties.password:#{null}}")
	private String password;
	
	@Value("${app.jwtProperties.alias:#{null}}")
	private String alias;
	
	
	@Autowired
	private TriFunction<String, String, String, JWSKeyStoreWrapper> myPrototypeFactory;
	
	private JWSKeyStoreWrapper jwsKeyStore;

	@PostConstruct
	private void init() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, JOSEException {
		if (keyStoreFile != null) {
			jwsKeyStore = myPrototypeFactory.apply(keyStoreFile, password, alias);
		
			jwsKeyStore.listCertificates();
			
			PrivateKey k = jwsKeyStore.getPrivateKey();
			PublicKey pk = jwsKeyStore.getPublicKey();
			String alias = jwsKeyStore.getDefaultAlias();
			
			RSAPublicKey rsapk = (RSAPublicKey)pk;
			
			RSAKey.Builder builder = new RSAKey.Builder(rsapk);
			
			privateKeyJWK = builder.privateKey(k).keyID(alias).build();
			publicKeyJWK = privateKeyJWK.toPublicJWK();
			
			signer = new RSASSASigner(privateKeyJWK);
			verifier = new RSASSAVerifier(publicKeyJWK);
		}
	}
	
	public String signJSON(String json) throws JsonMappingException, JsonProcessingException, JOSEException, ParseException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException {
		JWSObject jwsObject = new JWSObject(
				new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(privateKeyJWK.getKeyID()).build(),
				new Payload(json));
		jwsObject.sign(signer);
		
		String s = jwsObject.serialize();
		return s;
	}
	
	public String verifySignedJSON(String signedJson) throws ParseException, JOSEException {
		JWSObject jwsObject = JWSObject.parse(signedJson);
		jwsObject.verify(verifier);
		
		return jwsObject.getPayload().toString();
	}
}
