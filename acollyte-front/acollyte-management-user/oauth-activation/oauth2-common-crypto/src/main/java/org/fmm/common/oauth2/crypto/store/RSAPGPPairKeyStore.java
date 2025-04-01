package org.fmm.common.oauth2.crypto.store;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

//import javax.annotation.PostConstruct;

import org.springframework.util.ResourceUtils;

import jakarta.annotation.PostConstruct;

public class RSAPGPPairKeyStore {
	
	private RSAPublicKey publicKey = null;
	private RSAPrivateKey privateKey = null;
	
	private String publicKeyFileName = null;
	private String privateKeyFileName = null;
	
	public RSAPGPPairKeyStore(String publicKeyFileName, String privateKeyFileName) {
		super();
		this.publicKeyFileName = publicKeyFileName;
		this.privateKeyFileName = privateKeyFileName;
	}
	//-----BEGIN RSA PRIVATE KEY----- => PKCS1
	//-----BEGIN PRIVATE KEY-----     => PKCS8 Pero para pubKey no lo entiendo
	
	private static final String I_CERT = "-----BEGIN CERTIFICATE-----\n";
	private static final String F_CERT = "-----END CERTIFICATE-----\n"; 
	
	private static final String I_PUBLIC = "-----BEGIN PUBLIC KEY-----\n";
    private static final String F_PUBLIC = "-----END PUBLIC KEY-----\n";
	
    private static final String I_RSA_PUBLIC = "-----BEGIN RSA PUBLIC KEY-----\\r\\n";
    private static final String F_RSA_PUBLIC = "-----END RSA PUBLIC KEY-----\\r\\n";
    
	@PostConstruct
	private void init() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicKeyBytes = null;
		byte[] privateKeyBytes = null;
		X509EncodedKeySpec publicSpec = null;
		PKCS8EncodedKeySpec privateSpec = null;
		PKCS8EncodedKeySpec pkcs8PublicSpec = null;

		KeyFactory publicKf = null;
		KeyFactory privateKf = null;
		File file = null;
		
//		String inicio = "-----BEGIN PUBLIC KEY-----\r\n";
//		String fin = "-----END PUBLIC KEY-----\r\n";
		
//		String inicio = "-----BEGIN RSA PUBLIC KEY-----\r\n";
//		String fin = "-----END RSA PUBLIC KEY-----\r\n";

		String inicio = "-----BEGIN CERTIFICATE-----\n";
		String fin = "-----END CERTIFICATE-----\n";
		
		// PublicKey
		if (publicKeyFileName != null) {
			file = ResourceUtils.getFile("classpath:"+publicKeyFileName);
			publicKeyBytes=Files.readAllBytes(file.toPath());
			
			
			String temp = new String(publicKeyBytes, "UTF-8");
			
			if (temp.startsWith(I_CERT)) {
	            this.publicKey=paraCertificados(publicKeyBytes);
			} else if (temp.startsWith(I_RSA_PUBLIC)){
				this.publicKey=paraPKCS1(temp, I_RSA_PUBLIC, F_RSA_PUBLIC);
			} else if (temp.startsWith(I_PUBLIC)) {
				this.publicKey=paraPKCS1(temp, I_PUBLIC, F_PUBLIC);
			} else {
    			String publicKeyPEM = temp.replace(I_PUBLIC, "");
    //			System.out.println(publicKeyPEM);
    	        publicKeyPEM = publicKeyPEM.replace(F_PUBLIC, "");
    	        publicKeyPEM = publicKeyPEM.replaceAll("[\n|\r]","");
    	        Decoder b64 = Base64.getDecoder();
    	        byte[] decoded = b64.decode(publicKeyPEM);
    			publicKf = KeyFactory.getInstance("RSA");
    	        
    //	        publicSpec = new X509EncodedKeySpec(publicKeyBytes);
    //	        publicSpec = new X509EncodedKeySpec(publicKeyPEM.getBytes());
    			
    			publicSpec = new X509EncodedKeySpec(decoded);
    //	        pkcs8PublicSpec = new PKCS8EncodedKeySpec(decoded); 
    
    //			publicKey = (RSAPublicKey)publicKf.generatePublic(pkcs8PublicSpec);
    		    publicKey = (RSAPublicKey)publicKf.generatePublic(publicSpec);
			}
		    		    
		}
	    
