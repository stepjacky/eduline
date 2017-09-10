package org.jackysoft.edu.message;

import java.util.HashMap;
import java.util.Map;

public class Message {

    public static final int OK_CODE=0;
    public static final int BAD_CODE=1;


    static final Map<Integer,String> messages = new HashMap<>();
    static {
        messages.put(OK_CODE,"登录成功");
        messages.put(BAD_CODE,"登录失败,用户名或者密码错误");
    }


    int status;
    String token;
    Object data;
    String message;

    public Message(int status, String token, String message) {
        this.status = status;
        this.token = token;
        this.message = message;
    }

    public Message(int status, String token,  String message,Object data) {
        this(status,token,message);
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
