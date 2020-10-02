package com.example.demo.News;

import java.security.Timestamp;

/* import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size; */

import lombok.Data;

//edit.htmlより値を受け取る
  @Data
  public class NewsEditRequest{
    
    private Integer newsId;

    // @NotEmpty(message = "タイトルを入力してください")
    // @Size( max = 30, message = "タイトルは30桁文字以内で入力してください")
    private String title;

    // @NotEmpty(message = "本文を入力してください")
    private String contents; 


    private String image;
    private Timestamp timestamp;
}