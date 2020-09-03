package com.xcbeyond.springboot.grpc.client.config;

import io.grpc.CallCredentials;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SecurityConfiguration {

    private String username = "admin";

    @Bean
        // Create credentials for username + password.
    CallCredentials grpcCredentials() {
        return CallCredentialsHelper.basicAuth(this.username, this.username + "Password");
    }
}
