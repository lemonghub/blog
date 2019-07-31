package com.xmgl.blog.util;


public class Result <T> {

    private static final Integer OK = 0;

    private int code;

    private String msg;

    private int count;

    private T data;

    private Result(int code, String message, int count, T data){
        this.count = count;
        this.data = data;
        this.code = code;
        this.msg = message;
    }

    public static <T> Result<T> createSuccessResult(int total, T data){
        return new Result<T>(OK, "", total, data);
    }

    public static Integer getOK() {
        return OK;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
