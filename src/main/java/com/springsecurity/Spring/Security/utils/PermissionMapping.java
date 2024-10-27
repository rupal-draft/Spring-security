package com.springsecurity.Spring.Security.utils;

import com.springsecurity.Spring.Security.entities.enums.Permissions;
import com.springsecurity.Spring.Security.entities.enums.Roles;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecurity.Spring.Security.entities.enums.Permissions.*;
import static com.springsecurity.Spring.Security.entities.enums.Roles.*;

public class PermissionMapping {
    private static final Map<Roles, Set<Permissions>> map = Map.of(
            ADMIN, Set.of(FLAT_CREATE, FLAT_DELETE,FLAT_UPDATE,FLAT_VIEW),
            BUYER, Set.of(FLAT_VIEW),
            SELLER, Set.of(FLAT_CREATE,FLAT_DELETE,FLAT_UPDATE)
    );

    public static Set<SimpleGrantedAuthority> getPermissions(Roles role){
        return map
                .get(role)
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}

