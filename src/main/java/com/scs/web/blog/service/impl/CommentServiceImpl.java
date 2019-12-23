package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.CommentService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);


    @Override
    public Result getComments(long article_id) {
        List<CommentDto> list = null;
        try {
            list = commentDao.selectAll(article_id);
        } catch (SQLException e) {
            logger.error("查询所有评论");
        }
        if (list != null) {
            return Result.success(list);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result addComment(long user_id, long article_id, String content) {
        Boolean suucess = new Boolean("false") ;
        try {
            suucess = commentDao.addComment(article_id , user_id,content);
        } catch (SQLException e) {
            logger.error("添加评论");
        }
        if (suucess) {
            return Result.success(suucess);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result deleteComment(long user_id, long article_id , int index  ) {
        Boolean suucess = new Boolean("false") ;
        try {
            suucess = commentDao.deleteComment(user_id ,article_id  , index);
        } catch (SQLException e) {
            logger.error("删除评论");
        }
        if (suucess) {
            return Result.success(suucess);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
