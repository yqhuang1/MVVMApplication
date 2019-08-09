package com.example.mvvmapplication.entity;

/**
 * 类说明
 *
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 * <p>
 * data:才是真正需要显示的列表json数据，即JSONArray
 */
public class HttpVideoResponse<T> {

    public boolean result;
    public String msg;
    public T data;//List

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpVideoResponse{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
