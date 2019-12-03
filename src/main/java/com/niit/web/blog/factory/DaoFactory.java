package com.niit.web.blog.factory;

import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.dao.RegionDao;
import com.niit.web.blog.dao.TopicDao;
import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.dao.impl.ArticleDaoImpl;
import com.niit.web.blog.dao.impl.RegionDaoImpl;
import com.niit.web.blog.dao.impl.TopicDaoImpl;
import com.niit.web.blog.dao.impl.UserDaoImpl;


/**
 * @author mq_xu
 * @ClassName DaoFactory
 * @Description Dao工厂类
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class DaoFactory {

    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static ArticleDao getArticleDaoInstance() {
        return new ArticleDaoImpl();
    }

    public static TopicDao getTopicDaoInstance() {
        return new TopicDaoImpl();
    }

    public static RegionDao getRegionDaoInstance() {
        return new RegionDaoImpl();
    }

}
