package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Profile.*;
import com.example.demo.Data.*;
import com.example.demo.Login.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileEditController {
    @Autowired
    UserEditService EditService;

    /**
   * ユーザー編集画面を表示
   *
   * @param id    表示するユーザーID
   * @param model Model
   * @return ユーザー編集画面
   */

    //user_Id={id}のユーザの編集処理
    @RequestMapping(value = "profile/edit/{id}")
    public String displayEdit(@AuthenticationPrincipal UserAccount ac, @PathVariable Integer id, Model model) {
    //テーブル結合したDetailとProfileを含めたプロフィール情報のセット
        LoginAccount login = EditService.findById(id);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(login.getId());
        userUpdateRequest.setName(login.getProfile().getName());
        userUpdateRequest.setFurigana(login.getProfile().getFurigana());
        userUpdateRequest.setProfileimage(login.getDetail().getProfileimage());
        userUpdateRequest.setAge(login.getDetail().getAge());
        userUpdateRequest.setPlace(login.getDetail().getPlace());
        userUpdateRequest.setSchool(login.getDetail().getSchool());
        userUpdateRequest.setHobby(login.getDetail().getHobby());
        userUpdateRequest.setComment(login.getDetail().getComment());

        Integer userid = ac.getId();
        String name = ac.getProfile().getName();
        String role = ac.getRole();
        model.addAttribute("role", role);
        model.addAttribute("id", userid);
        model.addAttribute("name", name);
        model.addAttribute("title", "プロフィール編集");

        //値を渡す
        model.addAttribute("userUpdateRequest", userUpdateRequest);
        return "profile/edit";

    }

    //user_Id={id}のユーザの情報更新処理
    @RequestMapping(value = "/profile/update/{id}", method = RequestMethod.POST)
    public String update(@AuthenticationPrincipal UserAccount ac, @PathVariable Integer id, @Validated @ModelAttribute UserUpdateRequest userUpdateRequest,
        BindingResult result, Model model) {

        //エラー処理
    if (result.hasErrors()) {
        System.out.println(userUpdateRequest);
        List<String> errorList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
        }
        Integer userid = ac.getId();
        String name = ac.getProfile().getName();
        String role = ac.getRole();
        model.addAttribute("role", role);
        model.addAttribute("id", userid);
        model.addAttribute("name", name);
        model.addAttribute("title", "プロフィール編集");
        model.addAttribute("validationError", "入力内容に誤りがあります");
        return "profile/edit";
    }
    // ユーザー情報の更新
    EditService.update(userUpdateRequest);
    return String.format("redirect:/profile/user/{id}", userUpdateRequest.getId());
    }
}
