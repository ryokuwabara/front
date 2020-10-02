package com.example.demo.NewsTest;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.CsvDataSetLoader;
import com.example.demo.Data.News;
import com.example.demo.News.NewsEditRequest;
import com.example.demo.News.NewsEditService;
import com.example.demo.News.NewsRequest;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class, })
@AutoConfigureMockMvc
@SpringBootTest
@Transactional

public class NewsTest {
  @Autowired
  NewsEditService newsEditService;

  // @Autowired
  // EditService editService;

  @Test
  void Newsの新規登録ができる() throws Exception {

    // 登録するデータを定義
    Integer Newsid = 44;
    String Title = "タイトル";
    String Contents = "コンテンツ";
    List<News> users = newsEditService.findAll(); // 全件取得
    Integer id = users.get(0).getUserid(); // 登録されている1件目のIDを取得

    NewsRequest request = new NewsRequest();
    request.setId(id);
    request.setNewsId(Newsid);
    request.setTitle(Title);
    request.setContents(Contents);
    newsEditService.create(request);

    List<News> user = newsEditService.findAll(); // 全件取得
    News testAccount = null;

    System.out.println(user);

    for (News u : user) {

      if (u.getTitle().equals(Title)) {
        testAccount = u;
      }
    }

    assertNotNull(testAccount);

  }

  @Test
  void タイトルを編集できる() throws Exception {

    NewsEditRequest editRequest = new NewsEditRequest();
    String updateTitle = "Title"; // 変更するタイトルを定義
    List<News> news = newsEditService.findAll(); // 全件取得
    Integer id = news.get(0).getNewsId(); // 登録されている1件目のIDを取得

    // editRequestにそれぞれ値をセットする
    editRequest.setNewsId(id);
    editRequest.setTitle(updateTitle);
    editRequest.setContents("Contents");
    // データベースを更新
    newsEditService.update(editRequest);

    News user = newsEditService.findById(id); // 取得したIDに対応するレコードを取得
    News flag = null; // 空のNews型のflagを定義

    // 更新したデータの名前と最初の定義したタイトルが一致していれば先ほど定義した空のflagに代入
    if (user.getTitle().equals(updateTitle)) {
      flag = user;
    }
    assertNotNull(flag); // flagがNull出ないことを確認

  }

  @Test
  void コンテンツを編集できる() throws Exception {

    NewsEditRequest editRequest = new NewsEditRequest();
    String updateContents = "コンテンツ"; // 変更するコンテンツを定義
    List<News> news = newsEditService.findAll(); // 全件取得
    Integer id = news.get(0).getNewsId(); // 登録されている1件目のIDを取得

    // editRequestにそれぞれ値をセットする
    editRequest.setNewsId(id);
    editRequest.setTitle("Title");
    editRequest.setContents(updateContents);
    // データベースを更新
    newsEditService.update(editRequest);

    News user = newsEditService.findById(id); // 取得したIDに対応するレコードを取得
    News flag = null; // 空のNews型のflagを定義

    // 更新したデータの名前と最初の定義したコンテンツが一致していれば先ほど定義した空のflagに代入
    if (user.getContents().equals(updateContents)) {
      flag = user;
    }
    assertNotNull(flag); // flagがNull出ないことを確認

  }

  @Test
    void ユーザ削除できる() throws Exception {

        List<News> news = newsEditService.findAll(); //全件取得
        Integer id = news.get(0).getNewsId(); //1件目のIDを取得
        newsEditService.delete(id); //データの削除

        //削除されているかの確認
        try {
        News delNews= newsEditService.findById(id); //値が受け取れない
        System.out.println(delNews);
      } catch (NoSuchElementException ex) { //例外によって削除を確認
          assertNotNull(ex); //削除されていればOK
      }
    }
  }
