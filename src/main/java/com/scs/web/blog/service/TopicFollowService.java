package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

public interface TopicFollowService {
    /**
     *
     * @param user_id
     * @param article_id
     * @return
     */
    Result addTopicFollow(long user_id, long article_id) ;

    /**
     *
     * @param user_id
     * @param article_id
     * @return
     */
    Result deleteTopicFollow(long user_id, long article_id) ;

    /**
     *
     * @param user_id
     * @param article_id
     * @return
     */
    Result selectTopicFollow(long user_id, long article_id) ;

}
