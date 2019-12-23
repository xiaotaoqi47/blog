package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.LikeDao;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDaoimpl implements LikeDao {
    private static Logger logger = LoggerFactory.getLogger(FollowDaoImpl.class);
    @Override
    public boolean selectByArticleAndUser(long articleId, long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;
        String sql = " select * from t_like where article_id = ? and user_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, articleId);
        pst.setLong(2, userId);
        ResultSet rs = pst.executeQuery();
        if(rs.next() ){
            System.out.println(rs);
            success = true ;
            System.out.println(success);
        }
        DbUtil.close(connection, pst, rs);
        return success ;
    }

    @Override
    public boolean addByArticleAndUser(long articleId, long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;
        String sql = " insert into t_like (user_id, article_id) values (? ,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        pst.setLong(2, articleId);
        int rs =pst.executeUpdate();
        if (rs > 0 ) {
            success= true ;
        }
        DbUtil.close(connection, pst);
        return success ;
    }

    @Override
    public boolean deleteByArticleAndUser(long articleId, long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean success = false ;

        String sql = " delete from t_like where user_id=? and article_id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        pst.setLong(2, articleId);
        int rs = pst.executeUpdate();

        if (rs > 0 ){
            success = true ;
        }
        DbUtil.close(connection, pst);
        return  success ;
    }
}
