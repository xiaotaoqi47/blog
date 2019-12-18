package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;

import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.UrlPatten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @ClassName ArticleController
 * @Description 文章控制器
 * @Date 2019/11/11
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/article", "/api/article/*"})
public class ArticleController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String uri = req.getRequestURI().trim();
        if ("/api/article".equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getArticlesByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getArticlesByKeywords(resp, keywords);
            } else {
                getHotArticles(req, resp);
            }
        } else {
            getArticle(req, resp);
        }
    }

    private void getHotArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getHotArticles();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getArticlesByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Result result = articleService.getArticle(Long.parseLong(id));
        Gson gson = new GsonBuilder().create();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getRequestURI().trim();
        String id = info.substring(info.lastIndexOf("/") + 1);
        System.out.println(id);
        Result result = articleService.batchDelete(Long.parseLong(id));
        Gson gson = new GsonBuilder().create();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case UrlPatten.ARTICLE_WRITE:
                Insert(req, resp);
                break;
            default:
        }
    }
    private void Insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Article article =gson.fromJson(stringBuilder.toString(),Article.class);

        System.out.println(article);

        //插入数据库，并返回该行主键
        Result rs = articleService.Write(article);
        //补全user的id字段信息
        //通过response对象返回Json信息
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(rs));
        out.close();


    }

}
