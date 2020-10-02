package com.example.demo.UserManagement;

import com.example.demo.*;
import com.example.demo.Data.*;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EditService  {

  @Autowired
  private LoginAccountRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  //データ全件取得
  public List<LoginAccount> findAll() {
    return repository.findAll();
  }

  //idを取得するメソッド
  public LoginAccount findById(Integer id) {
    return repository.findById(id).get();
  }

  //新規登録メソッド
  public void create(EditNewRequest editNewRequest){

    LoginAccount user = new LoginAccount();
    Profile profile = new Profile();
    Detail detail = new Detail();
    long millis = System.currentTimeMillis();  //現在時刻を取得
    Timestamp timestamp = new Timestamp(millis);  //timestamp型の変数へ

    //ユーザーIDの生成
    Integer UserId = new java.util.Random().nextInt(1000000000) + 1000000000;  //10億～20億でランダムに値を生成
    DataProcess DataProcess = new DataProcess();  //DataProcessのインスタンスを作成
    List<LoginAccount> temporary = this.findAll();  //データ全件を一時リストに格納
    UserId = DataProcess.idcheck(UserId, temporary);  //重複チェックのためidcheckメソッドに生成した値と一時リストを渡す

    //各テーブルのカラムに対応した値をセット
    user.setId(UserId);  //idcheckメソッドより帰ってきた値をloginテーブルのuser_idにセット
    user.setAccount(editNewRequest.getAccount());
    user.setRole(editNewRequest.getRole());
    user.setPassword(passwordEncoder.encode(editNewRequest.getPassword()));  //取得したパスワードを暗号化しセット
    user.setTimestamp(timestamp);
    user.setEnabled(true);
    user.setProfile(profile);  //全てのカラムが空のprofile型の変数をセット
    user.getProfile().setId(user.getId());  //profileテーブルのuser_idにloginテーブルのuser_idと同じ値セット
    user.getProfile().setName(editNewRequest.getProfile().getName());
    user.getProfile().setFurigana(editNewRequest.getProfile().getFurigana());
    user.getProfile().setEnter(editNewRequest.getProfile().getEnter());
    user.getProfile().setUnit(editNewRequest.getProfile().getUnit());
    user.getProfile().setSuperior(editNewRequest.getProfile().getSuperior());
    user.setDetail(detail);   //全てのカラムが空のDetail型の変数をセット
    user.getDetail().setId(user.getId());  ////Dtailテーブルのuser_idにloginテーブルのuser_idと同じ値セット
    user.getDetail().setProfileimage(editNewRequest.getDetail().getProfileimage());  
    repository.save(user);  //データベースに登録
  }

  //更新メソッド
  public void update(EditRequest EditRequest) {

    //edit.htmlより受け取ったIDを受け取る
    LoginAccount user = findById(EditRequest.getId());

    //受け取ったIDに対応したレコードを上書き
    user.setAccount(EditRequest.getAccount());
    user.setRole(EditRequest.getRole());
    user.getProfile().setName(EditRequest.getProfile().getName());
    user.getProfile().setFurigana(EditRequest.getProfile().getFurigana());
    user.getProfile().setUnit(EditRequest.getProfile().getUnit());
    user.getProfile().setEnter(EditRequest.getProfile().getEnter());
    user.getProfile().setSuperior(EditRequest.getProfile().getSuperior());
    repository.save(user);  //データベースの更新
  }

  //ユーザーデータの削除
  public void delete(Integer id) {

    LoginAccount user = findById(id);  //受け取ったIDに対応したレコードを取得
    repository.delete(user);  //データの削除
  }
  
}
