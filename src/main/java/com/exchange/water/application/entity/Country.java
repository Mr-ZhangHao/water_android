package com.exchange.water.application.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1.
 */

public class Country implements Serializable {

    /**
     * id : 1
     * name : 中国
     * coding : zh-cn
     * area : +86
     * img_url : https://waterio.oss-cn-hongkong.aliyuncs.com/wateriovn/flags/zh-cn.png
     * create_time : 2019-05-05 17:36:21.0
     * update_time : 2019-05-05 17:37:35.0
     */

    private int id;
    private String name;
    private String coding;
    private String area;
    private String img_url;
    private String create_time;
    private String update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
