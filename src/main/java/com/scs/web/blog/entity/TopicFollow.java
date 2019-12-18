package com.scs.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TopicFollow
 * @Description 关注专题实体类
 * @Author xiaotaoqi
 * @Date 2019/11/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicFollow {
    private Long id;
    private Long userId;
    private Long topicId;
}
