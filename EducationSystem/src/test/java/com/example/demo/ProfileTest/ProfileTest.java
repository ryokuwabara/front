package com.example.demo.ProfileTest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.demo.Data.LoginAccount;
import com.example.demo.Profile.UserEditService;
import com.example.demo.Profile.UserUpdateRequest;
import com.example.demo.UserManagement.EditService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class, })
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ProfileTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserEditService userEditService;

    @Autowired
    private EditService editService;

    @Test
    @DatabaseSetup(value = "/profile/")
        void testLoginOk1() throws Exception {
        // ログインテスト
        // dddd@skywill.jpとpasswordでadminに飛べるかどうか
        this.mockMvc.perform(formLogin("/login").user("dddd@skywill.jp").password("password"))
            .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("admin"));
    }

    @Test
    void プロフィール編集できる() throws Exception {
        // データ更新テスト
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

        List<LoginAccount> users = editService.findAll(); //データの全件取得
        Integer userId = users.get(0).getId(); //idを取得

        Integer updateAge = 55; //更新後データの定義

        //UpdateRequestに値を格納
        userUpdateRequest.setId(userId); 
        userUpdateRequest.setAge(updateAge);
        userEditService.update(userUpdateRequest); //データの更新

        LoginAccount user = userEditService.findById(userId); //変更したはずのidに関連したデータを取得
        LoginAccount us = null; 
        for(LoginAccount u : users){
            if(user.getDetail().getAge().equals(updateAge)) { //AgeがupdateAgeに変更されてるかチェック
                us = u; //データが変更されているとusに値が格納
            }
        }
        assertNotNull(us); //nullの判別
    }
}