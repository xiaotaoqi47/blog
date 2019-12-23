package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.CommentDto;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {

    /**
     * 查询所有的评论
     * @param article_id
     * @return
     */
     List<CommentDto> selectAll(long article_id) throws SQLException;

    /**
     *
     * @param artcle_id
     * @param user_id
     * @param content
     * @return
     * @throws SQLException
     */
     boolean addComment(long artcle_id, long user_id, String content) throws SQLException;


    /**
     *
     * @param user_id
     * @param artcle_id
     * @param index
     * @return
     * @throws SQLException
     */
    boolean deleteComment(long user_id, long artcle_id, int index) throws SQLException;
}

