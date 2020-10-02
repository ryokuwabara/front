package com.example.demo.Controller;

import com.example.demo.Login.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DiaryController {

  //個人の日報が一覧になっているページ
  @RequestMapping(value = "/diary/mylist/{id}",  method = {RequestMethod.GET, RequestMethod.POST})
  public String mylist(@PathVariable ("id") final Integer id,
    @AuthenticationPrincipal UserAccount ac, Model model) {

    Integer userId = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("id", userId);
    model.addAttribute("name", name);
    model.addAttribute("role", role);
    model.addAttribute("title", "日報一覧");
    return "diary/mylist";
  }

  
  //管理側の日報を登録したユーザーの名前が一覧になっているページ
  @RequestMapping(value = "/diary/userlist",  method = {RequestMethod.GET, RequestMethod.POST})
  public String userlist(@AuthenticationPrincipal UserAccount ac, Model model) {

    Integer userId = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("id", userId);
    model.addAttribute("name", name);
    model.addAttribute("role", role);
    model.addAttribute("title", "受講者一覧");
    return "diary/userlist";
  }

  //日報作成ページ
  @RequestMapping(value = "/diary/new",  method = {RequestMethod.GET, RequestMethod.POST})
  public String create(@AuthenticationPrincipal UserAccount ac, Model model) {

    Integer userId = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("id", userId);
    model.addAttribute("name", name);
    model.addAttribute("role", role);
    model.addAttribute("title", "日報作成");
    return "diary/new";
  }

  //コメントしたりなど、日報を閲覧するページ
  @RequestMapping(value = "/diary/view",  method = {RequestMethod.GET, RequestMethod.POST})
  public String view(@AuthenticationPrincipal UserAccount ac, Model model) {

    Integer userId = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("id", userId);
    model.addAttribute("name", name);
    model.addAttribute("role", role);
    model.addAttribute("title", "日報閲覧");
    return "diary/view";
  }
}