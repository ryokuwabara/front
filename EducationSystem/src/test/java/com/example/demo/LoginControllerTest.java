package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.Controller.LoginController;
import com.example.demo.Data.LoginAccountRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTest {

  // mockMvc TomcatサーバへデプロイすることなくHttpリクエスト・レスポンスを扱うためのMockオブジェクト
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  LoginAccountRepository repository;

  @InjectMocks
  LoginController controller;

  // getリクエストでviewを指定し、httpステータスでリクエストの成否を判定
  @Test
  public void login_アクセスできるか() throws Exception {
    this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
  }
}
