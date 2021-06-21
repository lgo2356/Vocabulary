package com.hun.vocabulary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hun.vocabulary.R;
import com.hun.vocabulary.db.entity.VocabularyEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VocabularyListAdapter extends RecyclerView.Adapter<VocabularyListAdapter.ViewHolder> {

    private List<VocabularyEntity> vocabularyList = new ArrayList<>();

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_vocabulary_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        VocabularyEntity vocabulary = vocabularyList.get(pos);

        holder.tvTitle.setText(vocabulary.title);
        holder.tvMemo.setText(vocabulary.memo);
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }

    public void setVocabularyList(List<VocabularyEntity> vocabularyList) {
        this.vocabularyList = vocabularyList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvMemo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvMemo = itemView.findViewById(R.id.tv_memo);
        }
    }
}
