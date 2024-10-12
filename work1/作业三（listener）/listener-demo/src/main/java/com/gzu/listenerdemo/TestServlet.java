package com.gzu.listenerdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 模拟请求处理时间
        try {
            Thread.sleep(1000); // 假设处理需要 1 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write("Test Servlet Response");
    }
}
