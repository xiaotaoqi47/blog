package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.ArticleDto;
import com.scs.web.blog.entity.ArticleAdd;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleAddService;
import com.scs.web.blog.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiaotaoqi
 * @ClassName ArticleAddController
 * @Description TODO
 * @Date 2019/12/5:11:15
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/art","/api/art/*"})
public class ArticleAddController extends HttpServlet {
    private ArticleAddService articleAddService = ServiceFactory.getArticleAddServiceInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/art/con".equals(uri)) {
            Connect(req, resp);
        }else if ("/api/art/change".equals(uri)){
            update(req, resp);
        }
    }

    private void Connect(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
        //请求字符集设置
        req.setCharacterEncoding("UTF-8");
        //接送客户端船体的Json数据，通过缓冲字符流按行读取，存入可变长字符串中
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        //将接受到的客户端JSON字符串转成User对象
        Gson gson = new GsonBuilder().create();
        ArticleAdd user =gson.fromJson(stringBuilder.toString(),ArticleAdd.class);
        System.out.println(user);
        //插入数据库，并返回该行主键
        Result rs = articleAddService.addArticle(user);
        //补全user的id字段信息
        //通过response对象返回Json信息
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(rs));
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
        ArticleDto articleDto = gson.fromJson(stringBuilder.toString(), ArticleDto.class);
        Result result = articleAddService.upDate(articleDto);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

}
