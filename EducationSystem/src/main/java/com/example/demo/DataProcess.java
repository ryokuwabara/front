package com.example.demo;

import com.example.demo.Data.*;

import java.util.ArrayList;
import java.util.List;


public class DataProcess {

  public DataProcess() { // 引数なしのコンストラクタ
  }

  //UserManagementControllerのlistメソッドより一時リストを受け取る
  public List<LoginAccount> convert(List<LoginAccount> profile){ 

    for (int i = 0; i < profile.size(); i++) { //profileの要素の数だけfor文を回す

      //i番目に取得したロールの文字列を日本語に変換
      if (profile.get(i).getRole().equals("ADMIN")){
        profile.get(i).setRole("管理者");

      } else if (profile.get(i).getRole().equals("USER")){
        profile.get(i).setRole("受講者");

      } else if (profile.get(i).getRole().equals("TUTOR")){
        profile.get(i).setRole("講師");

      } else if (profile.get(i).getRole().equals("VIEWER")){
        profile.get(i).setRole("閲覧者");

      }
    }

    return profile; //listメソッドへリストを返す

  }

   //UserManagementControllerのcreateメソッドで生成した値を受け取る
    public Integer idcheck(Integer Id, List<LoginAccount> temporary){

    List<Integer> IdList = new ArrayList<>(); //user_idのみを格納するリスト
    
    for ( int i = 0; i < temporary.size(); i++){  //temporaryの要素の数だけfor文を回す
      IdList.add(temporary.get(i).getId());  //IdListに登録済みのUser_idを格納
    }

    while (IdList.contains(Id)){  //createメソッドから受け取ったIdをIdList内に含んでいればもう一度生成を行う
      Id = new java.util.Random().nextInt(1000000000) + 1000000000;  //10億～20億でランダムに値を生成
    } 

    return Id;  //重複していないuser_idを返す
  }


}
