package com.xlauncher.minecraft.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.xlauncher.minecraft.R;
import com.xlauncher.minecraft.data.models.GitHubMod;

import java.util.ArrayList;
import java.util.List;

public class GitHubModsAdapter extends RecyclerView.Adapter<GitHubModsAdapter.GitHubModViewHolder> {

    private List<GitHubMod> mods;
    private OnModActionListener listener;
    private Context context;

    public GitHubModsAdapter(Context context, OnModActionListener listener) {
        this.context = context;
        this.mods = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public GitHubModViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_github_mod, parent, false);
        return new GitHubModViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubModViewHolder holder, int position) {
        GitHubMod mod = mods.get(position);
        holder.bind(mod, listener);
    }

    @Override
    public int getItemCount() {
        return mods.size();
    }

    public void setMods(List<GitHubMod> newMods) {
        mods.clear();
        mods.addAll(newMods);
        notifyDataSetChanged();
    }

    public static class GitHubModViewHolder extends RecyclerView.ViewHolder {
        private TextView modName;
        private TextView modAuthor;
        private TextView modDescription;
        private TextView stars;
        private TextView downloads;
        private MaterialButton publishBtn;

        public GitHubModViewHolder(@NonNull View itemView) {
            super(itemView);
            modName = itemView.findViewById(R.id.github_mod_name);
            modAuthor = itemView.findViewById(R.id.github_mod_author);
            modDescription = itemView.findViewById(R.id.github_mod_description);
            stars = itemView.findViewById(R.id.github_mod_stars);
            downloads = itemView.findViewById(R.id.github_mod_downloads);
            publishBtn = itemView.findViewById(R.id.publish_to_store_btn);
        }

        public void bind(GitHubMod mod, OnModActionListener listener) {
            modName.setText(mod.getName());
            modAuthor.setText("by " + mod.getAuthor());
            modDescription.setText(mod.getDescription() != null ? mod.getDescription() : "No description");
            stars.setText("⭐ " + mod.getStars());
            downloads.setText("📥 " + mod.getDownloads());

            publishBtn.setOnClickListener(v -> listener.onPublishClick(mod));
        }
    }

    public interface OnModActionListener {
        void onPublishClick(GitHubMod mod);
    }
}
