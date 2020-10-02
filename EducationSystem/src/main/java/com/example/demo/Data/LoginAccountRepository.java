package com.example.demo.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAccountRepository extends JpaRepository<LoginAccount, Integer> {

  public LoginAccount findByAccount(String account);
  //List <LoginAccount> findByRole(String role);

}
