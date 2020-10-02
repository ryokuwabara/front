package com.example.demo.Data;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "work")
@Data
public class Work {

  public Work() {
  }

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "work_id")
  private Diary diary;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, name = "work_id")
  private String workId;

  @Column(nullable = false, name = "worktitle")
  private String workTitle;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Integer time;

  @Column(nullable = false)
  private LocalDate deadline;

  @Column(nullable = false)
  private Integer progress;

  @Column(nullable = false, name = "attachmentfile")
  private String updateFile;
}
