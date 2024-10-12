package com.gzu.listenerdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class RequestLoggerListener implements ServletRequestListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // 创建 JSON 对象
        ObjectNode logEntry = objectMapper.createObjectNode();
        logEntry.put("timestamp", dateFormat.format(new Date(startTime)));
        logEntry.put("type", "request_started");
        logEntry.put("client_ip", request.getRemoteAddr());
        logEntry.put("method", request.getMethod());
        logEntry.put("uri", request.getRequestURI());
        logEntry.put("query_string", request.getQueryString());
        logEntry.put("user_agent", request.getHeader("User-Agent"));

        // 记录日志
        try {
            System.out.println(objectMapper.writeValueAsString(logEntry));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        // 获取请求开始时间
        long startTime = (long) request.getAttribute("startTime");
        // 计算请求处理时间
        long processingTime = System.currentTimeMillis() - startTime;

        // 创建 JSON 对象
        ObjectNode logEntry = objectMapper.createObjectNode();
        logEntry.put("timestamp", dateFormat.format(new Date()));
        logEntry.put("type", "request_finished");
        logEntry.put("processing_time_ms", processingTime);

        // 记录日志
        try {
            System.out.println(objectMapper.writeValueAsString(logEntry));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}



