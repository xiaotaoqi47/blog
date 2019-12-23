package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName UserDao
 * @Description UserDao数据访问对象接口
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 新增用户
     *
     * @param user
     * @throws SQLException
     */
    void insert(User user) throws SQLException;

    /**
     * 批量新增用户
     *
     * @param userList
     * @throws SQLException
     */
    void batchInsert(List<User> userList) throws SQLException;

    /**
     * 根据手机号查找用户
     *
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;

    /**
     * 查询热门用户
     *
     * @return
     * @throws SQLException
     */
    List<User> selectHotUsers() throws SQLException;


    /**
     * 查询分页用户
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<User> selectByPage(int currentPage, int count) throws SQLException;

    /**
     * 根据id查询用户
     * @param  id
     * @return
     * @throws SQLException
     */
    UserVo getUser(long id) throws SQLException;


    /**
     * 模糊搜索用户
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<User> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据用户id 获取用户
     * @param id
     * @return
     */
    User findUserById(Long id) throws SQLException;

    /**
     * 更新用户信息
     * @param avatar
     * @param nickname
     * @param mobile
     * @param md5Hex
     * @param gender
     * @param birthday
     * @param introduction
     */
    boolean updateInfo(int userId, String avatar, String nickname, String mobile, String md5Hex, String gender, LocalDate birthday, String introduction);

    /**
     *
     * @param f_userId
     * @param t_userId
     * @return
     */
    boolean addUserFans(long f_userId, long t_userId) throws SQLException;

    /**
     *
     * @param f_userId
     * @param t_userId
     * @return
     */
    boolean deleteUserFans(long f_userId, long t_userId) throws SQLException;

    /**
     *
     * @param f_userId
     * @param t_userId
     * @return
     */
    int selectUserFans(long f_userId, long t_userId) throws SQLException;



    void updateAvatar(int userId, String avatar);
}
