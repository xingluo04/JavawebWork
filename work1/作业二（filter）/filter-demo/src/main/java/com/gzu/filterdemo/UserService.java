package com.gzu.filterdemo;

public class UserService {
    public User authenticate(String username, String password) {
        // 这里应该有实际的验证逻辑，比如查询数据库
        if ("admin".equals(username) && "password".equals(password)) {
            return new User(username, password);
        }
        return null;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
