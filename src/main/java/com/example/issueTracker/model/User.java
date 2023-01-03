package com.example.issueTracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String password;
    private boolean enabled;
    @OneToMany(mappedBy = "user")
    private List<Board> boards;


    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }
}
