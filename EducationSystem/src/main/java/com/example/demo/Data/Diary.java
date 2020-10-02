package com.example.demo.Data;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "diary")
@Data
public class Diary {

  public Diary() {
  }

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "diary_id")
  private Diary_others diary_others;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "diary_id")
  private Diary_view diary_view;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "diary_id")
  private Diary_comment diary_comment;

  @Id
  @Column(nullable = false, unique = true, name = "diary_id")
  private String diaryId;

  @Column(nullable = false, unique = true, name = "work_id")
  private String workId;

  @Column(nullable = false, unique = true, name = "user_id")
  private Integer userId;

  @Column(nullable = false, name = "updateday")
  private Timestamp update;

  @Column(nullable = false, name = "diaryenabled")
  private boolean diaryEnabled;

}
