package com.example.demo.Controller;


import java.sql.Timestamp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
  // @Autowired
  // UserAccount sv;

  long millis = System.currentTimeMillis();
  Timestamp timestamp = new Timestamp(millis);

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(final Model model) {
    model.addAttribute("title", "ログイン");
    model.addAttribute("iserror", false);

    
    return "login";
  }

  @RequestMapping(value = "/login-error", method = RequestMethod.GET)
  public String loginError(final Model model) {

    model.addAttribute("title", "ログイン");
    model.addAttribute("iserror", true);
    return "login";
  }

}
