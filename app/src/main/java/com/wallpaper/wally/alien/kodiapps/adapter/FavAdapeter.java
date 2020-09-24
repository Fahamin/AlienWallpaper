package com.wallpaper.wally.alien.kodiapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wallpaper.wally.alien.kodiapps.activity.DetailsActivity;
import com.wallpaper.wally.alien.kodiapps.activity.MainActivity;
import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.model.FavModel;

import java.util.List;


public class FavAdapeter extends RecyclerView.Adapter<FavAdapeter.MovieHolder>  {

    Context context;
    RecyclerView recyclerView;
    List<FavModel> movieList;

    public FavAdapeter(Context context, RecyclerView recyclerView, List<FavModel> movieList) {
        this.context = context;
        this.recyclerView = recyclerView;
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
        final FavModel movieModel = movieList.get(position);

        Picasso.get().load(movieList.get(position).getLink()).into(holder.imageThumble);

        if (MainActivity.favDatabase.favoriteDao().isFavorite(movieModel.getId()) == 1) {
            holder.fav_btn.setImageResource(R.drawable.fav_red);
        } else {
            holder.fav_btn.setImageResource(R.drawable.fav_white);

        }


        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                holder.fav_btn.setImageResource(R.drawable.fav_white);
                MainActivity.favDatabase.favoriteDao().delete(movieModel);
                Toast.makeText(context, "remove", Toast.LENGTH_SHORT).show();


            }
        });
        holder.movie_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailsActivity.class).putExtra("link", movieModel.getLink()));

            }
        });
    }



    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView imageThumble;
        TextView titleTxt;
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
