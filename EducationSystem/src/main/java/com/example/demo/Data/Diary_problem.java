package com.example.demo.Data;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "diary_problem")
@Data
public class Diary_problem {

  public Diary_problem() {
  }

  @Column(nullable = false, name = "diary_id")
  private String diaryId;

  @Column(nullable = false, name = "problem_id")
  private String problemId;
}

