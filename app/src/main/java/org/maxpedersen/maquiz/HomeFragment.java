package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.maxpedersen.maquiz.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String namePS = UserValueCapture.nameGlobal;
        int zIDPS = UserValueCapture.zIDGlobal;
        String userMsg = UserValueCapture.userMsg;
        String zIDString = Integer.toString(zIDPS);
        Log.d("Home Fragment", zIDString);
        getUserDetails(zIDPS, userMsg);

    }

    public void getUserDetails(int zIDPS, String show){

        int zID = zIDPS;
        //Int is still a constant
        //Query the database

        int scoreFromDB =  DatabaseService.getDbInstance(this.getContext()).getAppDatabase().resultDAO().getSummedScore(zID);


        int score = scoreFromDB;
        int xpData = score*10;
        String xp = String.valueOf(xpData);
        changingTextView(show, zID, xp);
    }

    public void changingTextView(String name, int zID, String xp){
        TextView nameTV = (TextView) getView().findViewById(R.id.welcomeUserTV);
        TextView xpTV = (TextView) getView().findViewById(R.id.xpNumber);
        nameTV.setText(name);
        xpTV.setText(xp);
    }




}
