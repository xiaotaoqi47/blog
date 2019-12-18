package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaotaoqi
 * @ClassName ArticleDto
 * @Description TODO
 * @Date 2019/12/4:11:40
 * @Version 1.0
 **/
@Data
public class ArticleDto implements Serializable {
    private Long userId;
    private Long id;
    private Long topicId;
    private String title;
    private String summary;
    private String thumbnail;
    private String content;

}
