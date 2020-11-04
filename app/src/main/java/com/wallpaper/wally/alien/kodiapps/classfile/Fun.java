package com.wallpaper.wally.alien.kodiapps.classfile;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.wallpaper.wally.alien.kodiapps.R;


public class Fun {

    public static Context context;

    private static com.facebook.ads.InterstitialAd interstitial;
    private static int count = 0;

    public Fun(Context context) {
        this.context = context;

    }

    public static boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;

        }
    }

    public static void addShow() {
        count ++;
        if (count % 2 == 0) {
            interstitial = new com.facebook.ads.InterstitialAd(context, context.getString(R.string.fb_insta_id));
            interstitial.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    SHOWINSTADD();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            interstitial.loadAd();
        }
    }

    private static void SHOWINSTADD() {
        if (interstitial.isAdLoaded()) {
            interstitial.show();
        }
    }


}