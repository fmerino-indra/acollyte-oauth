package org.fmm.acollyte.acollyte;

import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.Base64;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;


@SpringBootTest(classes = {AcollyteApplication.class})
public class JWKApplicationTests {

	@Value("classpath:google-jwk.json")
	private Resource jwkResource;
	@Test
	public void jwkWrapper() throws IOException, ParseException, JOSEException, NoSuchAlgorithmException, InvalidKeySpecException {

		JWKSet set = null;
		JWK jwk = null;
		RSAKey key = null;
		PublicKey pKey = null;
		RSAPublicKey rsaPkey = null;
		byte[] data = null;
		String base64econded = null;
		
		RSAPublicKeySpec spec;
		KeyFactory f = KeyFactory.getInstance("RSA");
		PublicKey pk = null; 
		
		if (jwkResource == null)
			jwkResource = new ClassPathResource("google-jwk.json");
		
		set = JWKSet.load(jwkResource.getFile());
		jwk = set.getKeyByKeyId("822838c1c8bf9edcf1f5050662e54bcb1adb5b5f");
		System.out.println(jwk.computeThumbprint());
		System.out.println(jwk.toString());
		System.out.println(jwk.toJSONString());
		key = jwk.toRSAKey();
		pKey = key.toPublicKey();
		rsaPkey = (RSAPublicKey)key.toPublicKey();
		
		System.out.println("Certificado:");
		System.out.println("-----BEGIN CERTIFICATE-----");
		System.out.println(jwk.getX509CertThumbprint());
		System.out.println("-----END CERTIFICATE-----");

		System.out.println("n - Modulus:" + rsaPkey.getModulus());
		System.out.println("e - Public exponent:" + rsaPkey.getPublicExponent());
		
		data = rsaPkey.getEncoded();
		base64econded = new String(Base64.getEncoder().encode(data));
		System.out.println(base64econded);

		spec = new RSAPublicKeySpec(rsaPkey.getModulus(), rsaPkey.getPublicExponent());
		pk = f.generatePublic(spec);
		data = pk.getEncoded();
		base64econded = new String(Base64.getEncoder().encode(data));
		System.out.println(base64econded);
		StringWriter sw = new StringWriter();
		PemWriter pw = new PemWriter(sw);
		PemObject pem = new PemObject("RSA PUBLIC KEY", data);
		pw.writeObject(pem);
		pw.close();
		System.out.println(sw.toString());
	}

}
