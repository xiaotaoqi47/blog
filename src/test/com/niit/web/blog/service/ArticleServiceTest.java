package com.niit.web.blog.service;

import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.util.Result;

import org.junit.Test;

public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void getHotArticles() {
       Result result =  articleService.getHotArticles();
        System.out.println(result.getData());
    }

    @Test
    public void getPageArticles() {
    }

    @Test
    public void getArticle() {
    }
}