package com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;

@Data
public class Usuarios implements UserDetails {

    private Long id;
    private String nombre;
    private String apellido;
    private Long documentoIdentidad;

    private String celular;
    private String email;
    private String clave;

    @ManyToOne
    private Rol rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.getNombre()));
    }

    @Override
    public String getPassword() {
        return getClave();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
