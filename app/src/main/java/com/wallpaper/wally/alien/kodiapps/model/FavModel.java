package com.wallpaper.wally.alien.kodiapps.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favModel")
public class FavModel {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "link")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}