	    // PrivateKey
		if (privateKeyFileName != null) {
			file = ResourceUtils.getFile("classpath:"+privateKeyFileName);
			privateKeyBytes=Files.readAllBytes(file.toPath());
			privateSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			privateKf = KeyFactory.getInstance("RSA");
		    privateKey = (RSAPrivateKey) privateKf.generatePrivate(privateSpec);
		}
	}
	private RSAPublicKey paraPKCS1(String utf8Encoded, String inicio, String fin) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory publicKeyfactory = null;
		X509EncodedKeySpec publicSpec = null;
		RSAPublicKey publicKey = null;
		
		String publicKeyPEM = utf8Encoded.replace(inicio, "");
        publicKeyPEM = publicKeyPEM.replace(fin, "");
        publicKeyPEM = publicKeyPEM.replaceAll("[\n|\r]","");
        Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(publicKeyPEM);
        publicKeyfactory = KeyFactory.getInstance("RSA");
        
		publicSpec = new X509EncodedKeySpec(decoded);
		publicKey = (RSAPublicKey)publicKeyfactory.generatePublic(publicSpec);
        
	    return publicKey;
	}

	private RSAPublicKey paraPKCS8(String utf8Encoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory publicKeyfactory = null;
		RSAPublicKey publicKey = null;
		PKCS8EncodedKeySpec pkcs8PublicSpec = null;
		
		String publicKeyPEM = utf8Encoded.replace(I_PUBLIC, "");
        publicKeyPEM = publicKeyPEM.replace(F_PUBLIC, "");
        publicKeyPEM = publicKeyPEM.replaceAll("[\n|\r]","");
        Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(publicKeyPEM);
        publicKeyfactory = KeyFactory.getInstance("RSA");
        
        pkcs8PublicSpec = new PKCS8EncodedKeySpec(decoded); 
		publicKey = (RSAPublicKey)publicKeyfactory.generatePublic(pkcs8PublicSpec);
        
	    return publicKey;
	}
	private RSAPublicKey paraCertificados(byte[] publicKeyBytes) {
	    CertificateFactory certificateFactory;
	    Certificate cert = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            cert = certificateFactory.generateCertificate(new ByteArrayInputStream(publicKeyBytes));
            return (RSAPublicKey) cert.getPublicKey();
        } catch (CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
	}
	
	private void init2() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicKeyBytes = null;
		byte[] privateKeyBytes = null;
		X509EncodedKeySpec publicSpec = null;
		PKCS8EncodedKeySpec privateSpec = null;
		KeyFactory publicKf = null;
		KeyFactory privateKf = null;
		File file = null;
		
		// PublicKey
		if (publicKeyFileName != null) {
			file = ResourceUtils.getFile("classpath:"+publicKeyFileName);
			publicKeyBytes=Files.readAllBytes(file.toPath());
			publicSpec = new X509EncodedKeySpec(publicKeyBytes);
			publicKf = KeyFactory.getInstance("RSA");
		    publicKey = (RSAPublicKey)publicKf.generatePublic(publicSpec);
		}
	    
	    // PrivateKey
		if (publicKeyFileName != null) {
			file = ResourceUtils.getFile("classpath:"+privateKeyFileName);
			privateKeyBytes=Files.readAllBytes(file.toPath());
			privateSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			privateKf = KeyFactory.getInstance("RSA");
		    privateKey = (RSAPrivateKey) privateKf.generatePrivate(privateSpec);
		}
	}
	
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKeyFileName() {
		return publicKeyFileName;
	}

	public void setPublicKeyFileName(String publicKeyFileName) {
		this.publicKeyFileName = publicKeyFileName;
	}

	public String getPrivateKeyFileName() {
		return privateKeyFileName;
	}

	public void setPrivateKeyFileName(String privateKeyFileName) {
		this.privateKeyFileName = privateKeyFileName;
	}
}
