package com.scs.web.blog.dao.impl;


import com.scs.web.blog.dao.TopicFollowDao;
import com.scs.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicFollowDaoImpl implements TopicFollowDao {
    @Override
    public boolean selctByTopicIdAndUserId(long user_id, long topic_id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;
        String sql = " select * from t_topic_follow where user_id = ? and topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, user_id);
        pst.setLong(2, topic_id);
        ResultSet rs = pst.executeQuery();
        if(rs.next() ){
            success = true ;
        }
        DbUtil.close(connection, pst, rs);
        return success ;
    }

    @Override
    public boolean addByTopicIdAndUserId(long user_id, long topic_id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;
        String sql = " insert into t_topic_follow (user_id, topic_id) values (? ,?);" +
                "";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, user_id);
        pst.setLong(2, topic_id);
        int rs =pst.executeUpdate();
        if (rs > 0 ) {
            success= true ;
        }
        DbUtil.close(connection, pst);
        return success ;
    }

    @Override
    public boolean deleteByTopicIdAndUserId(long user_id, long topic_id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;
        String sql = "delete from t_topic_follow where user_id =? and  topic_id =?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, user_id);
        pst.setLong(2, topic_id);
        int rs =pst.executeUpdate();
        if (rs > 0 ) {
            success= true ;
        }
        DbUtil.close(connection, pst);
        return success ;
    }
}
