package com.wallpaper.wally.alien.kodiapps.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.fragmetn.AlienFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.AngleFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.AnimalFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.Favorite;
import com.wallpaper.wally.alien.kodiapps.fragmetn.FlowerFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.GameFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.GostFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.NatureFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.NeklessFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.OcanFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.ScifiFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.SuperHeroFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.TecnoFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.WaterFrag;
import com.wallpaper.wally.alien.kodiapps.fragmetn.WomenFrag;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5,
            R.string.tab_text_6, R.string.tab_text_7, R.string.tab_text_8, R.string.tab_text_9, R.string.tab_text_10,
            R.string.tab_text_11, R.string.tab_text_12, R.string.tab_text_13, R.string.tab_text_14, R.string.tab_text_15

    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AlienFrag();
        }
        if (position == 1) {
            return new WaterFrag();
        }
        if (position == 2) {
            return new NatureFrag();
        }
        if (position == 3) {
            return new NeklessFrag();
        }
        if (position == 4) {
            return new GameFrag();
        }
        if (position == 5) {
            return new FlowerFrag();
        }
        if (position == 6) {
            return new SuperHeroFrag();
        }
        if (position == 7) {
            return new WomenFrag();
        }
        if (position == 8) {
            return new AnimalFrag();
        }
        if (position == 9) {
            return new AngleFrag();
        }
        if (position == 10) {
            return new TecnoFrag();
        }
        if (position == 11) {
            return new ScifiFrag();
        }
        if (position == 12) {
            return new OcanFrag();
        }
        if (position == 13) {
            return new GostFrag();
        }
        if (position == 14) {
            return new Favorite();
        }

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 15;
    }
}