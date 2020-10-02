package com.example.demo.Data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name = "news")
@Data
   public class News { //implements Serializable
        //private static final long serialVersionUID = 1L; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "news_id")
    private Integer newsId;
    
    @Column(nullable = false, unique = true, name = "user_id")
    private Integer userid;
    
    @Column(nullable = false,  name="title")
    private String title;

    @Column(nullable = false, name="contents")
    private String contents;
    
    @Column( name="newsimage")
    private String image;

    @Column(nullable = false, name = "newsupdate")
    private Timestamp timestamp;

    @Column(nullable = false)
    private boolean newsenabled;


    // 更新メソッド
    public void update(String title,  String contents, String image,Timestamp timestamp) {
        setNewsId(0);
        setUserid(userid);
        setTitle(title);
        setContents(contents);
        setImage(image);  
        setTimestamp(timestamp);
        setNewsenabled(true);
    }
	
}
