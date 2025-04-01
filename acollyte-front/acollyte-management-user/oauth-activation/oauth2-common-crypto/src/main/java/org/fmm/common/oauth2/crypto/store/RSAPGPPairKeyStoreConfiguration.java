package org.fmm.common.oauth2.crypto.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RSAPGPPairKeyStoreConfiguration {
    @Bean
    public BiFunction<String, String, RSAPGPPairKeyStore> myRSAPGPPairKeyStoreFactory() {
        return (arg1,arg2) -> myRSAPGPPairKeyStore(arg1,arg2);
    } 
    @Bean
    @Scope(value = "prototype")
    public RSAPGPPairKeyStore myRSAPGPPairKeyStore(String publicKeyFileName, String privateKeyFileName) {
       return new RSAPGPPairKeyStore(publicKeyFileName, privateKeyFileName);
    }

}
