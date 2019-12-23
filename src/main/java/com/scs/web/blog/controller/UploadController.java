package com.scs.web.blog.controller;

import com.scs.web.blog.util.HttpUtil;
import com.scs.web.blog.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description 上传图片控制器
 * @Author mq_xu
 * @Date 2019/11/20
 **/
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = "/api/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("filename");
        String name = part.getSubmittedFileName();
        System.out.println(name);
        String path = req.getSession().getServletContext().getRealPath("");
        System.out.println(path);
        part.write(path + name);
        HttpUtil.getResponseBody(resp, new Result(0, "http://localhost:9091/" + name));
        System.out.println(path + name);
    }
}
