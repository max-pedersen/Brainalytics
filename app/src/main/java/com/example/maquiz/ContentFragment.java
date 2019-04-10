package com.example.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ContentFragment extends Fragment implements ContentAdapter.OnNoteListener {
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutMaanger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content,container, false);
        ArrayList<Content> list = Content.getTestContent();
        buildRecyclerAdapter(rootView, list);
        return rootView;
    }

    public void buildRecyclerAdapter(View rootView, ArrayList<Content> list){
        mRecyclerView = rootView.findViewById(R.id.contentRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutMaanger = new LinearLayoutManager(getContext());
        mAdapter = new ContentAdapter(list,this);
        mRecyclerView.setLayoutManager(mLayoutMaanger);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void extract(int index){
        Intent intent = new Intent(getContext(), ContentDetailed.class);
        intent.putExtra("arrayIdx", index);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d("main", "onNoteClicked: clicked" + position);
        extract(position);
    }
}
