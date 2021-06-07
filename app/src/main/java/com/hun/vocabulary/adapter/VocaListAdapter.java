package com.hun.vocabulary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hun.vocabulary.R;
import com.hun.vocabulary.db.entity.VocaListEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VocaListAdapter extends RecyclerView.Adapter<VocaListAdapter.ViewHolder> {

    private List<VocaListEntity> vocaList = new ArrayList<>();

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_vocabulary_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        VocaListEntity voca = vocaList.get(pos);

        holder.tvTitle.setText(voca.title);
        holder.tvMemo.setText(voca.memo);
    }

    @Override
    public int getItemCount() {
        return vocaList.size();
    }

    public void setVocaList(List<VocaListEntity> vocaList) {
        this.vocaList = vocaList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvMemo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvMemo = itemView.findViewById(R.id.tv_memo);
        }
    }
}
