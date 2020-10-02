package com.example.demo.Controller;

import com.example.demo.Login.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewerController {

  //viewerアクセス時の処理
  @RequestMapping(value = "/viewer", method = RequestMethod.GET)
  public String admin(@AuthenticationPrincipal UserAccount ac, Model model) {
    //idと名前を取得し、それぞれ表示
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("role", role);
    model.addAttribute("title", "閲覧者");
    return "viewer";
  }
}

