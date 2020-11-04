package com.wallpaper.wally.alien.kodiapps.fragmetn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.activity.MainActivity;
import com.wallpaper.wally.alien.kodiapps.adapter.FavAdapeter;
import com.wallpaper.wally.alien.kodiapps.classfile.Fun;
import com.wallpaper.wally.alien.kodiapps.model.FavModel;
import com.wallpaper.wally.alien.kodiapps.viewmodel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends Fragment {

    private FavoriteViewModel mViewModel;
    RecyclerView recyclerView;
    public static List<FavModel> list;
    FavAdapeter adapter;
    TextView emptyTxt;

    public static Favorite newInstance() {
        return new Favorite();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Fun(getContext());

        emptyTxt = view.findViewById(R.id.notFoundTxt);
        recyclerView = view.findViewById(R.id.favRecviewID);

        list = new ArrayList<>();
        // new Fun(getContext());
       /* final LinearLayout adContainer = view.findViewById(R.id.banner_container);
        if (!checkInternet()) {
            adContainer.setVisibility(View.INVISIBLE);
        }
        AdView adView = new AdView(getContext(), getString(R.string.banner), AdSize.BANNER_HEIGHT_50);
        adContainer.addView(adView);
        adView.loadAd();*/


        list = MainActivity.favDatabase.favoriteDao().getFavoriteData();


        if (list.size() > 0) {
            prepareForView();
        } else {
            recyclerView.setVisibility(View.GONE);
            emptyTxt.setVisibility(View.VISIBLE);
        }


    }

    private void prepareForView() {

        RecyclerView.LayoutManager layoutManagerBeforeMeal = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManagerBeforeMeal);
        //  recyclerView.addItemDecoration(new ItemDecorate(1, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new FavAdapeter(getContext(), recyclerView, list);
        adapter.notifyDataSetChanged();
       /* FBNativeBanAdapter fbAdapter = FBNativeBanAdapter.Builder.with(getResources().getString(R.string.nativBanner), adapter)
                .adItemInterval(3)
                .build();*/
        recyclerView.setAdapter(adapter);

    }

}
