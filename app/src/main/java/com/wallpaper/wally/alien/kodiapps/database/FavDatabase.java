package com.wallpaper.wally.alien.kodiapps.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wallpaper.wally.alien.kodiapps.model.FavModel;


@Database(entities={FavModel.class},version = 1)
public abstract class FavDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

}
