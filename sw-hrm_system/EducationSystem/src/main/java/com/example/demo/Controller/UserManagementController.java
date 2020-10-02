package com.example.demo.Controller;

import com.example.demo.*;
import com.example.demo.UserManagement.*;
import com.example.demo.Data.*;
import com.example.demo.Login.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserManagementController {

  @Autowired
  LoginAccountRepository LoginAccountRepository;

  @Autowired
  private EditService EditService;

  //一覧画面
  @RequestMapping(value = "user-management/list", method = {RequestMethod.GET, RequestMethod.POST})
  public String list(@AuthenticationPrincipal UserAccount ac, final Model model) {

    List<LoginAccount> TemporaryData = EditService.findAll(); //取得した全データを一時リストへ格納
    DataProcess DataProcess = new DataProcess(); //ロールを日本語へ変換するためのインスタンスの作成
    List<LoginAccount> profile = DataProcess.convert(TemporaryData); //変換するメソッドへ一時リストを送り、戻ってきたデータを新たなリストへ追加

    Integer id = ac.getId();  //ログインしているユーザーのIDを取得
    String name = ac.getProfile().getName();  //ログインしているユーザーのネームを取得
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "ユーザー一覧");

    model.addAttribute("profiles", profile); // user-management/listへデータを受け渡す
    return "user-management/list";
  }

  //新規作成画面
  @RequestMapping(value = "user-management/new", method = {RequestMethod.GET, RequestMethod.POST})
  public String newUser(@AuthenticationPrincipal UserAccount ac, final Model model) {

    Integer id = ac.getId();  //ログインしているユーザーのIDを取得
    String name = ac.getProfile().getName();  //ログインしているユーザーのネームを取得
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "新規作成");

    return "user-management/new";
  }

  //新規作成
  @PostMapping("user-management/create")
  public String create(@ModelAttribute final EditNewRequest editNewRequest) {

    EditService.create(editNewRequest); //CreateRequestより受け取ったデータをupdateメソッドへ送る。
    return "redirect:/user-management/list"; ///user-management/listへリダイレクト
  }

  //編集画面
  @GetMapping("user-management/edit/{id}") //@PathVariableで{id}部分を動的に取得
  public String edit(@PathVariable ("id") final Integer id,
  @AuthenticationPrincipal UserAccount ac, final Model model) {

    LoginAccount UserProfile = EditService.findById(id); // 取得したIDに対応したデータを格納
    model.addAttribute("userprofile", UserProfile); // 格納したデータをuser-management/editへ受け渡す

    Integer userid = ac.getId();  //ログインしているユーザーのIDを取得
    String name = ac.getProfile().getName();  //ログインしているユーザーのネームを取得
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", userid);
    model.addAttribute("name", name);
    model.addAttribute("title", "ユーザー情報編集");

    return "user-management/edit";
  }

  //データ更新
  @PostMapping("user-management/update")
  public String update(@ModelAttribute final EditRequest Edit, BindingResult result, final Model model) {

    EditService.update(Edit); //EditRequestより受け取ったデータをupdateメソッドへ送る。
    return "redirect:/user-management/list"; ///user-management/listへリダイレクト
  }

  //削除機能
  @RequestMapping(value = "user-management/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
  public String delete(@PathVariable ("id") final Integer id) {
      EditService.delete(id);
      return "redirect:/user-management/list";
  }

}

