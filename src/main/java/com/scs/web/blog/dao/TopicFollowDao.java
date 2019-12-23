package com.scs.web.blog.dao;

import java.sql.SQLException;

public interface TopicFollowDao {
    /**
     *
     * @param user_id
     * @param topic_id
     * @return
     */
    boolean selctByTopicIdAndUserId(long user_id, long topic_id) throws SQLException;

    /**
     *
     * @param user_id
     * @param topic_id
     * @return
     */
    boolean addByTopicIdAndUserId(long user_id, long topic_id) throws SQLException;

    /**
     *
     * @param user_id
     * @param topic_id
     * @return
     */
    boolean deleteByTopicIdAndUserId(long user_id, long topic_id) throws SQLException;
}