package com.wallpaper.wally.alien.kodiapps.model;

public class ImageModel {

    int id;
    String l;

    public ImageModel() {
    }

    public ImageModel(int id, String link) {
        this.id = id;
        this.l = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
