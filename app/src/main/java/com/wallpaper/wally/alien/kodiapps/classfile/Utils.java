package com.wallpaper.wally.alien.kodiapps.classfile;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Utils {

    public static DatabaseReference neklessF = FirebaseDatabase.getInstance().getReference("nekless");
    public static DatabaseReference alienF = FirebaseDatabase.getInstance().getReference("alien");
    public static DatabaseReference waterF = FirebaseDatabase.getInstance().getReference("water");
    public static DatabaseReference womenF = FirebaseDatabase.getInstance().getReference("women");
    public static DatabaseReference angleF = FirebaseDatabase.getInstance().getReference("angle");
    public static DatabaseReference animalF = FirebaseDatabase.getInstance().getReference("animal");
    public static DatabaseReference flowerF = FirebaseDatabase.getInstance().getReference("flower");
    public static DatabaseReference gameF = FirebaseDatabase.getInstance().getReference("game");
    public static DatabaseReference gostF = FirebaseDatabase.getInstance().getReference("gost");
    public static DatabaseReference natureF = FirebaseDatabase.getInstance().getReference("nature");
    public static DatabaseReference ocanF = FirebaseDatabase.getInstance().getReference("ocan");
    public static DatabaseReference scifiF = FirebaseDatabase.getInstance().getReference("scifi");
    public static DatabaseReference superheroF = FirebaseDatabase.getInstance().getReference("superhero");
    public static DatabaseReference techoF = FirebaseDatabase.getInstance().getReference("techo");



    public static Context context;
    public static  ProgressDialog progressDialog;
}
