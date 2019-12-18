package com.scs.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaotaoqi
 * @ClassName ArticleAdd
 * @Description TODO
 * @Date 2019/12/5:10:18
 * @Version 1.0
 **/
@Data
public class ArticleAdd {
    private Long id;
    private Long userId;
    private Long topicId;
    private String title;
    private String summary;
    private String thumbnail;
    private String content;
    private Integer likes;
    private Integer comments;
    private LocalDateTime createTime;
}
