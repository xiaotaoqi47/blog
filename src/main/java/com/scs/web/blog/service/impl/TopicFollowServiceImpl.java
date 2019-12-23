package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.TopicFollowDao;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.TopicFollowService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TopicFollowServiceImpl implements TopicFollowService {
    private TopicFollowDao topicFollowDao = DaoFactory.getTopicFollowDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public Result addTopicFollow(long user_id, long article_id) {
        Boolean suucess = new Boolean("false") ;
        try {
            suucess = topicFollowDao.addByTopicIdAndUserId(user_id , article_id);
        } catch (SQLException e) {
            logger.error("添加");
        }
        if (suucess) {
            return Result.success(suucess);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result deleteTopicFollow(long user_id, long article_id) {
        Boolean suucess = new Boolean("false") ;
        try {
            suucess = topicFollowDao.deleteByTopicIdAndUserId(user_id , article_id);
        } catch (SQLException e) {
            logger.error("删除");
        }
        if (suucess) {
            return Result.success(suucess);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectTopicFollow(long user_id, long article_id) {
        Boolean suucess = new Boolean("false") ;
        try {
            suucess = topicFollowDao.selctByTopicIdAndUserId(user_id , article_id);
        } catch (SQLException e) {
            logger.error("查询");
        }
        if (suucess) {
            return Result.success(suucess);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
