package org.example.entities;

public class ScreenShot {
    public int id;
    public String url;
    public int type;

    public ScreenShot(int id, String url, int type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public ScreenShot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ScreenShot{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
