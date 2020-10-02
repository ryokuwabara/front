package com.example.demo.News;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Data.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional(rollbackOn = Exception.class)
public class NewsEditService  {


    @Autowired
    private NewsRepository repository;

    @Autowired
    private LoginAccountRepository LArepository;

    // データ全件取得
    public List<News> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "newsId"));

    }
    public LoginAccount findbyId(Integer id) {
        return LArepository.findById(id).get();
    }

    // 新規登録メソッド
    public void create(NewsRequest newsRequest) {
        News news= new News();
        long millis = System.currentTimeMillis(); // 現在時刻を取得
        Timestamp timestamp = new Timestamp(millis); // timestamp型の変数へ
        news.setUserid(newsRequest.getId());
        System.out.println(newsRequest.getId());

        //news.setNewsId(newsRequest.getNewsId());
        //System.out.println(newsRequest.getNewsId());
        news.setTitle(newsRequest.getTitle());
        news.setContents(newsRequest.getContents());
        news.setImage(newsRequest.getImage());
        news.setTimestamp(timestamp);
        repository.save(news); // データベースに登録
    }
    //  主キー検索
    public News findById(Integer id) {
        return repository.findById(id).get();
       }

// 更新メソッド
    public void update(NewsEditRequest newsEditRequest) {
        News news = findById(newsEditRequest.getNewsId());
        long millis = System.currentTimeMillis(); // 現在時刻を取得
        Timestamp timestamp = new Timestamp(millis); // timestamp型の変数へ

        news.setTitle(newsEditRequest.getTitle());
        news.setContents(newsEditRequest.getContents());
        news.setImage(newsEditRequest.getImage());
        news.setTimestamp(timestamp);
        repository.save(news);
        }
        // 削除
        public void delete(Integer id) {
            News news = findById(id);
            repository.delete(news);
          }
    
    }