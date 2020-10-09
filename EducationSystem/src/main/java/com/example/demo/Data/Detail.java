  
package com.example.demo.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "detail")
@Data
public class Detail {

  public Detail() {
  }

  public Detail(Integer age, String place, String school, String hobby, String comment, String profileimage) {
    setId(0);
    setAge(age);
    setPlace(place);
    setSchool(school);
    setHobby(hobby);
    setComment(comment);
    setProfileimage(profileimage);
  }

  //主キー
  @Id
  @Column(nullable = false, unique = true, name = "user_id")
  private Integer id;

  @Column
  private Integer age;

  @Column
  private String place;

  @Column
  private String school;

  @Column
  private String hobby;

  @Column
  private String comment;

  //画像のURL
  @Column(nullable = false)
  private String profileimage;

  public Integer getId() {
    return id;
  }

}