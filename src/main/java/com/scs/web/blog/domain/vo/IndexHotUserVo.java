package com.scs.web.blog.domain.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class IndexHotUserVo {
    private Long id;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String introduction;
    private String banner;
    private String email;
    private String homepage;
    private Integer follows;
    private Integer fans;
    private Integer articles;
    private LocalDateTime createTime;
    private Short status;
    private Integer isFollows;
}
