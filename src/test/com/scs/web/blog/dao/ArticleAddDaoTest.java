package com.scs.web.blog.dao;

import com.scs.web.blog.entity.ArticleAdd;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleAddDaoTest {
private ArticleAddDao articleAddDao = DaoFactory.getArticleAddDaoInstance();
    @Test
    public void insert() throws SQLException {
        ArticleAdd articleAdd = new ArticleAdd();
        articleAdd.setUserId((long)4);
        articleAdd.setTopicId((long)4);
        articleAdd.setTitle("标题");
        articleAdd.setSummary("摘要");
        articleAdd.setThumbnail("缩略图");
        articleAdd.setContent("文章内容");
        articleAdd.setLikes(0);
        articleAdd.setComments(0);
        articleAdd.setCreateTime(LocalDateTime.now());
        int n = articleAddDao.insert(articleAdd);
        System.out.println(n);
    }
    @Test
    public void selectAll() throws SQLException
    {
        List<ArticleAdd> articleAddList = articleAddDao.selectAll();
        articleAddList.forEach(System.out::println);
    }
}