package com.xlauncher.minecraft.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.ui.adapters.ModsAdapter;

public class SmartStoreFragment extends Fragment {

    private RecyclerView modsRecycler;
    private ModsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_smart_store, container, false);

        modsRecycler = root.findViewById(R.id.mods_recycler);
        modsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ModsAdapter(new java.util.ArrayList<>(), mod -> {
            // Handle mod click
        });
        modsRecycler.setAdapter(adapter);

        return root;
    }
}
