package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.FollowDao;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.entity.UserFollow;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FollowDaoIpl
 * @Description TODO
 * @Author mq_xu
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

    @Override
    public int unfollow(long fromId, long toId) {
        return 0;
    }

    @Override
    public int follow(long fromId, long toId) {
        return 0;
    }

    @Override
    public boolean isFollow(long fromId, long toId) {
        Connection connection = DbUtil.getConnection();
        String sql = "select count(*) from t_user_follow where from_id = ? and to_id = ? ";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setObject(1, fromId);
            pst.setObject(2, toId);
            ResultSet rs = pst.executeQuery();
            DbUtil.close(connection, pst);
            List<UserFollow> userFollows = BeanHandler.convertUserFollow(rs);
            if (userFollows != null && userFollows.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
