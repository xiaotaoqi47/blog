package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleAddDao;
import com.scs.web.blog.domain.dto.ArticleDto;
import com.scs.web.blog.entity.ArticleAdd;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ArticleAddService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author xiaotaoqi
 * @ClassName ArticleAddServiceImpl
 * @Description TODO
 * @Date 2019/12/5:10:30
 * @Version 1.0
 **/
public class ArticleAddServiceImpl implements ArticleAddService {
    private ArticleAddDao articleAddDao = DaoFactory.getArticleAddDaoInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleAddService.class);
    @Override
    public Result addArticle(ArticleAdd articleAdd) {
        int n = 0;
        try {
            articleAdd.setCreateTime(LocalDateTime.now());
            n = articleAddDao.insert(articleAdd);

            System.out.println(n);
        } catch (SQLException e) {
            logger.error("评论内容添加失败");
        }
        if(n != 0){
            return Result.success(n);
        }
        return Result.failure(ResultCode.ARTICLE_WRITER_FAIL);
    }

    @Override
    public Result upDate(ArticleDto articleDto) {
        int n = 0;
        try {
            n = articleAddDao.update(articleDto);
        } catch (SQLException e) {
            logger.error("修改文章出现异常");
        }
        if (n != 0) {
            return Result.success(n);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
