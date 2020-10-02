package com.example.demo.Controller;

import com.example.demo.Profile.*;
import com.example.demo.Data.*;
import com.example.demo.Login.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

  @Autowired
  UserEditService EditService;

  //user_Id={id}の値を持つユーザのプロフィール表示
  @RequestMapping(value = "profile/user/{id}", method = RequestMethod.GET)
  public String index(@AuthenticationPrincipal UserAccount ac, @PathVariable Integer id, Model model) {
    LoginAccount login = EditService.findById(id);
    Integer userid = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", userid);
    model.addAttribute("name", name);
    model.addAttribute("login", login);
    model.addAttribute("title", "プロフィール");
    return "profile/user";

  }
}
