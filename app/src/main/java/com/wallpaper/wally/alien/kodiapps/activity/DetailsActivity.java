package com.wallpaper.wally.alien.kodiapps.activity;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.classfile.BitmapTransform;
import com.wallpaper.wally.alien.kodiapps.classfile.DownloadFileFromURL;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;
import static com.wallpaper.wally.alien.kodiapps.classfile.Utils.progressDialog;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    String link;
    Boolean wall = false;

    private static final int MAX_WIDTH = 1280;
    private static final int MAX_HEIGHT = 720;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);
      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        imageView = findViewById(R.id.fullImageID);

        link = getIntent().getStringExtra("link");

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabDownload = findViewById(R.id.fab_DownloadID);

        int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));


        Picasso.get().load(link).transform(new BitmapTransform(MAX_WIDTH,MAX_HEIGHT)).fit().centerInside()
                .placeholder(R.drawable.progress_animation).
                into(imageView);


       // new ImageLoadTask(link, imageView).execute();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wall = true;
                progressDialog = new ProgressDialog(DetailsActivity.this);
                progressDialog.setMessage("Processing Wallpaper...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new ImageLoadTask(link, imageView).execute();

            }
        });

        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFileFromURL(DetailsActivity.this, link).execute(link);
            }
        });

    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {

            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
            if (wall) {
                setWally(bitmap);

            }
        }
    }

    public void setWally(Bitmap bitmap) {

        try {

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int height = metrics.heightPixels;
            int width = metrics.widthPixels;
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, width, height, true);
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailsActivity.this);
            wallpaperManager.setWallpaperOffsetSteps(1, 1);
            wallpaperManager.suggestDesiredDimensions(width, height);
            try {
                wallpaperManager.setBitmap(bitmap2);
                Toast.makeText(DetailsActivity.this, "Setup Complete", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}


