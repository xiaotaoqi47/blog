package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.dto.UserAvatarDto;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.dto.UserUpdateDto;
import com.scs.web.blog.domain.vo.HotUserVO;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.listener.MySessionContext;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.HttpUtil;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import com.scs.web.blog.util.UrlPatten;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName UserController
 * @Description 用户控制器
 * @Date 2019/11/9
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*",})
public class UserController extends HttpServlet {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if (UrlPatten.USER.equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            String userId = req.getParameter("userId");
            String f_user  =req.getParameter("f_user") ;
            if (userId != null && !"0".equals(userId)) {
                HttpUtil.getResponseBody(resp, userService.getUserInfo(Long.valueOf(userId)));
            }
            if (page != null) {
                HttpUtil.getResponseBody(resp, userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            } else if (keywords != null) {
                HttpUtil.getResponseBody(resp, userService.selectByKeywords(keywords));
            } else {
                List<User> userList = userService.getHotUsers();
                List<HotUserVO> hotUserVOList = new ArrayList<>();
                for (User user : userList) {
                    try {
                        HotUserVO hotUserVO = new HotUserVO();
                        BeanUtils.copyProperties(hotUserVO, user);
                        long n = 0 ;
                        if (f_user.equals(" ")){
                            n = 0 ;
                        }else{
                            n = Long.parseLong(f_user) ;
                        }
                        hotUserVO.setFlag(userService.selectUserFnas(n, user.getId()));
                        hotUserVOList.add(hotUserVO);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                HttpUtil.getResponseBody(resp, Result.success(hotUserVOList));
            }
        } else {
            System.out.println(uri);
            HttpUtil.getResponseBody(resp, userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case UrlPatten.USER_SIGN_IN:
                signIn(req, resp);
                break;
            case UrlPatten.USER_SIGN_UP:
                signUp(req, resp);
                break;
            case UrlPatten.USER_CHECK_MOBILE:
                String mobile = req.getParameter("mobile");
                HttpUtil.getResponseBody(resp, userService.checkMobile(mobile));
                break;
            case UrlPatten.USER_UPDATE:
                updateInfo(req, resp);
            case UrlPatten.USER_CHANGE_AVATAR:
                updateAvatar(req, resp);
                break;
            default:
        }
    }
    private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("修改用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserAvatarDto userAvatarDto = gson.fromJson(requestBody, UserAvatarDto.class);
        HttpUtil.getResponseBody(resp,userService.updateAvatar(userAvatarDto.getUserId(), userAvatarDto.getAvatar()));
    }

    private void updateInfo(HttpServletRequest req, HttpServletResponse resp) {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("修改用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();

        UserUpdateDto userUpdateDto = gson.fromJson(requestBody, UserUpdateDto.class);
        HttpUtil.getResponseBody(resp, userService.updateInfo(userUpdateDto));
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        //客户端输入的验证码
        String inputCode = userDto.getCode().trim();
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        //从自定义的监听代码中取得之前的session对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        //取得当时存入的验证码
//        String correctCode = session.getAttribute("code").toString();
        //忽略大小写比对
        if (/*inputCode.equalsIgnoreCase(correctCode)*/ true) {
            HttpUtil.getResponseBody(resp, userService.signIn(userDto));
            //验证码正确，进入登录业务逻辑调用
        } else {
            //验证码错误，直接将错误信息返回给客户端，不要继续登录流程了
            HttpUtil.getResponseBody(resp, Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }
    }


    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("用户注册信息：{}", requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        HttpUtil.getResponseBody(resp, userService.signUp(userDto));
    }
}
