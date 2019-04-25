package org.maxpedersen.maquiz;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {
    private ArrayList<Article> mList;
    private OnItemClickListener mListener;
    private OnNoteListener mOnNoteListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public TextView published;
        public TextView date;
        OnNoteListener OnNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener listener){
            super(itemView);
            title = itemView.findViewById(R.id.newsTitleTV);
            description = itemView.findViewById(R.id.descriptionTV);
            published = itemView.findViewById(R.id.publisherTV);
            date = itemView.findViewById(R.id.dateTV);
            itemView.setOnClickListener(this);
            this.OnNoteListener = listener;
        }

        @Override
        public void onClick(View v) {
            OnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public NewsArticleAdapter(ArrayList<Article> newsList, OnNoteListener OnNoteListener){
        mList = newsList;
        this.mOnNoteListener = OnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_cardview, parent, false);
        ViewHolder evh = new ViewHolder(v, mOnNoteListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article currentItem = mList.get(i);
        viewHolder.title.setText(currentItem.getTitle());
        viewHolder.description.setText(currentItem.getDescription());
        viewHolder.published.setText(currentItem.getSource().getName());
        String dateManipulated = currentItem.getPublishedAt().substring(0, 10);
        viewHolder.date.setText(dateManipulated);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}

