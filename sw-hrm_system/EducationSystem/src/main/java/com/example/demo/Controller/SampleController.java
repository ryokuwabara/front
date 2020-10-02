package com.example.demo.Controller;

import com.example.demo.Login.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
  @RequestMapping(value = "/admin/sample1", method = RequestMethod.GET)
  public String as1(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル1");
    return "admin/sample1";
  }

  @RequestMapping(value = "/admin/sample2", method = RequestMethod.GET)
  public String as2(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル2");
    return "admin/sample2";
  }

  @RequestMapping(value = "/admin/sample3", method = RequestMethod.GET)
  public String as3(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル3");
    return "admin/sample3";
  }

  @RequestMapping(value = "/admin/sample4", method = RequestMethod.GET)
  public String as4(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル4");
    return "admin/sample4";
  }

  @RequestMapping(value = "/admin/sample5", method = RequestMethod.GET)
  public String as5(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル5");
    return "admin/sample5";
  }

  @RequestMapping(value = "/index/sample1", method = RequestMethod.GET)
  public String is1(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル1");
    return "index/sample1";
  }

  @RequestMapping(value = "/index/sample2", method = RequestMethod.GET)
  public String is2(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル2");
    return "index/sample2";
  }

  @RequestMapping(value = "/index/sample3", method = RequestMethod.GET)
  public String is3(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル3");
    return "index/sample3";
  }

  @RequestMapping(value = "/index/sample4", method = RequestMethod.GET)
  public String is4(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル4");
    return "index/sample4";
  }

  @RequestMapping(value = "/index/sample5", method = RequestMethod.GET)
  public String is5(@AuthenticationPrincipal UserAccount ac, Model model) {
    Integer id = ac.getId();
    String name = ac.getProfile().getName();
    String role = ac.getRole();
    model.addAttribute("role", role);
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("title", "サンプル5");
    return "index/sample5";
  }
}