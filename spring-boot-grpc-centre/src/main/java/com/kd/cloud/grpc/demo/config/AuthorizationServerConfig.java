package com.kd.cloud.grpc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,proxyTargetClass = true,prePostEnabled=true)
public class AuthorizationServerConfig {



}
