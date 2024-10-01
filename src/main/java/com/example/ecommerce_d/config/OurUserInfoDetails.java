package com.example.ecommerce_d.config;

import com.example.ecommerce_d.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OurUserInfoDetails implements UserDetails {//interface lấy thông tin người dùng bao gồm tên, mật khẩu, quyền
    private User user;
    private List<GrantedAuthority> roles;
    public OurUserInfoDetails(User user){
        this.user=user;
//        this.roles= Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
