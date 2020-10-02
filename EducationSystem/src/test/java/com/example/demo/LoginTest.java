package com.example.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class, })
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class LoginTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する英字ユーザ名でログインできる() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("abcdefgh@skywill.jp").password("abcdefgh"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("index"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void ひらがなユーザ名はログインできない() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("あ@skywill.jp").password("ああああああああ"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void カタカナユーザ名はログインできない() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("アskywill.jp").password("アアアアアアアア"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void 漢字ユーザ名はログインできない() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("亜@skywill.jp").password("亜亜亜亜亜亜亜亜"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void 環境依存文字ユーザ名はログインできない() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("㌔@skywill.jp").password("㌔㌔㌔㌔㌔㌔㌔㌔"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void 正常系と異常系複合ユーザ名はログインできない() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("0a%あア亜㌔!@skywill.jp").password("0a%あア亜㌔!"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する利用者ユーザに正しいユーザ名と正しいパスワードを入力してログインできる() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("aoki@skywill.jp").password("aokiaoki"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("index"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する利用者ユーザの正しいユーザ名と誤ったパスワードを入力してエラーになる() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("aoki@skywill.jp").password("password"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する利用者ユーザの誤ったユーザ名と正しいパスワードを入力してエラーになる() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("sw@skywill.jp").password("aokiaoki"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する利用者ユーザの誤ったユーザ名と誤ったパスワードを入力してエラーになる() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("sw@skywill.jp").password("pass"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在するADMINユーザはadminにアクセスする() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("yamaguti@skywill.jp").password("yamaguti"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("admin"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在するTUTORユーザはtutorにアクセスする() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("sakakibara@skywill.jp").password("sakakibara"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("tutor"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する一般ユーザはindexにアクセスする() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("aoki@skywill.jp").password("aokiaoki"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("index"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在する閲覧ユーザはindexにアクセスする() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("horimoto@skywill.jp").password("horimoto"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("viewer"));
  }

  @Test
  @DatabaseSetup(value = "/login/")
  void DB上に存在するユーザ名とパスワードでパスワードが8文字未満() throws Exception {
    this.mockMvc.perform(formLogin("/login").user("murata@skywill.jp").password("murata"))
        .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login-error"));
  }

}
