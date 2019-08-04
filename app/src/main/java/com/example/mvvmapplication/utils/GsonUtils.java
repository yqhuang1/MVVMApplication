package com.example.mvvmapplication.utils;

import com.example.mvvmapplication.entity.User;
import com.google.gson.Gson;

public class GsonUtils {

    public void gsonFrom() {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);              //100
        double d = gson.fromJson("\"99.99\"", double.class);  //99.99
        boolean b = gson.fromJson("true", boolean.class);     // true
        String str = gson.fromJson("String", String.class);   // String
    }

    public void gsonTo() {
        Gson gson = new Gson();
        String jsonNumber = gson.toJson(100);       // 100
        String jsonBoolean = gson.toJson(false);    // false
        String jsonString = gson.toJson("String"); //"String"
    }

    public void gsonToUser() {
        Gson gson = new Gson();
        User user = new User("怪盗kidou", 24, "163@qq.com");
        String jsonObject = gson.toJson(user); // {"name":"怪盗kidou","age":24,"email":"163@qq.com"}
    }

    public void gsonFromUser() {
        Gson gson = new Gson();
        String jsonString = "{\"name\":\"怪盗kidou\",\"age\":24,\"email\":\"163@qq.com\"}";
        User user = gson.fromJson(jsonString, User.class);
    }

}
