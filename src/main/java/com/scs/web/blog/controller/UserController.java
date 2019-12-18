package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.listener.MySessionContext;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.ResponseObject;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user".equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getUsersByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getUsersByKeywords(resp, keywords);
            } else {
                getHotUsers(req, resp);
            }
        } else {
            getUser(req, resp);
        }
    }

    private void getHotUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.getHotUsers();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUsersByPage(HttpServletResponse resp, int page, int count) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.selectByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUsersByKeywords(HttpServletResponse resp, String keywords) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String info = req.getPathInfo().trim();
        String id = info.substring(info.indexOf("/") + 1);
        Gson gson = new GsonBuilder().create();
        Result result = userService.getUser(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        Gson gson = new GsonBuilder().create();
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder);
        UserDto user = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Result result = userService.upDate(user);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case "/api/user/sign-in":
                signIn(req, resp);
                break;
            case "/api/user/sign-up":
                signUp(req, resp);
                break;
            case "/api/user/check":
                check(req, resp);
                break;
            case "/api/user/change":
                update(req, resp);
                break;
            case "/api/user/care":
                String id = req.getParameter("id");
                String ur = req.getRequestURI().trim();
                System.out.println(ur);
                String iscare = req.getParameter("iscare");
                System.out.println(id);
                follow(resp, Long.parseLong(id), Integer.parseInt(iscare));
                break;
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        String inputCode = userDto.getCode().trim();
        String sessionId = req.getHeader("Access-Token");
        System.out.println("客户端传来的JSESSIONID：" + sessionId);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        String correctCode = session.getAttribute("code").toString();
        System.out.println("正确的验证码：" + correctCode);
        PrintWriter out = resp.getWriter();
        if (inputCode.equalsIgnoreCase(correctCode)) {
            Result result = userService.signIn(userDto);
            out.print(gson.toJson(result));
        } else {
            Result result = Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
            out.print(gson.toJson(result));
        }
        out.close();
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("验证账号");
    }

    private void follow(HttpServletResponse resp, long id, int iscare) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result rs = userService.follow(id, iscare);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(rs));
        out.close();
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        Map<String, Object> map = null;
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        String requestPath = req.getRequestURI().trim();
        PrintWriter out = resp.getWriter();
        Result result = userService.signUp(userDto);
        resp.setContentType("application/json;charset=utf-8");
        int code = resp.getStatus();
        String msg = code == 200 ? "成功":"失败";
        ResponseObject ro = ResponseObject.success(code,msg,userDto);
        PrintWriter out1 = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
}