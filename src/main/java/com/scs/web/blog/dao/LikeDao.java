package com.scs.web.blog.dao;

import java.sql.SQLException;

public interface LikeDao {
    /**
     *
     * @param articleId
     * @param userId
     * @return
     * @throws SQLException
     */
    public boolean selectByArticleAndUser(long articleId, long userId) throws SQLException;

    /**
     *
     * @param articleId
     * @param userId
     * @throws SQLException
     */
    public boolean addByArticleAndUser(long articleId, long userId) throws SQLException;

    /**
     *
     * @param articleId
     * @param userId
     * @throws SQLException
     */

    public boolean deleteByArticleAndUser(long articleId, long userId) throws SQLException;



}
