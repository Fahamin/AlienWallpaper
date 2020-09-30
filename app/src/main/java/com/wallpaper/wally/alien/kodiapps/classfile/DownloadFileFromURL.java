package com.wallpaper.wally.alien.kodiapps.classfile;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    ProgressDialog mProgressDialog;
    private static Context context;
    public static String link;

    public DownloadFileFromURL(Context context, String link) {
        this.context = context;
        this.link = link;
        haveStoragePermission();


    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

            showProgress();
    }

    public void showProgress() {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Downloading... Please Wait!");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    public  static boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;

        }
    }

     public static void copyItem(String s) {


         ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
         ClipData clip = ClipData.newPlainText("simple text", s);

         if(clipboard != null)
         {
             clipboard.setPrimaryClip(clip);
             Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show();
         }


        /* int sdk = android.os.Build.VERSION.SDK_INT;
         if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
             android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                     getSystemService(Context.CLIPBOARD_SERVICE);
             clipboard.setText(copyText);
         } else {
             android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                     getSystemService(Context.CLIPBOARD_SERVICE);
             android.content.ClipData clip = android.content.ClipData
                     .newPlainText("Your OTP", copyText);
             clipboard.setPrimaryClip(clip);
         }*/

     }

     /**
      * Downloading file in background thread
      */
    @Override
    protected String doInBackground(String... f_url) {

        String fileName = link.substring(link.lastIndexOf('/') + 1);

            int count;
            FileOutputStream outputStream;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                //save file directory
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), fileName);

                outputStream = new FileOutputStream(file);

                byte data[] = new byte[1024];
                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();
                outputStream.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }


        return null;

    }

    /**
     * Updating progress bar
     */
    protected void onProgressUpdate(String... progress) {
        mProgressDialog.setProgress(Integer.parseInt(progress[0]));
    }



    public static boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //  Log.e("Permission error", "You have permission");
                return true;
            } else {

                //  Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Toast.makeText(context, "Need to Permission for Download", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else { //you dont need to worry about these stuff below api level 23
            //  Log.e("Permission error", "You already have the permission");
            return true;
        }
    }

    @Override
    protected void onPostExecute(String file_url) {
        mProgressDialog.dismiss();
        Toast.makeText(context, "Download Complete", Toast.LENGTH_SHORT).show();
        Log.d("DDD","COMPLETE");
    }

}

