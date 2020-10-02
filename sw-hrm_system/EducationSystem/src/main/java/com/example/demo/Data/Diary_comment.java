package com.example.demo.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "diary_comment")
@Data
public class Diary_comment {

  public Diary_comment() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, name = "diary_id")
  private String diaryId;

  @Column
  private String comment;

  @Column(name = "attachmentfile")
  private String commentFile;
}

