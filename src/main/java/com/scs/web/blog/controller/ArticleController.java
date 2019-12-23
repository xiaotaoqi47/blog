package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.WriteArticleDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.HttpUtil;
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

/**
 * @author mq_xu
 * @ClassName ArticleController
 * @Description 文章控制器
 * @Date 2019/11/11
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/article", "/api/article/*", "/api/article/write" ,"/api/article/delete"})
public class ArticleController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @SneakyThrows
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
        }else if("/api/article/delete".equals(uri)){
            String userid = req.getParameter("user");
            String articleId =req.getParameter("article") ;
            deleteArticle(resp, Long.parseLong(articleId) , Long.parseLong(userid)) ;
        }
        else {
            getArticle(req, resp);
        }


    }

    private void deleteArticle(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException, SQLException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.deleteArticle(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();

    }



    private void addArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("发表文章：" + requestBody);
        Gson gson = new GsonBuilder().create();

        WriteArticleDto writeArticleDto = gson.fromJson(requestBody, WriteArticleDto.class);

        int userId = writeArticleDto.getUserId();
        int topicId = writeArticleDto.getTopicId();
        String title = writeArticleDto.getTitle();
        String summary = writeArticleDto.getSummary();
        String thumbnail = writeArticleDto.getThumbnail();
        String content = writeArticleDto.getContent();
        articleService.addArticle(userId, topicId, title, summary, thumbnail, content);

        Result result = Result.success("添加成功");
        HttpUtil.getResponseBody(resp, result);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String uri = req.getRequestURI().trim();
        if ("/api/article/write".equals(uri)) {
            addArticle(req, resp);
        }
    }
}
