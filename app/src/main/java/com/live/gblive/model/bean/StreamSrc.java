package com.live.gblive.model.bean;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 16:05
 * description:
 */
public class StreamSrc {
    private String name;
    private String src;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "StreamSrc{" +
                "name='" + name + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
