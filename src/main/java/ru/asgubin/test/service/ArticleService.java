package ru.asgubin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.asgubin.test.entity.Articles;
import ru.asgubin.test.repository.ArticlesRepository;

@Service
public class ArticleService extends AbstractService<Articles, Long, ArticlesRepository> {

    @Autowired
    public ArticleService(ArticlesRepository repository) {
        super(repository);
    }
}