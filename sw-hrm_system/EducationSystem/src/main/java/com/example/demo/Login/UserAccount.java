package com.example.demo.Login;

import com.example.demo.Data.*;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccount implements UserDetails {

  /**
   *
   */
  private static final long serialVersionUID = 1962968174573719234L;
  private LoginAccount user;
  private Collection<GrantedAuthority> authorities;

  public UserAccount(final LoginAccount account, final Collection<GrantedAuthority> authorities) {
    this.user = account;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  public Integer getId() {
    return user.getId();
  }

  public String getUsername() {
    return user.getAccount();
  }

  public String getRole() {
    return user.getRole();
  }

  public String getPassword() {
    return user.getPassword();
  }

  public Profile getProfile(){
    return user.getProfile();
  }
  

  public Detail getDetail(){
    return user.getDetail();
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
    return user.getEnabled();
  }
}
