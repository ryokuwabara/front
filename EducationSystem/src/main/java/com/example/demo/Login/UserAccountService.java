package com.example.demo.Login;

import com.example.demo.Data.*;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {
  
  @Autowired
  private LoginAccountRepository LArepository;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    final String mailFormat = "^[a-z]+(\\.[a-z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*@[a-z0-9][a-z0-9\\-]*(\\.[a-z0-9\\-]+)+$";

    final String passwordForm = "[a-zA-Z0-9!-/:-@\\[-`{-~]{8,}$";

    //メールフォーマットチェックはユーザ追加に
    if (!username.matches(mailFormat)) {
      throw new UsernameNotFoundException("Username is invailed");
    }

    if (username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }

    final LoginAccount ac = LArepository.findByAccount(username);
    
    if (ac == null) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    if (!ac.getEnabled()) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    final UserAccount user = new UserAccount(ac, getAuthorities(ac));

    //パスワードフォーマットチェックはユーザ追加に
    if (!user.getPassword().matches(passwordForm)) {
      throw new UsernameNotFoundException("password is invailed");
    }

    return user;

  }

  private Collection<GrantedAuthority> getAuthorities(final LoginAccount account) {

    final String roles = account.getRole();

    switch (roles) {
      case "ADMIN":
        return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
      case "TUTOR":
        return AuthorityUtils.createAuthorityList("ROLE_TUTOR");
      case "USER":
        return AuthorityUtils.createAuthorityList("ROLE_USER");
      default:
        return AuthorityUtils.createAuthorityList("ROLE_VIEWER");
    }
  }
}
