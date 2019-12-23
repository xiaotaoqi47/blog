package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Result;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/api/userFan", "/api/userFaned","/api/userFans"})
public class UserFanController  extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        String f_user = req.getParameter("f_user") ;
        String t_user = req.getParameter("t_user") ;
        if ("/api/userFan".equals(uri)){
            addUserFans(resp , Long.parseLong(f_user) , Long.parseLong(t_user)) ;
        }else{
                deleteUserFans(resp , Long.parseLong(f_user) , Long.parseLong(t_user));
        }
    }



    private void deleteUserFans(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException, SQLException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.deleteUserFans(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void addUserFans(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException, SQLException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.addUserFans(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
