package com.xcbeyond.springboot.grpc.server.config;
import com.xcbeyond.springboot.grpc.server.converter.KeyCloakGrantedAuthoritiesConverter;
import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoderJwkSupport;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class JWTAuthConfig {
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(
            final KeyCloakGrantedAuthoritiesConverter keyCloakGrantedAuthoritiesConverter) {

        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        //converter.setJwtGrantedAuthoritiesConverter(keyCloakGrantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    KeyCloakGrantedAuthoritiesConverter keyCloakGrantedAuthoritiesConverter() {
        return new KeyCloakGrantedAuthoritiesConverter();
    }

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider(final JwtAuthenticationConverter jwtAuthenticationConverter) {
        final JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtDecoder());
        provider.setJwtAuthenticationConverter(jwtAuthenticationConverter);
        return provider;
    }

    @Bean
        /*
         * Add the authentication providers to the manager.
         */
    AuthenticationManager authenticationManager(final JwtAuthenticationProvider jwtAuthenticationProvider) {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(jwtAuthenticationProvider);
        return new ProviderManager(providers);
    }

    @Bean
        // Configure which authentication types you support.
    GrpcAuthenticationReader authenticationReader() {
        return new BearerAuthenticationReader(BearerTokenAuthenticationToken::new);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        // Uses local Keycloak instance running on port 8080 with the realm: TestRealm
        final String endpointURI = "http://localhost:8080/auth/realms/TestRealm/protocol/openid-connect/certs";
        //return NimbusJwtDecoder.withJwkSetUri(endpointURI).build();
        //return NimbusJwtDecoder
        //return JwtDecoder.formIssuerLocation(endpointURI);
        return new NimbusJwtDecoderJwkSupport(endpointURI, JwsAlgorithms.HS256);
    }
}
