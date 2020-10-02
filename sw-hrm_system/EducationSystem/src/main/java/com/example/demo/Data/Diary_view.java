package com.example.demo.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "diary_view")
@Data
public class Diary_view {

  public Diary_view() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, name = "diary_id")
  private String diaryId;

  @Column(nullable = false)
  private String confirmation;

}

