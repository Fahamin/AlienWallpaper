package com.wallpaper.wally.alien.kodiapps.model;

import java.util.List;

public class DataHomeModel {
    List<ImageModel> list;
    String image_Catagory;

    public DataHomeModel(List<ImageModel> list, String image_Catagory) {
        this.list = list;
        this.image_Catagory = image_Catagory;
    }

    public List<ImageModel> getList() {
        return list;
    }

    public void setList(List<ImageModel> list) {
        this.list = list;
    }

    public String getImage_Catagory() {
        return image_Catagory;
    }

    public void setImage_Catagory(String image_Catagory) {
        this.image_Catagory = image_Catagory;
    }
}
