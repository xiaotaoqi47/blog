package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Region;


import java.sql.SQLException;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName RegionDao
 * @Description RegionDao数据访问接口
 * @Date 10:54 2019/11/9
 * @Version 1.0
 **/
public interface RegionDao {

    /**
     *  查询所有地址
     *
     * @return
     */
    List<Region> selectAll()throws SQLException;
}
