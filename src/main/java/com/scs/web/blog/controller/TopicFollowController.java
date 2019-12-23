package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.TopicFollowService;
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

@WebServlet(urlPatterns = {"/api/topicfollow", "/api/delete/topicfollow" ,"/api/add/topicfollow" })
public class TopicFollowController extends HttpServlet {
    private TopicFollowService topicFollowService= ServiceFactory.getTopicFollowServiceInstance();
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
            if ("/api/topicfollow".equals(uri)){
                selecttopicfollowByArticleAndUser(resp ,Long.parseLong(user_id) , Long.parseLong(article_id) );
            }else if ("/api/delete/topicfollow".equals(uri)){
                deletetopicfollowByArticleAndUser(resp ,Long.parseLong(user_id) , Long.parseLong(article_id)  );
            }else  if ("/api/add/topicfollow".equals(uri)){
                addtopicfollowByArticleAndUser(resp ,Long.parseLong(user_id) , Long.parseLong(article_id)  );
            }
        }

    }

    private void addtopicfollowByArticleAndUser(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicFollowService.addTopicFollow(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void deletetopicfollowByArticleAndUser(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicFollowService.deleteTopicFollow(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void selecttopicfollowByArticleAndUser(HttpServletResponse resp, long parseLong, long parseLong1) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicFollowService.selectTopicFollow(parseLong , parseLong1);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
