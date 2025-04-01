package org.fmm.common.oauth2.crypto.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JWSConfiguration {
    @Bean
    public TriFunction<String, String, String, JWSKeyStoreWrapper> myJWSKeyStoreFactory() {
        return (arg1,arg2,arg3) -> myJWSKeyStore(arg1,arg2,arg3);
    } 
    @Bean
    @Scope(value = "prototype")
    public JWSKeyStoreWrapper myJWSKeyStore(String keyStoreFile, String password, String alias) {
       return new JWSKeyStoreWrapper(keyStoreFile, password, alias);
    }

}
