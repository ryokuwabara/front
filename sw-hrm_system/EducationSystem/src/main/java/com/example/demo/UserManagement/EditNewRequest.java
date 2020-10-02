package com.example.demo.UserManagement;

import java.io.Serializable;

import com.example.demo.Data.Detail;
import com.example.demo.Data.Profile;

import lombok.Data;


@Data
//edit.htmlより値を受け取る
public class EditNewRequest implements Serializable{
  
  private static final long serialVersionUID = -680383416552706566L;

  //テーブルのカラムに対応した変数を定義
  private String account;
  private String role; 
  private String password;

  //結合したテーブルを定義
  private Profile profile;
  private Detail detail;
  
}
