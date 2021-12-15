package com.boots.service;

import com.boots.entity.News;
import com.boots.repository.NewsRepository;
import com.boots.service.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    NewsRepository newsRepository;

    public boolean addArticle(News news) throws ArticleException {
        if (news == null){
            throw new ArticleException("Ошибка сохранения новости");
        }
        newsRepository.save(news);
        return true;
    }

    public boolean deleteArticle(News news) throws ArticleException {
        if (news == null){
            throw new ArticleException("Ошибка удаления новости");
        }
        newsRepository.delete(news);
        return true;
    }

    public boolean editArticle(News news){
        if (news == null){
            return false;
        }
        newsRepository.save(news);
        return true;
    }

    public boolean rateArticle(Long id, boolean rating){
        News target = getArticle(id);
        if (rating){
            target.setLikes(target.getLikes()+1);
        } else {
            target.setDislikes(target.getLikes()+1);
        }
        newsRepository.save(target);
        return true;
    }

    public boolean editArticle(Long id){
        newsRepository.save(getArticle(id));
        return true;
    }

    public List<News> getArticles(){
        return newsRepository.findAll();
    }

    public News getArticle(Long id){
        Optional<News> res = newsRepository.findById(id);
        return res.orElse(null);
    }
}
