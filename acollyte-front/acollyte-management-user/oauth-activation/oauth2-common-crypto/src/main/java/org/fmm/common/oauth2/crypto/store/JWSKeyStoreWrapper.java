package org.fmm.common.oauth2.crypto.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import jakarta.annotation.PostConstruct;

@Component
@Scope("prototype")
public class JWSKeyStoreWrapper {

	private String keyStoreFile;
	private String password;
	private String alias;
	
	private KeyStore keyStore = null;

	public JWSKeyStoreWrapper() {
		super();
	}

	public JWSKeyStoreWrapper(String keyStoreFile, String password, String alias) {
		super();
		this.keyStoreFile = keyStoreFile;
		this.password = password;
		this.alias = alias;
	}
	
	@SuppressWarnings("resource")
	@PostConstruct
	public void init() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		keyStore = KeyStore.getInstance("JKS");
		ClassPathResource cpr = null;
		File file = null;
		InputStream is = null;
		try {
			file = ResourceUtils.getFile("classpath:"+keyStoreFile);
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			cpr = new ClassPathResource(keyStoreFile);
			is = cpr.getInputStream();
		}
		try {
			keyStore.load(is,password.toCharArray() );
		} catch (IOException ioe) {
			throw new RuntimeException("[JWSKeyStoreWrapper] Keystore not loaded successfully. Wrong password", ioe);
		} finally {
			is.close();
		}
	}
	
	public List<String> listCertificates() throws KeyStoreException {
		return Collections.list(keyStore.aliases());
	}
	
	public PublicKey getPublicKey() throws KeyStoreException {
		return getPublicKey(this.alias);
	}
	public PublicKey getPublicKey(String alias) throws KeyStoreException {
		PublicKey pk = null;
		if (keyStore.isKeyEntry(alias)) {
			pk = keyStore.getCertificate(alias).getPublicKey();
		}
		return pk;
	}
	
	public PrivateKey getPrivateKey() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		return getPrivateKey(this.alias, this.password.toCharArray());
	}
	public PrivateKey getPrivateKey(String alias, char[] password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		Key k = null;
		if (keyStore.isKeyEntry(alias)) {
			k = keyStore.getKey(alias, password);
		}
		if (k instanceof PrivateKey)
			return (PrivateKey)k;
		else
			return null;
	}

	public String getDefaultAlias() {
		return alias;
	}
}
