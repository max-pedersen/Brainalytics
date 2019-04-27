package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.maxpedersen.maquiz.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Button btnNews;
    Button btnNews2;
    LineChart historicalLc;

// TODO revise having both onCreateView and onViewCreated in same class and fix access to button
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnNews = (Button) getView().findViewById(R.id.btnNews);
        btnNews2 = (Button) getView().findViewById(R.id.btnNews2);


        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //News results page is navigated to
                Intent intent = new Intent(getContext(),NewsActivity.class);
                intent.putExtra("requestType", 1);

                startActivity(intent);
            }
        });

        btnNews2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //News results page is navigated to
                Intent intent = new Intent(getContext(),NewsActivity.class);
                intent.putExtra("requestType", 2);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
        String namePS = UserValueCapture.nameGlobal;
        int zIDPS = UserValueCapture.zIDGlobal;
        String userMsg = UserValueCapture.userMsg;
        String zIDString = Integer.toString(zIDPS);
        Log.d("Home Fragment", zIDString);
        getUserDetails(zIDPS, userMsg);
    }
    //Retrieves the details needed to update the home fragment
    public void getUserDetails(int zIDPS, String show){
        int zID = zIDPS;

        //Int is still a constant
        //Query the database

        int scoreFromDB =  DatabaseService.getDbInstance(this.getContext()).getAppDatabase().userResultJoinDAO().getUserSummedScore(zID);




        int score = scoreFromDB;
        int xpData = score*10;
        String xp = String.valueOf(xpData);
        changingTextView(show, zID, xp);
    }
    //Updates the home fragement
    public void changingTextView(String name, int zID, String xp){
        TextView nameTV = (TextView) getView().findViewById(R.id.welcomeUserTV);
        TextView xpTV = (TextView) getView().findViewById(R.id.xpNumber);
        nameTV.setText(name);
        xpTV.setText(xp);
    }
}
