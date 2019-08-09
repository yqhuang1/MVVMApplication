package com.example.mvvmapplication.entity;

/**
 * 类说明
 * * @version [版本]
 *
 * @see [相关类]
 * @since [模块]
 * <p>
 * json数据中data的数据，即JSONArray的各项数据
 */
public class BaseVideoEntity {

    public String match_id;
    public String match_type;
    public String name;
    public String pic_tj;
    public String endtime;
    public String upload_time;
    public String display;

    public String url;
    public String pic_600_x;

    public int width;
    public int height;
    public int mark;

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

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
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
                ", display='" + display + '\'' +
                ", url='" + url + '\'' +
                ", pic_600_x='" + pic_600_x + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", mark=" + mark +
                '}';
    }
}
