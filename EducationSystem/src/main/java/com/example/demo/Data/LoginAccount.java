package com.example.demo.Data;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "login")
@Data

public class LoginAccount {

  public LoginAccount(){

  }

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "user_id")
  private Profile profile;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "user_id")
  private Detail detail;

  @Id
  @Column(nullable = false, unique = true, name = "user_id")
  private Integer id;

  @Column(nullable = false, unique = true)
  private String account;

  @Column(nullable = false, name = "duties")
  private String role;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, name = "updateday")
  private Timestamp timestamp;

  @Column(nullable = false)
  private boolean enabled;

  public Integer getId() {
    return id;
  }

  public String getAccount(){
    return account;
  }

  public String getRole(){
    return role;
  }

  public String getPassword(){
    return password;
  }

  public Timestamp getTimestamp(){
    return timestamp;
  }

  public boolean getEnabled(){
    return enabled;
  }

  public LoginAccount(String account,  String password, Timestamp timestamp,  String role) {
    setId(0);
    setAccount(account);
    setRole(role);
    setPassword(password);  
    setTimestamp(timestamp);
    setEnabled(true);
  }

}
