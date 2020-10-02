package com.example.demo.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "problem")
@Data
public class Problem {

  public Problem() {
  }

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "problem_id")
  private Diary diary;

  @Id
  @Column(nullable = false, unique = true, name = "problem_id")
  private String problemId;

  @Column
  private String problem;

  @Column
  private String factor;

  @Column
  private String solution;

  @Column
  private String situation;
}
