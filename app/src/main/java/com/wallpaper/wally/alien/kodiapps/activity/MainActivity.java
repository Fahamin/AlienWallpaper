package com.wallpaper.wally.alien.kodiapps.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.database.FavDatabase;
import com.wallpaper.wally.alien.kodiapps.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

 public static FavDatabase favDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(tabs.MODE_SCROLLABLE);

        favDatabase = Room.databaseBuilder(getApplicationContext(), FavDatabase.class, "alavdb").allowMainThreadQueries().build();

    }
}