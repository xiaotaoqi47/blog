package com.scs.web.blog.domain.dto;

import lombok.Data;

@Data
public class WriteArticleDto {
    private int userId;
    private int topicId;
    private String title;
    private String summary;
    private String thumbnail;
    private String content;
}
