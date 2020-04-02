package com.example.charityapp.model;

public class News {
    private String nid;
    private String titleNews;
    private String bodyNews;
    private String mImageUrl;
    private String time,
            date;
    public News(){

      }
    public News(String nid, String titleNews, String bodyNews, String mImageUrl, String time, String date) {
        this.nid = nid;
        this.titleNews = titleNews;
        this.bodyNews = bodyNews;
        this.mImageUrl = mImageUrl;
        this.time = time;
        this.date = date;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getBodyNews() {
        return bodyNews;
    }

    public void setBodyNews(String bodyNews) {
        this.bodyNews = bodyNews;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
