package com.scs.web.blog.service;

import com.scs.web.blog.entity.ArticleAdd;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.Result;
import org.junit.Test;

public class ArticleAddServiceTest {
    private ArticleAddService articleAddService = ServiceFactory.getArticleAddServiceInstance();
    @Test
    public void addArticle() {
        ArticleAdd article = new ArticleAdd();
        article.setUserId((long)4);
        article.setTopicId((long)4);
        article.setThumbnail("缩略图");
        article.setTitle("标题");
        article.setContent("内容");
        article.setSummary("摘要");
        Result result = articleAddService.addArticle(article);
        System.out.println(result);
    }
}