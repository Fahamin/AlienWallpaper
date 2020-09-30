package com.wallpaper.wally.alien.kodiapps.fragmetn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.wallpaper.wally.alien.kodiapps.R;
import com.wallpaper.wally.alien.kodiapps.adapter.ImageAdapter;
import com.wallpaper.wally.alien.kodiapps.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

import static com.wallpaper.wally.alien.kodiapps.classfile.Utils.gostF;

public class GostFrag extends Fragment {

    private com.wallpaper.wally.alien.kodiapps.fragmetn.GostViewModel mViewModel;
    public static RecyclerView recyclerView;
    public static List<ImageModel> list;
    ImageAdapter adapter;
    public static GostFrag newInstance() {
        return new GostFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gost_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.birdsRecId);

        loadData();
    }

    private void loadData() {
        gostF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                Iterable<DataSnapshot> allSingleItem = dataSnapshot.getChildren();

                for (DataSnapshot singleItem : allSingleItem) {
                    ImageModel dataModel = singleItem.getValue(ImageModel.class);
                    list.add(dataModel);
                }

                adapter = new ImageAdapter(getContext(), recyclerView, list);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Loading Failed ! Check Network Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(com.wallpaper.wally.alien.kodiapps.fragmetn.GostViewModel.class);
        // TODO: Use the ViewModel
    }

}
