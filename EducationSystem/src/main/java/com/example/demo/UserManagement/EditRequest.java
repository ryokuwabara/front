package com.example.demo.UserManagement;

import com.example.demo.Data.*;
import java.io.Serializable;

import lombok.Data;


@Data
//edit.htmlより値を受け取る
public class EditRequest implements Serializable{
  
  private static final long serialVersionUID = -680383416552706566L;

  //テーブルのカラムに対応した変数を定義
  private Integer id;
  private String account;
  private String role; 

  //結合したテーブルを定義
  private Profile profile;
  
}
