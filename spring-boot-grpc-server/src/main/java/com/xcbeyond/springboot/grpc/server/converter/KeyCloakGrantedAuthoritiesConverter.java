package com.xcbeyond.springboot.grpc.server.converter;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeyCloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String SCOPE_AUTHORITY_PREFIX = "ROLE_";
    private static final Collection<String> WELL_KNOWN_SCOPE_ATTRIBUTE_NAMES =
            Arrays.asList("realm_access");

    /**
     * Extracts the authorities
     *
     * @param jwt The {@link Jwt} token
     * @return The {@link GrantedAuthority authorities} read from the token scopes
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        return getScopes(jwt)
                .stream()
                .map(authority -> SCOPE_AUTHORITY_PREFIX + authority.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the realm access roles from a {@link Jwt} Keycloak access token. This can also be done for any kind of
     * roles/scopes contained in the token. Adapt this method accordingly.
     *
     * @param jwt The {@link Jwt} access token
     * @return The realm access roles from the token
     */
    private Collection<String> getScopes(Jwt jwt) {
        Collection<String> result = new ArrayList<>();
        for (String attributeName : WELL_KNOWN_SCOPE_ATTRIBUTE_NAMES) {
            // Retrieve realm_access entry from access token
            JSONObject realm_access = (JSONObject) jwt.getClaims().get(attributeName);
            if (Objects.isNull(realm_access)) {
                return Collections.emptyList();
            }

            // Retrieve roles from realm_access
            JSONArray roles = (JSONArray) realm_access.get("roles");
            if (Objects.isNull(roles)) {
                return Collections.emptyList();
            }

            for (Object role : roles) {
                result.add((String) role);
            }
        }
        return result;
    }
}
