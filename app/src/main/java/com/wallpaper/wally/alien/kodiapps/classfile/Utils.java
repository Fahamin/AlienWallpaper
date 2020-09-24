package com.wallpaper.wally.alien.kodiapps.classfile;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Utils {

    public static DatabaseReference birdsF = FirebaseDatabase.getInstance().getReference("Birds");
    public static DatabaseReference alienF = FirebaseDatabase.getInstance().getReference("alien");
    public static DatabaseReference flowersF = FirebaseDatabase.getInstance().getReference("Flower");
    public static DatabaseReference lekesF = FirebaseDatabase.getInstance().getReference("Lekes");
    public static DatabaseReference riverF = FirebaseDatabase.getInstance().getReference("River");
    public static DatabaseReference forestf = FirebaseDatabase.getInstance().getReference("forest");

    public static DatabaseReference romanceF = FirebaseDatabase.getInstance().getReference("Romance");
    public static DatabaseReference sportF = FirebaseDatabase.getInstance().getReference("Sport");
    public static DatabaseReference thillerF = FirebaseDatabase.getInstance().getReference("Thiller");
    public static DatabaseReference warF = FirebaseDatabase.getInstance().getReference("warMovie");



    public static Context context;
    public static  ProgressDialog progressDialog;
}
