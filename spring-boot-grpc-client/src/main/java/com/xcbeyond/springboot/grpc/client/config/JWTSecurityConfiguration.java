package com.xcbeyond.springboot.grpc.client.config;

import io.grpc.CallCredentials;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTSecurityConfiguration {

    public final static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIx" +
            "MjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNTE2MjM5MDk" +
            "wfQ.FHg7gTSQKoUdIJ6PqtoC_tuqCmXhBZvknvl8hftD1l0";


    @Bean
        //Create credentials bearer Token
    CallCredentials grpcCredentials() {
        return CallCredentialsHelper.bearerAuth(this.token);
    }
}
