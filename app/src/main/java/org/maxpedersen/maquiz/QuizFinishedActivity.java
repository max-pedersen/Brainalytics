package org.maxpedersen.maquiz;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

    TextView title;
    TextView userMsg;
    TextView scoreTV;
    TextView XPTV;
    PieChart pieChart;
    Button homeBtn;
    String userMessage;
    ImageView reaction;
    ImageView xpGraphic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);

        homeBtn = findViewById(R.id.homeButton);
        onClick(homeBtn);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);
        updateResults(score);



        Result resultFromQuiz = new Result(0, score, UserValueCapture.zIDGlobal);
        DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase().resultDAO().insertResult(resultFromQuiz);
        Log.d(" from result methods", " " + resultFromQuiz.toString() + " updated to " + score);

        inflatePieChart(score);
        animationOfElements();


    }

    public void updateResults(int score){

        int xp = score*10;
        String user = UserValueCapture.nameGlobal;

        title = findViewById(R.id.resultsTitle);
        userMsg = findViewById(R.id.userMSG);
        scoreTV = findViewById(R.id.scoreTV);
        XPTV = findViewById(R.id.xpGain);
        reaction = findViewById(R.id.reaction);
        xpGraphic = findViewById(R.id.xpGraphic);

        if(score > 5) {
            userMessage = "Great work " + user + "!";
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
        pieChart.animateY(5000, Easing.EaseInOutCubic);
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

    public void animationOfElements (){

        float transparency = 0.0f;

        title.setAlpha(transparency);
        userMsg.setAlpha(transparency);
        reaction.setAlpha(transparency);
        homeBtn.setAlpha(transparency);
        scoreTV.setAlpha(transparency);
        XPTV.setAlpha(transparency);
        pieChart.setAlpha(transparency);
        xpGraphic.setAlpha(transparency);

        ObjectAnimator animateResult = ObjectAnimator.ofFloat(title, View.ALPHA, 0.0f,1.0f).setDuration(1000);
        ObjectAnimator animateUserMsg = ObjectAnimator.ofFloat(userMsg, View.ALPHA, 0.0f,1.0f).setDuration(500);
        ObjectAnimator animateEmoji = ObjectAnimator.ofFloat(reaction, View.ALPHA, 0.0f,1.0f).setDuration(500);
        ObjectAnimator animateButton = ObjectAnimator.ofFloat(homeBtn, View.ALPHA, 0.0f,1.0f).setDuration(500);
        ObjectAnimator animateScoreTV = ObjectAnimator.ofFloat(scoreTV, View.ALPHA, 0.0f,1.0f).setDuration(400);
        ObjectAnimator animateXPTV = ObjectAnimator.ofFloat(XPTV, View.ALPHA, 0.0f,1.0f).setDuration(400);
        ObjectAnimator animateGraph = ObjectAnimator.ofFloat(pieChart, View.ALPHA, 0.0f,1.0f).setDuration(400);
        ObjectAnimator animateXPGraphic = ObjectAnimator.ofFloat(xpGraphic, View.ALPHA, 0.0f,1.0f).setDuration(400);

        AnimatorSet setFinal = new AnimatorSet();
        AnimatorSet subSetFirst = new AnimatorSet();
        AnimatorSet subSetSecond = new AnimatorSet();

        subSetFirst.playTogether(animateUserMsg, animateEmoji, animateButton);
        subSetSecond.playTogether(animateScoreTV, animateXPTV, animateGraph, animateXPGraphic);
        setFinal.playSequentially(animateResult, subSetFirst, subSetSecond);

        setFinal.start();
    }
}
