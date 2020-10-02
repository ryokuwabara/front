package com.example.demo.Diary;

import com.example.demo.Data.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class DiaryNewRequest implements Serializable{

    private static final long serialVersionUID = -680383416552706566L;

    private Timestamp update;
    private Work work;
    private Problem problem;
    private Diary_others diary_others;
    private Diary_view diary_view;

/*     private String work;
    private String content;
    private Integer time;
    private LocalDate deadline;
    private Integer progress;
    private String upadateFile; */ 

}