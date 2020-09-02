package com.xcbeyond.springboot.grpc.server.config;

import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.check.GrpcSecurityMetadataSource;
import net.devh.boot.grpc.server.security.check.ManualGrpcSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig {
/*    @Bean
    AuthenticationManager authenticationManager() {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(...); // Possibly JwtAuthenticationProvider
        return new ProviderManager(providers);
    }*/

/*    @Bean
    GrpcAuthenticationReader authenticationReader() {
        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // The actual token class is dependent on your spring-security library (OAuth2/JWT/...)
        readers.add(new BearerAuthenticationReader(accessToken -> new BearerTokenAuthenticationToken(accessToken)));
        return new CompositeGrpcAuthenticationReader(readers);
    }*/


/*    @Bean
    GrpcSecurityMetadataSource grpcSecurityMetadataSource() {
        final ManualGrpcSecurityMetadataSource source = new ManualGrpcSecurityMetadataSource();
        source.set(MyServiceGrpc.getMethodA(), AccessPredicate.authenticated());
        source.set(MyServiceGrpc.getMethodB(), AccessPredicate.hasRole("ROLE_USER"));
        source.set(MyServiceGrpc.getMethodC(), AccessPredicate.hasAllRole("ROLE_FOO", "ROLE_BAR"));
        source.set(MyServiceGrpc.getMethodD(), auth -> "admin".equals(auth.getName()));
        source.setDefault(AccessPredicate.denyAll());
        return source;
    }*/

}
