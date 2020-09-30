package com.wallpaper.wally.alien.kodiapps.classfile;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.IOException;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class BootReceiver extends BroadcastReceiver {

    Bitmap bitmap;
    public BootReceiver(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int height = metrics.heightPixels;
            int width = metrics.widthPixels;
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap,width,height, true);
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            wallpaperManager.setWallpaperOffsetSteps(1, 1);
            wallpaperManager.suggestDesiredDimensions(width, height);
            try {
                wallpaperManager.setBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            Log.e(TAG,e.toString());
        }
    }


   // imageView.setImageBitmap(getBitmapFromURL(url));
}
