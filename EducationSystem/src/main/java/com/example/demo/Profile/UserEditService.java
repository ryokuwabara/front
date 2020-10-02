package com.example.demo.Profile;

import com.example.demo.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEditService {
  /**
   * ユーザー情報 Repository
   */
  @Autowired
  private LoginAccountRepository LARepository;

  /**
   * ユーザー情報 主キー検索
   *
   * @return 検索結果
   */
  public LoginAccount findById(Integer id) {
    return LARepository.findById(id).get();
  }

  /**
   * ユーザー情報 更新
   *
   * @param user ユーザー情報
   */
  public void update(UserUpdateRequest userUpdateRequest) {
    LoginAccount user = findById(userUpdateRequest.getId());
    user.getProfile().setName(userUpdateRequest.getName());
    user.getProfile().setFurigana(userUpdateRequest.getFurigana());
    user.getDetail().setProfileimage(userUpdateRequest.getProfileimage());
    user.getDetail().setAge(userUpdateRequest.getAge());
    user.getDetail().setPlace(userUpdateRequest.getPlace());
    user.getDetail().setSchool(userUpdateRequest.getSchool());
    user.getDetail().setHobby(userUpdateRequest.getHobby());
    user.getDetail().setComment(userUpdateRequest.getComment());
    //DBにセーブ
    LARepository.save(user);
  }
}
