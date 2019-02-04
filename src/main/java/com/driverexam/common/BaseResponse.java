package com.driverexam.common;

import java.util.Date;

public class BaseResponse {

    private int code = 200;
    private String msg = "请求成功";
    private long time = new Date().getTime();
    private Object data;

    public BaseResponse() {

    }

    public BaseResponse(String msg) {
        this(msg, 200);
    }

    public BaseResponse(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
