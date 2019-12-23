package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 喜欢控制器
 */

@WebServlet(urlPatterns = {"/api/like", "/api/delete/like" ,"/api/add/like" })
public class LikeController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String uri = req.getRequestURI().trim();
        System.out.println(uri);
        String article_id = req.getParameter("article") ;
        String user_id = req.getParameter("user") ;
        System.out.println(user_id);
        if (user_id != null && article_id !=null){
            if ("/api/like".equals(uri)){
                selectLikeByArticleAndUser(resp ,Long.parseLong(article_id) , Long.parseLong(user_id) );
            }else if ("/api/delete/like".equals(uri)){
                deleteLikeByArticleAndUser(resp ,Long.parseLong(article_id) , Long.parseLong(user_id) );
            }else  if ("/api/add/like".equals(uri)){
                addLikeByArticleAndUser(resp ,Long.parseLong(article_id) , Long.parseLong(user_id) );

            }
        }

    }

    private void addLikeByArticleAndUser(HttpServletResponse resp, long parseInt, long parseInt1) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.addByArticleAndUser(parseInt , parseInt1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void deleteLikeByArticleAndUser(HttpServletResponse resp, long parseInt, long parseInt1) throws IOException {

        Result result = articleService.deleteByArticleAndUser(parseInt , parseInt1);
        PrintWriter out = resp.getWriter();
        out.print(result);
        out.close();
    }

    private void selectLikeByArticleAndUser(HttpServletResponse resp, long parseInt, long parseInt1) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.selectByArticleAndUser(parseInt , parseInt1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
