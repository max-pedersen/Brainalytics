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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_cardview, parent, false);
        ViewHolder evh = new ViewHolder(v);
        return(evh);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article currentItem = mList.get(i);
        viewHolder.title.setText(currentItem.getTitle());
        viewHolder.description.setText(currentItem.getDescription());
        viewHolder.published.setText(currentItem.getSource().getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView published;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleTV);
            description = itemView.findViewById(R.id.descriptionTV);
            published = itemView.findViewById(R.id.publisherTV);
        }
    }

    public NewsArticleAdapter(ArrayList<Article> list){
        mList = list;
    }


}

