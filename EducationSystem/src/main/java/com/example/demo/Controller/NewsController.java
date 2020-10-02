package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Data.*;
import com.example.demo.Login.*;
import com.example.demo.News.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NewsController {

    @Autowired
    NewsEditService  newsEditService;

    @Autowired
    UserAccountService UserAccountService;

    // 一覧画面
    @GetMapping(value = "/news/list")
    public String displayList(Model model) {
        List<News> news = newsEditService.findAll();
       

        model.addAttribute("news", news);
       
        
        return "news/list";
    }
    // 削除
    // @DeleteMapping("{id}")
    // public String destroy(@PathVariable Integer id) {
    //     newsEditService.delete(id);
    //     return "redirect:/news";
    // }

    // 新規作成画面
    @GetMapping(value = "/news/add")
    public String displayNew(@AuthenticationPrincipal UserAccount ac, Model model) {
       
        Integer userId=ac.getId();
        // System.out.println(userId);
        model.addAttribute("userId",userId);
       // model.addAttribute("newsRequest", new NewsRequest());
        return "news/add";
    }

    // 新規作成
    @RequestMapping(value = "/news/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute NewsRequest newsRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for(ObjectError error : result.getAllErrors()) {
            errorList.add(error.getDefaultMessage());
            }
            
            model.addAttribute("validationError", errorList);
            return "news/add";
        }
        // ユーザー情報の登録
        newsEditService.create(newsRequest);
        return "redirect:/news/list";
    }
    
    // ユーザー編集画面を表示
    
    @GetMapping("/news/{id}/edit")
    public String Edit(@PathVariable ("id") final Integer id,final Model model) {
        News news = newsEditService.findById(id);
        model.addAttribute("newsRequest", news); 
        return "news/edit";
    }
    
    //   ユーザー更新
    
    @RequestMapping(value="/news/update", method=RequestMethod.POST)
    public String update(@Validated @ModelAttribute NewsEditRequest newsEditRequest, BindingResult result, Model model) {
        // if (result.hasErrors()) {
        //     List<String> errorList = new ArrayList<String>();
        //     for(ObjectError error : result.getAllErrors()) {
        //     errorList.add(error.getDefaultMessage());
        //     }
          
        //     model.addAttribute("validationError", errorList);
        //     return "news/edit";
        // }
        // ユーザー情報の更新
        newsEditService.update(newsEditRequest);
        return "redirect:/news/list"; 
    }
    //削除
    @RequestMapping(value = "news/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
  public String delete(@PathVariable ("id") final Integer id) {
      newsEditService.delete(id);
      return "redirect:/news/list";
  }
}