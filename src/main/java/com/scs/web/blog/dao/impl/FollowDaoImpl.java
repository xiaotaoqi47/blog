package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.FollowDao;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FollowDaoIpl
 * @Description TODO
 * @Author xiaotaoqi
 * @Date 2019/11/29
 **/
public class FollowDaoImpl implements FollowDao {
    private static Logger logger = LoggerFactory.getLogger(FollowDaoImpl.class);

    @Override
    public List<User> getUserFollows(long userId) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUserFans(long userId) throws SQLException {
        return null;
    }

    @Override
    public List<Topic> getTopicFollows(long topicId) throws SQLException {
        return null;
    }
}
