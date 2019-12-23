package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

/**
 * 评论接口
 */
public interface CommentService {

    /**
     *
     * @param article_id
     * @return
     */
    Result getComments(long article_id);

    /**
     *
     * @param user_id
     * @param article_id
     * @param content
     * @return
     */
    Result addComment(long user_id, long article_id, String content);


    /**
     *
     * @param user_id
     * @param article_id
     * @param index
     * @return
     */
    Result deleteComment(long user_id, long article_id, int index);
}
