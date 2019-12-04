package com.niit.web.blog.factory;

import com.niit.web.blog.service.impl.ArticleServiceImpl;
import com.niit.web.blog.service.impl.TopicServiceImpl;
import com.niit.web.blog.service.impl.UserServiceImpl;


/**
 * @author xiaotaoqi
 * @ClassName ServiceFactory
 * @Description Service工厂类
 * @Date 10:56 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {
    public static UserServiceImpl getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static ArticleServiceImpl getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }

    public static TopicServiceImpl getTopicServiceInstance() {
        return new TopicServiceImpl();
    }

}
