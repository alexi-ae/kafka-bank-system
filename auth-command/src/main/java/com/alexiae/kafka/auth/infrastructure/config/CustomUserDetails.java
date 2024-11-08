package com.alexiae.kafka.auth.infrastructure.config;

import com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes devolver los roles del usuario
        return null; // Deberías devolver los roles como GrantedAuthority
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Usamos el email como nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Lógica para verificar si la cuenta ha expirado
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Lógica para verificar si la cuenta está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Lógica para verificar si las credenciales han expirado
    }

    @Override
    public boolean isEnabled() {
        return true; // Lógica para verificar si la cuenta está habilitada
    }

    public UserEntity getUser() {
        return user; // Método para acceder a la entidad Usuario
    }
}
