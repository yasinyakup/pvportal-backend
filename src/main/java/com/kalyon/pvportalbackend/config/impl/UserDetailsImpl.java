package com.kalyon.pvportalbackend.config.impl;

import com.kalyon.pvportalbackend.model.Users;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

   private final Users users;

   public UserDetailsImpl(Users users){
       this.users = users;
   }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList((String[]) users.getRoles().toArray());
        return authorityList;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !users.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !users.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return users.isExpired();
    }

    @Override
    public boolean isEnabled() {
        return users.isEnabled();
    }
}
