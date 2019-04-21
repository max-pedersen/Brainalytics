package org.maxpedersen.maquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maquiz.R;

public class QuizFinishedActivity extends AppCompatActivity {

    TextView userMsg;
    TextView scoreTV;
    TextView XPTV;

    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);

        homeBtn = findViewById(R.id.homeButton);
        onClick(homeBtn);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);
        updateResults(score);
    }

    public void updateResults(int score){

        int xp = score*10;
        String user = UserValueCapture.nameGlobal;

        userMsg = findViewById(R.id.userMSG);
        scoreTV = findViewById(R.id.scoreTV);
        XPTV = findViewById(R.id.xpGain);

        String userMessage = "Great Work " + user;
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
}
