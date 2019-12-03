package com.niit.web.blog.dao;


import com.niit.web.blog.domain.vo.TopicVo;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TopicDaoTest {
    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        topicDao.batchInsert(JSoupSpider.getTopics());
    }

    @Test
    public void selectHotTopics() throws SQLException {
        List<Topic> topicList = topicDao.selectHotTopics();
        System.out.println(topicList.size());
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<Topic> topicList = topicDao.selectByKeywords("小");
        System.out.println(topicList.size());
    }

    @Test
    public void getTopic() throws SQLException {
        TopicVo topicVo = topicDao.getTopic(1);
        System.out.println(topicVo);
    }
}