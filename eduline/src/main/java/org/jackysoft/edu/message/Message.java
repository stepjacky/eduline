package org.jackysoft.edu.message;

import java.util.HashMap;
import java.util.Map;

public class Message {

    static final Map<Integer,String> messages = new HashMap<>();
    static {
        messages.put(0,"登录成功");
        messages.put(1,"登录失败,用户名或者密码错误");
    }


    int status;
    String token;
    Object data;
    String message;

    public Message(int status, String token) {
        this.status = status;
        this.message =
        this.token = token;
    }

    public Message(int status, String message, String token, Object data) {
        this(status,token);
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
