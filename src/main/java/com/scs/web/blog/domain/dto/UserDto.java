package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xiaotaoqi
 * @ClassName UserDto
 * @Description 用户传输对象
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class UserDto implements Serializable {
    private Long id;
    private String introduction;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private String code;
    private String gender;
    private String address;
    private String banner;
    private String email;
}
