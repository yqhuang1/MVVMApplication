package com.example.mvvmapplication.http;

/**
 * Created by seph_von on 2018/3/25.
 */

public class OverTimeException extends RuntimeException {
    private String code;

    public OverTimeException(String code){
        this.code = code;
    }

    @Override
    public String getMessage() {
        return "over time exception";
    }
}
