package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

import java.sql.SQLException;

/**
 * @author mq_xu
 * @ClassName ArticleService
 * @Description 文章服务层接口
 * @Date 2019/11/11
 * @Version 1.0
 **/
public interface ArticleService {
    /**
     * 获取热门文章
     *
     * @return
     */
    Result getHotArticles();

    /**
     * 获取分页文章
     *
     * @param currentPage
     * @param count
     * @return
     */
    Result getArticlesByPage(int currentPage, int count);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    Result getArticle(long id);


    /**
     * 根据标题或摘要模糊查询文章
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);

    /**
     * 添加喜欢
     * @param article_id
     * @param user_id
     * @return
     */
    Result addByArticleAndUser(long article_id, long user_id);

    /**
     * 取消喜欢
     * @param article_id
     * @param user_id
     * @return
     */
    Result deleteByArticleAndUser(long article_id, long user_id);

    /***
     * 查询是否喜欢
     * @param article_id
     * @param user_id
     * @return
     */
    Result selectByArticleAndUser(long article_id, long user_id) ;

    /**
     *
     * @param userId
     * @param topicId
     * @param title
     * @param summary
     * @param thumbnail
     * @param content
     */
    void addArticle(int userId, int topicId, String title, String summary, String thumbnail, String content);




    Result deleteArticle(Long article, long userid) throws SQLException;
}
