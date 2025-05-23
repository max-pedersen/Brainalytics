package org.maxpedersen.maquiz;

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

import org.maxpedersen.maquiz.R;

import java.util.ArrayList;
//Fragment with a RecyclerView to display the different weeks of topics to be quizzed on
public class QuizFragment extends Fragment implements QuizAdapter.OnNoteListener {
    private RecyclerView mRecyclerView;
    private QuizAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        ArrayList<Content> list = (ArrayList<Content>) DatabaseService.getDbInstance(getContext()).getAppDatabase()
                .contentDAO().getContents();
        buildRecyclerAdapter(rootView, list);
        int zID = UserValueCapture.zIDGlobal;
        String zIDString = Integer.toString(zID);
        Log.d("Quiz Fragment", zIDString);
        return rootView;
    }

    public void buildRecyclerAdapter(View rootView, ArrayList<Content> list){
        mRecyclerView = rootView.findViewById(R.id.contentRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new QuizAdapter(list,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void extract(int index){
        //Extract the week from the database and pass into the quiz
        Intent intent = new Intent(getContext(), QuizActivity.class);
        intent.putExtra("arrayIdx", index);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d("main", "onNoteClicked: clicked" + position);
        extract(position);
    }
}
