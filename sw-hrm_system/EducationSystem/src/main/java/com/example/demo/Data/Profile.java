package com.example.demo.Data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile {

  public Profile() {
  }

  @Id
  @Column(nullable = false, unique = true, name = "user_id")
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String furigana;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate enter;

  @Column(nullable = false)
  private String unit;

  @Column(nullable = false)
  private String superior;


  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getFurigana() {
    return furigana;
  }

  public LocalDate getEnter(){
    return enter;
  }

  public String getUnit() {
    return unit;
  }

  public String getSuperior() {
    return superior;
  }


  public Profile(String name, String furigana, LocalDate enter, String unit, String superior) {
    setId(0);
    setName(name);
    setFurigana(furigana);
    setEnter(enter);
    setUnit(unit);
    setSuperior(superior);
  } 
}
