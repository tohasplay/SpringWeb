package com.demo.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.security.PermissionsConfig.*;

public enum RoleConfig {
    ADMIN(Sets.newHashSet(READ, WRITE, MAKE_ORDER)),
    USER(Sets.newHashSet(READ, MAKE_ORDER));

    @Getter
    private final Set<PermissionsConfig> permissions;

    RoleConfig(Set<PermissionsConfig> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
