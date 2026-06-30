package com.xlauncher.minecraft.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.data.models.Mod;

import java.util.List;

public class ModsAdapter extends RecyclerView.Adapter<ModsAdapter.ModViewHolder> {

    private List<Mod> mods;
    private OnModClickListener listener;

    public ModsAdapter(List<Mod> mods, OnModClickListener listener) {
        this.mods = mods;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ModViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mod, parent, false);
        return new ModViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModViewHolder holder, int position) {
        Mod mod = mods.get(position);
        holder.bind(mod, listener);
    }

    @Override
    public int getItemCount() {
        return mods.size();
    }

    public void updateMods(List<Mod> newMods) {
        mods.clear();
        mods.addAll(newMods);
        notifyDataSetChanged();
    }

    public static class ModViewHolder extends RecyclerView.ViewHolder {
        private TextView modName;
        private TextView modAuthor;
        private TextView modVersion;
        private ImageView modIcon;
        private TextView modDownloads;
        private TextView modRating;

        public ModViewHolder(@NonNull View itemView) {
            super(itemView);
            modName = itemView.findViewById(R.id.mod_name);
            modAuthor = itemView.findViewById(R.id.mod_author);
            modVersion = itemView.findViewById(R.id.mod_version);
            modIcon = itemView.findViewById(R.id.mod_icon);
            modDownloads = itemView.findViewById(R.id.mod_downloads);
            modRating = itemView.findViewById(R.id.mod_rating);
        }

        public void bind(Mod mod, OnModClickListener listener) {
            modName.setText(mod.getName());
            modAuthor.setText(mod.getAuthor());
            modVersion.setText(mod.getVersion());
            modDownloads.setText(String.valueOf(mod.getDownloadCount()));
            modRating.setText(String.format("%.1f", mod.getRating()));
            
            itemView.setOnClickListener(v -> listener.onModClick(mod));
        }
    }

    public interface OnModClickListener {
        void onModClick(Mod mod);
    }
}
