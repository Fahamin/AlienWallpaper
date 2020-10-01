package com.wallpaper.wally.alien.kodiapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.activity.DetailsActivity;
import com.wallpaper.wally.alien.kodiapps.activity.MainActivity;
import com.wallpaper.wally.alien.kodiapps.classfile.BitmapTransform;
import com.wallpaper.wally.alien.kodiapps.model.FavModel;
import com.wallpaper.wally.alien.kodiapps.model.ImageModel;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MovieHolder> {
    private static final int MAX_WIDTH = 720;
    private static final int MAX_HEIGHT = 1280;


    Context context;
    RecyclerView recyclerView;
    List<ImageModel> movieList, searchList;

    public ImageAdapter(Context context, RecyclerView recyclerView, List<ImageModel> movieList) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.movieList = movieList;
        this.searchList = movieList;
    }

    public ImageAdapter(List<ImageModel> movieList, Context context) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, null);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, final int position) {

        final ImageModel imageModel = movieList.get(position);

        int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));



        Picasso.get().load(movieList.get(position).getL()).transform(new BitmapTransform(MAX_WIDTH,MAX_HEIGHT)).fit().centerInside()
                .placeholder(R.drawable.progress_animation).
                into(holder.imageThumble);


        if (MainActivity.favDatabase.favoriteDao().isFavorite(imageModel.getI()) == 1) {
            holder.fav_btn.setImageResource(R.drawable.fav_red);
        } else {
            holder.fav_btn.setImageResource(R.drawable.fav_white);

        }


        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavModel favModel = new FavModel();

                int id = imageModel.getI();

                String link = imageModel.getL();


                favModel.setId(id);
                favModel.setLink(link);


                if (MainActivity.favDatabase.favoriteDao().isFavorite(id) != 1) {
                    holder.fav_btn.setImageResource(R.drawable.fav_red);
                    MainActivity.favDatabase.favoriteDao().addData(favModel);
                    Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                } else {
                    holder.fav_btn.setImageResource(R.drawable.fav_white);
                    MainActivity.favDatabase.favoriteDao().delete(favModel);
                    Toast.makeText(context, "remove", Toast.LENGTH_SHORT).show();
                }


            }
        });
        holder.movie_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, movieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, DetailsActivity.class).putExtra("link", imageModel.getL()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView imageThumble;
        RelativeLayout movie_Layout;
        ImageView fav_btn;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageThumble = itemView.findViewById(R.id.imageViewID);
            movie_Layout = itemView.findViewById(R.id.fulllaMOVIyoutID);
            fav_btn = itemView.findViewById(R.id.favId);

        }

    }
}
