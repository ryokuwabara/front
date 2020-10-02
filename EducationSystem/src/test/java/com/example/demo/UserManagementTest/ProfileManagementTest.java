package com.example.demo.UserManagementTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.CsvDataSetLoader;
import com.example.demo.Data.*;
import com.example.demo.UserManagement.*;
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

public class ProfileManagementTest {

    @Autowired
    EditService editService;

    /*  @BeforeEach
    public void setup(){

        LoginAccount user = new LoginAccount("skywill@skywill.jp", "password", new Timestamp(65432345678L), "ADMIN");  //loginのインスタンス
        Profile profile = new Profile("テスト名前", "てすとふりがな", LocalDate.parse("2020-01-01"), "CA", "テストグループ");  //profileのインスタンス
        Detail detail = new Detail(20, "テスト出身", "テスト出身校", "テスト趣味", "テスト一言", "テスト画像");  //detailのインスタンス
        user.setProfile(profile);  //LoginAccountにセット
        user.setDetail(detail);  //LoginAccountにセット
        editService.create(user);
        List<LoginAccount> users = editService.findAll();
        System.out.println(users);

    } */

    @Test
    void ユーザ登録できる() throws Exception {

        //登録するデータを定義
        String Email = "00001@skywill.jp";
        Profile profile = new Profile("スカイ　ウイル", "すかい　ういる", LocalDate.parse("2020-01-01"), "CA", "田中");  //profileのインスタンス
        Detail detail = new Detail(20, "", "", "", "", "テスト画像");  //detailのインスタンス

        EditNewRequest editNewRequest = new EditNewRequest();
        editNewRequest.setAccount(Email);
        editNewRequest.setRole("USER");
        editNewRequest.setPassword("password");
        editNewRequest.setProfile(profile);  //LoginAccountにセット
        editNewRequest.setDetail(detail);  //LoginAccountにセット
        editService.create(editNewRequest);
        
        List<LoginAccount> users = editService.findAll();  //全件取得
        LoginAccount testAccount = null;

        for(LoginAccount u : users){

            if(u.getAccount().equals(Email)){
                testAccount = u;
            }
        }

        assertNotNull(testAccount);

    }

    @Test
    void FindByIdで任意のIDを取得できるか() {

        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        LoginAccount user = editService.findById(id);  //先ほど取得したIDに対応するレコードを取得
        assertEquals(id, user.getId());  //それぞれで取得したIDが一致しているか確認

    }

    @Test
    void 名前を編集できる() throws Exception {

        String updateName = "スカイ　ウイル子";  //変更する名前を定義
        Profile profile = new Profile(updateName, "てすとふりがな", LocalDate.parse("2020-01-01"), "CA", "テストグループ"); 
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole("USER");
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新したデータの名前と最初の定義した名前が一致していれば先ほど定義した空のflagに代入
        if(user.getProfile().getName().equals(updateName)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認

    }

    @Test
    void ふりがなを編集できる() throws Exception {

        String updateFurigana = "すかい　ういるこ";  //変更するふりがなを定義
        Profile profile = new Profile("テスト名前", updateFurigana, LocalDate.parse("2020-01-01"), "CA", "テストグループ");      
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole("USER");
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新したデータのふりがなと最初の定義したふりがなが一致していれば先ほど定義した空のflagに代入
        if(user.getProfile().getFurigana().equals(updateFurigana)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認

    }

    @Test
    void 入社日を編集できる() throws Exception {
        
        LocalDate updateEnter = LocalDate.parse("2021-01-01");  //変更する入社日を定義
        Profile profile = new Profile("テスト名前", "てすとふりがな", updateEnter, "CA", "テストグループ");      
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole("USER");
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新したデータの入社日と最初の定義した入社日が一致していれば先ほど定義した空のflagに代入
        if(user.getProfile().getEnter().equals(updateEnter)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認

    }

    @Test
    void 所属を編集できる() throws Exception {

        String updateUnit = "BM";   //変更するユニットを定義
        Profile profile = new Profile("テスト名前", "てすとふりがな", LocalDate.parse("2020-01-01"), updateUnit, "テストグループ");      
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();  
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole("USER");
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新したデータのユニットと最初の定義したユニットが一致していれば先ほど定義した空のflagに代入
        if(user.getProfile().getUnit().equals(updateUnit)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認

    }

    @Test
    void グループを編集できる() throws Exception {

        String updateSuperior = "鈴木";  //変更するグループを定義
        Profile profile = new Profile("テスト名前", "てすとふりがな", LocalDate.parse("2020-01-01"), "CA", updateSuperior);      
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole("USER");
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新したデータのグループと最初の定義したグループが一致していれば先ほど定義した空のflagに代入
        if(user.getProfile().getSuperior().equals(updateSuperior)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認

    }

    @Test
    void 権限を編集できる() throws Exception {

        String updateRole = "ADMIN";  //変更する権限を定義
        Profile profile = new Profile("テスト名前", "てすとふりがな", LocalDate.parse("2020-01-01"), "CA", "テストグループ");      
        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得

        //EditRequestにそれぞれ値をセットする
        EditRequest editRequest = new EditRequest();
        editRequest.setId(id);
        editRequest.setAccount("skywill@skywill.jp");
        editRequest.setRole(updateRole);
        editRequest.setProfile(profile);
        //データベースを更新
        editService.update(editRequest);

        LoginAccount user = editService.findById(id);  //取得したIDに対応するレコードを取得
        LoginAccount flag = null;  //空のLoginAccount型のflagを定義

        //更新した権限のグループと最初の定義した権限が一致していれば先ほど定義した空のflagに代入
        if(user.getRole().equals(updateRole)){
            flag = user;
        }
        assertNotNull(flag);  //flagがNullでないことを確認
    }

    @Test
    void ユーザ削除できる() throws Exception {

        List<LoginAccount> users = editService.findAll();  //全件取得
        Integer id = users.get(0).getId();  //登録されている1件目のIDを取得
        editService.delete(id);  //データの削除

        //削除されているかの確認
        try {
            LoginAccount delAccount= editService.findById(id); //nullではなく値が存在しない
            System.out.println(delAccount);
          } catch (NoSuchElementException ex) { //例外によって削除を確認
              assertNotNull(ex); //削除されていればnullが出ない
    }
}

}


