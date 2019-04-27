package org.maxpedersen.maquiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
//Need to change this to quiz adapter once we have the database
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private ArrayList<Content> mList;
    private QuizAdapter.OnItemClickListener mListener;
    private QuizAdapter.OnNoteListener mOnNoteListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(QuizAdapter.OnItemClickListener listener){
        mListener = listener;
    }
    //Creates the viewholders
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        QuizAdapter.OnNoteListener OnNoteListener;

        public ViewHolder(@NonNull View itemView, QuizAdapter.OnNoteListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.newsTitleTV);
            itemView.setOnClickListener(this);
            this.OnNoteListener = listener;
        }
        @Override
        public void onClick(View v) {
            OnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    //Constructor for the adapter to allow the class to pass in a list and an OnNoteListener to
    // allow for the RecyclerView to be filled and clicked
    public QuizAdapter(ArrayList<Content> contentList, QuizAdapter.OnNoteListener OnNoteListener){
        mList = contentList;
        this.mOnNoteListener = OnNoteListener;
    }
    //ViewHolder is created. The primary purpose of this is to link the cardview to the adapter
    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_quiztopic_cardview, parent, false);
        QuizAdapter.ViewHolder vh = new ViewHolder(v, mOnNoteListener);
        return vh;
    }
    //When the ViewHolder has been established, the values within the cardview will be assigned
    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int i) {
        Content contentItem = mList.get(i);
        String cardText = contentItem.getTopic();
        holder.mTextView.setText(cardText);
    }
    //gets the size of the recycler view
    @Override
    public int getItemCount() {
        return mList.size();
    }
    //Interface of OnNoteListener
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}


