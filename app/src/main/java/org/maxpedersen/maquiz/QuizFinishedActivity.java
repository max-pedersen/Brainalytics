package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maquiz.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class QuizFinishedActivity extends AppCompatActivity {

    TextView userMsg;
    TextView scoreTV;
    TextView XPTV;
    PieChart pieChart;
    Button homeBtn;
    String userMessage;
    ImageView reaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);

        homeBtn = findViewById(R.id.homeButton);
        onClick(homeBtn);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);
        updateResults(score);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Overall Database").allowMainThreadQueries().build();

        Result resultFromQuiz = new Result(0, score, UserValueCapture.zIDGlobal);
        db.resultDAO().insertResult(resultFromQuiz);
        Log.d(" from result methods", " " + resultFromQuiz.toString() + " updated to " + score);



        inflatePieChart(score);
        /* Approach #1- relying on Score being put into the User Table
        int existingScore = db.userDAO().getExistingScore((UserValueCapture.zIDGlobal));


        db.userDAO().setUpdatedScore(score, UserValueCapture.zIDGlobal);

        int verifyUpdate = db.userDAO().getExistingScore((UserValueCapture.zIDGlobal));

        Log.d(" from setUpdatedScore", " " + score + " updated to " + verifyUpdate); */

    }

    public void updateResults(int score){

        int xp = score*10;
        String user = UserValueCapture.nameGlobal;

        userMsg = findViewById(R.id.userMSG);
        scoreTV = findViewById(R.id.scoreTV);
        XPTV = findViewById(R.id.xpGain);
        reaction = findViewById(R.id.reaction);

        if(score > 5) {
            userMessage = "Great Work " + user + ".";
            reaction.setImageResource(R.drawable.emoji);
        }
        else{
            userMessage = "Practice more to improve, " + user + ".";
            reaction.setImageResource(R.drawable.emojistudy);
        }
        String scoreTotal = "Score: " + score + "/10";
        String xpGained = "XP Gained: " + xp;

        userMsg.setText(userMessage);
        scoreTV.setText(scoreTotal);
        XPTV.setText(xpGained);
    }

    private void onClick(Button button){
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                goHome();
            }
        });
    }

    public void goHome(){
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }

    public void inflatePieChart(int right){
        int correct = right;
        int wrong = 10-correct;
        pieChart = (PieChart) findViewById(R.id.pieCharts);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(correct, "Corect"));
        yValues.add(new PieEntry(wrong, "Wrong"));
        pieChart.animateY(4000, Easing.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(yValues, "Question");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(new int[]{
                Color.parseColor("#008000"),
                Color.parseColor("#21000000")
        });
        dataSet.setDrawValues(false);
        PieData data = new PieData((dataSet));
        pieChart.setData(data);
    }
}
