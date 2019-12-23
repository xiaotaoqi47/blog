package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName UserDaoImpl
 * @Description UserDao数据访问对象接口实现类
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public void insert(User user) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,birthday,create_time) VALUES (?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, user.getMobile());
        pst.setString(2, user.getPassword());
        pst.setObject(3, DataUtil.getCreateTime());
        pst.setObject(4, DataUtil.getCreateTime());
        pst.executeUpdate();
        DbUtil.close(connection, pst);
    }


    @Override
    public void batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_user (id,mobile,password,nickname,avatar,gender,birthday,address,introduction,banner,homepage,follows,fans,articles,create_time,status) VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        userList.forEach(user -> {
            try {
                pst.setString(1, user.getMobile());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getNickname());
                pst.setString(4, user.getAvatar());
                pst.setString(5, user.getGender());
                pst.setObject(6, user.getBirthday());
                pst.setString(7, user.getAddress());
                pst.setString(8, user.getIntroduction());
                pst.setString(9, user.getBanner());
                pst.setString(10, user.getHomepage());
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setObject(14, user.getCreateTime());
                pst.setShort(15, user.getStatus());
                pst.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入用户数据产生异常");
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection, pst);
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, mobile);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        User user = null;
        if (userList.size() != 0) {
            user = userList.get(0);
        }
        DbUtil.close(connection, pst, rs);
        return user;
    }

    @Override
    public List<User> selectHotUsers() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY fans DESC LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }

    @Override
    public List<User> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }

    @Override
    public UserVo getUser(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        UserVo userVo = new UserVo();
        User user = BeanHandler.convertUser(rs).get(0);
        userVo.setUser(user);
        DbUtil.close(connection, pst, rs);
        return userVo;
    }

    @Override
    public List<User> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user " +
                "WHERE nickname LIKE ?  OR introduction LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }

    @Override
    public User findUserById(Long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = " + userId;
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return new User();
    }

    @Override
    public boolean updateInfo(int userId, String avatar, String nickname, String mobile, String password, String gender, LocalDate birthday, String introduction) {
        Connection connection = DbUtil.getConnection();
        try {
            String sql = "UPDATE `t_user` t " +
                    "SET t.`mobile` = ? , t.`password` = ? , t.`nickname` = ? , t.`avatar` = ? , t.`gender` = ? , t.`birthday` = ? , t.`introduction` = ? " +
                    " WHERE t.`id` = ? ";
            logger.info(sql);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, mobile);
            pst.setString(2, password);
            pst.setString(3, nickname);
            pst.setString(4, avatar);
            pst.setString(5, gender);
            pst.setObject(6, birthday);
            pst.setString(7, introduction);
            pst.setObject(8, userId);
            int i = pst.executeUpdate();
            DbUtil.close(connection, pst);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserFans(long f_userId, long t_userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean succuess = false;
        String sql = "UPDATE `t_user` t " +
                "SET t.`fans` = t.`fans` + 1 " +
                " WHERE t.`id` = ?;";
        String sql2 = "insert into t_user_follow (from_id, to_id) VALUES (? ,?);";
        logger.info(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        PreparedStatement pst2 = connection.prepareStatement(sql2);
        pst.setLong(1, t_userId);
        pst2.setLong(1, f_userId);
        pst2.setLong(2, t_userId);
        int i = pst.executeUpdate();
        int j = pst2.executeUpdate();
        DbUtil.close(connection, pst);
        DbUtil.close(connection, pst2);
        if (i > 0 && j > 0) {
            succuess = true;
        }
        DbUtil.close(connection, pst);
        DbUtil.close(connection, pst2);
        return succuess;
    }

    @Override
    public boolean deleteUserFans(long f_userId, long t_userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        boolean succuess = false;
        String sql = "UPDATE `t_user` t " +
                "SET t.`fans` = t.`fans` - 1 " +
                " WHERE t.`id` = ?;";
        String sql2 = "delete from t_user_follow where from_id=? and to_id=?";
        logger.info(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        PreparedStatement pst2 = connection.prepareStatement(sql2);
        pst.setLong(1, t_userId);
        pst2.setLong(1, f_userId);
        pst2.setLong(2, t_userId);
        int i = pst.executeUpdate();
        int j = pst2.executeUpdate();
        if (i > 0 && j > 0) {
            succuess = true;
        }
        DbUtil.close(connection, pst);
        DbUtil.close(connection, pst2);
        return succuess;
    }

    @Override
    public int selectUserFans(long f_userId, long t_userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        int succuess = 0;
        String sql = "select  * from t_user_follow where from_id=? and to_id=?";
        logger.info(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, f_userId);
        pst.setLong(2, t_userId);
        ResultSet res = pst.executeQuery();
        if (res.next()) {
            succuess = 1;
        }
        DbUtil.close(connection, pst, res);
        return succuess;
    }

    @Override
    public void updateAvatar(int userId, String avatar) {
        Connection connection = DbUtil.getConnection();
        String sql = "update t_user set avatar = ? where id = ? ";
        logger.info(sql);
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1, avatar);
            pst.setObject(2, userId);
            int i = pst.executeUpdate();
            DbUtil.close(connection, pst);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
