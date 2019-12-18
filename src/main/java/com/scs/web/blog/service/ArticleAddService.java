package com.scs.web.blog.service;

import com.scs.web.blog.domain.dto.ArticleDto;
import com.scs.web.blog.entity.ArticleAdd;
import com.scs.web.blog.util.Result;

/**
 * @author xiaotaoqi
 * @ClassName ArticleAddService
 * @Description TODO
 * @Date 2019/12/5
 * @Version 1.0
 **/
public interface ArticleAddService {
    /**
     * 新增文章
     * @param articleAddDto
     * @return
     */
    Result addArticle(ArticleAdd articleAdd);
    Result upDate(ArticleDto articleDto);
}
