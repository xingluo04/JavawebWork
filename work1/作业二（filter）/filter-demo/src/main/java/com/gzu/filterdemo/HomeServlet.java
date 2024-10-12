package com.gzu.filterdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查用户是否已经登录
        if (request.getSession().getAttribute("user") == null) {
            // 如果用户未登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // 如果用户已登录，转发到 home.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }
    }
}

