package com.example.mvvmapplication.entity;

public class BaseVideoEntity {

    public String match_id;
    public String match_type;
    public String name;
    public String pic_tj;
    public String endtime;
    public String upload_time;

    public String url;
    public String pic_600_x;

    public String mark;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMatch_type() {
        return match_type;
    }

    public void setMatch_type(String match_type) {
        this.match_type = match_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_tj() {
        return pic_tj;
    }

    public void setPic_tj(String pic_tj) {
        this.pic_tj = pic_tj;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic_600_x() {
        return pic_600_x;
    }

    public void setPic_600_x(String pic_600_x) {
        this.pic_600_x = pic_600_x;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "BaseVideoEntity{" +
                "match_id='" + match_id + '\'' +
                ", match_type='" + match_type + '\'' +
                ", name='" + name + '\'' +
                ", pic_tj='" + pic_tj + '\'' +
                ", endtime='" + endtime + '\'' +
                ", upload_time='" + upload_time + '\'' +
                ", url='" + url + '\'' +
                ", pic_600_x='" + pic_600_x + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
