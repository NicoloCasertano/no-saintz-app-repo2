package com.example.app.models.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "art_name")
    private String artName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
      name = "user_authorities",
      joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Authority> authorities = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_work",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id")
    )
    private Set<Work> works = new HashSet<>();


    public User(Integer userId, String userName, String password, String email, String artName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.artName = artName;
    }

    public User() {}

    public void addAuthority(Authority auth) {
      this.authorities.add(auth);
    }
    public void clearAuthorities() {
      this.authorities.clear();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
      return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
      return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
      return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
      return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
