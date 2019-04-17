package com.example.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    TextView questionTV;
    List<Quiz> questionList;

    static int counter=0;

    final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Quiz Database").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = findViewById(R.id.radioGroup);
        questionTV = findViewById(R.id.question);
        Button buttonApply = findViewById(R.id.nextQ);
        buttonApply.setVisibility(View.GONE);
        onClick(buttonApply);
        //Use the extra in the intent to select which weeks quiz to use
        questionList = getQuizList();
        generateQ(questionList);
    }

    private void onClick(Button buttonApply){
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                questionTV.setText("Your choice : " + radioButton.getText());
                Button buttonApply = findViewById(R.id.nextQ);
                buttonApply.setVisibility(View.GONE);
                counter++;
                if(counter == 10){
                    buttonApply.setText("Finish Quiz");
                    questionList = getQuizList();
                    generateQ(questionList);
                    //Insert data into the database for the quiz result including the session id
                }
                if(counter > 10){
                    goToFinished();

                }
                else{
                    questionList = getQuizList();
                    generateQ(questionList);
                    //Insert data into the database for the quiz result including the session id
                }
                //Ideally we should send the data of the radioButton selected to a DB along with the session id and the question id
                //This button clicked should also active generate() to go to the next question, however an if statement must be used to check if an option has selected then we increment counter
                //there should be also an if statement checking if the counter is > 10. If that is the case we need to diver to a method which will have an intent to go the quiz
                //finished activity
            }
        });
    }

    private void generateQ(List<Quiz> list){
        Object Quiz = list.get(counter);
        String question = ((Quiz) Quiz).getQuestion();
        String optionA = ((Quiz) Quiz).getOption_1();
        String optionB = ((Quiz) Quiz).getOption_2();
        String optionC = ((Quiz) Quiz).getOption_3();
        String optionD = ((Quiz) Quiz).getOption_4();
        String counterTV = counter + "/10 Answered";

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        questionTV.setText(question);
        radioButton1.setText(optionA);
        radioButton2.setText(optionB);
        radioButton3.setText(optionC);
        radioButton4.setText(optionD);

    }

    public void checkButton(View V){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        Button buttonApply = findViewById(R.id.nextQ);
        buttonApply.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void goToFinished(){
        Intent intent = new Intent(this,QuizFinishedActivity.class);
        startActivity(intent);
    }

    public List<Quiz> getQuizList(){
        List<Quiz> questionList = db.quizDao().getSelectedQuiz("Week 5");
        return questionList;
    }
}
