package com.example.demo.Controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Data.LoginAccount;
import com.example.demo.Login.UserAccount;
import com.example.demo.Profile.UserEditService;
import com.example.demo.Profile.UserUpdateRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

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

    // user_Id={id}のユーザの編集処理
    @RequestMapping(value = "profile/edit/{id}")
    public String displayEdit(@AuthenticationPrincipal UserAccount ac, @PathVariable Integer id, Model model) {
        // テーブル結合したDetailとProfileを含めたプロフィール情報のセット
        LoginAccount login = EditService.findById(id);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(login.getId());
        userUpdateRequest.setName(login.getProfile().getName());
        userUpdateRequest.setFurigana(login.getProfile().getFurigana());
        userUpdateRequest.setProfileimage(
                login.getId() + "_" + login.getProfile().getUnit() + "_" + login.getProfile().getName() + ".jpg"); // ファイル名の指定
        userUpdateRequest.setAge(login.getDetail().getAge());
        userUpdateRequest.setPlace(login.getDetail().getPlace());
        userUpdateRequest.setSchool(login.getDetail().getSchool());
        userUpdateRequest.setHobby(login.getDetail().getHobby());
        userUpdateRequest.setComment(login.getDetail().getComment());

        Integer userid = ac.getId();
        String name = ac.getProfile().getName();
        String role = ac.getRole();
        String image = ac.getDetail().getProfileimage();
        model.addAttribute("role", role);
        model.addAttribute("id", userid);
        model.addAttribute("name", name);
        model.addAttribute("image", image);
        model.addAttribute("title", "プロフィール編集");

        // 値を渡す
        model.addAttribute("userUpdateRequest", userUpdateRequest);
        return "profile/edit";
    }

    // user_Id={id}のユーザの情報更新処理
    @RequestMapping(value = "/profile/update/{id}", method = RequestMethod.POST)
    public String update(@AuthenticationPrincipal UserAccount ac, @PathVariable Integer id,
            @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, MultipartFile multipartFile,
            Model model) {

        if (!multipartFile.isEmpty()) {
            try {
                // ファイル名のリネイム
                // File oldFileName = new File(multipartFile.getOriginalFilename());
                // File newFileName = new File(
                //         ac.getId() + "_" + ac.getProfile().getUnit() + "_" + ac.getProfile().getName() + ".jpg");
                // oldFileName.renameTo(newFileName);

                StringBuffer data = new StringBuffer();

        //submitされた画像データストリームを取得する
        InputStream is = multipartFile.getInputStream();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] indata = new byte[10240*16];
        int siz;
        //バイト配列に変換
        while( ( siz = is.read(indata, 0, indata.length) ) > 0 ) {
            os.write( indata, 0, siz );
        }
        //画像データをbase64エンコードする
        String base64 = new String(Base64.encodeBase64(os.toByteArray()), "ASCII");

        //画像タイプはJPEG固定
        data.append("data:image/jpeg;base64,");
        data.append(base64);

        //エンコードデータを、データモデルにセット（view の <img src= プロパティに反映）
        model.addAttribute("base64data", data.toString());
        
                // 保存先を定義
                String uploadPath = "src/main/resources/static/img/profile/";
                // byte[] bytes = multipartFile.getBytes();
 
                // 指定ファイルへ読み込みファイルを書き込み
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadPath + ac.getId() + "_" + ac.getProfile().getUnit() + "_" + ac.getProfile().getName())));
                stream.write(base64.getBytes());
                stream.close();

            } catch (Exception e) {
                System.out.println(e);
            }
  
        }

        // エラー処理
        if (result.hasErrors()) {
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
