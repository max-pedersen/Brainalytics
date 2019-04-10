package com.example.maquiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>  {
    private ArrayList<Content> mList;
    private OnItemClickListener mListener;
    private OnNoteListener mOnNoteListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        OnNoteListener OnNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
            this.OnNoteListener = listener;
        }
        @Override
        public void onClick(View v) {
            OnNoteListener.onNoteClick(getAdapterPosition());
        }
    }


    public ContentAdapter(ArrayList<Content> contentList, OnNoteListener OnNoteListener){
        mList = contentList;
        this.mOnNoteListener = OnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_topic_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v, mOnNoteListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Content contentItem = mList.get(i);
        String cardText = contentItem.getTopic() + ": " + contentItem.getTopicTitle();
        holder.mTextView.setText(cardText);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}

