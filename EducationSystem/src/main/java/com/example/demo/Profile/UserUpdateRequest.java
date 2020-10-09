  
package com.example.demo.Profile;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
// UserRequestを継承
public class UserUpdateRequest implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7468420156957582173L;
  private Integer id;
  private String name;
  private String furigana;
  private String profileimage;
  private Integer age;
  private String place;
  private String school;
  private String hobby;
  private String comment;

}