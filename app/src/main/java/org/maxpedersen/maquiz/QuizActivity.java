package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.maxpedersen.maquiz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    //Establish UI elements to be used in the class
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    TextView questionTV;
    TextView counterTV;
    TextView reviewTV;
    TextView scoreTV;
    List<Question> questionList;
    int counter=0;
    int score=0;
    List<Question> randomQuestionsFromWeek;
    List<Question> questionsFromCSV;
    ProgressBar mProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Allocates the variables to actual UI resources/assets
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = findViewById(R.id.radioGroup);
        questionTV = findViewById(R.id.question);
        counterTV = findViewById(R.id.counter);
        reviewTV = findViewById(R.id.reviewTV);
        scoreTV = findViewById(R.id.scoreTV);
        reviewTV.setVisibility(View.INVISIBLE);
        mProgressBar =findViewById(R.id.progressBar);
        Button buttonApply = findViewById(R.id.nextQ);
        //Manipulating the state of the submit button after every question
        buttonApply.setVisibility(View.GONE);
        buttonApply.setText("Review Question");
        //Sets the state of the button, there are two states
        UserValueCapture.setQuizActivityState(0);
        onClick(buttonApply);
        final Handler mHandler = new Handler();
        Intent intent = getIntent();
        int weekSpecified = intent.getIntExtra("arrayIdx", 1) + 2 ;


        //The statement belows queries the database for 10 question using the parameter weekSpecified, which is the index of the Array List + 1. i.e.
        // If someone presses Week 3 its index would be 2 and then + 1. That would be 3. 3 would be used as a parameter when getting the questions

      randomQuestionsFromWeek = DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase()
                .questionDAO().getSelectedQuiz(weekSpecified);
        generateQ(randomQuestionsFromWeek);
        progressBarThread(mHandler);
    }

    private void onClick(Button buttonApply){
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //If state = 0 do the following
                if (UserValueCapture.quizActivityState == 0) {
                    questionList = randomQuestionsFromWeek;
                    //passes the questionList to the method checkAnswer to see if the answer is correct
                    checkAnswer(questionList);
                    //Increments the counter
                    counter++;
                    String text = counter + "/10 Answered";
                    //Updates counter on the Quiz Activity
                    counterTV.setText(text);
                    Button buttonApply = findViewById(R.id.nextQ);
                    //If it's the last question the text on the button will change to Finish Quiz
                    if(counter == 10){
                        buttonApply.setText("Finish Quiz");
                    }
                    //Else the text on the button will change to Next Question
                    else{
                        buttonApply.setText("Next Question > ");
                    }
                    //if the state is = 1 do the following.
                    // Main purpose of state = 1 is to restart the quizactivity for the next question
                }
                else if(UserValueCapture.quizActivityState == 1){
                    //Gets the id of the radio button clicked
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    //Allocates that id to the class variable radioButton
                    radioButton = findViewById(radioID);
                    Button buttonApply = findViewById(R.id.nextQ);
                    //ButtonApply disappears
                    buttonApply.setVisibility(View.GONE);
                    //If counter is > 9 or 10 (since we limited it to 10 questions) it will call a method which will start the quiz finished activity
                    if (counter > 9) {
                        goToFinished();
                        //Else it generates the next question
                    } else {
                        buttonApply.setText("Review Question");
                        questionList = randomQuestionsFromWeek;
                        generateQ(questionList);
                        reviewTV.setVisibility(View.INVISIBLE);
                    }
                    //Resets the state to equal to 0 which is for checking answers
                    UserValueCapture.setQuizActivityState(0);
                }
            }

        });
    }

//The list of questions is passed here and based on the counter this method will generate a new question each time
    private void generateQ(List<Question> list){
        Question Quiz = list.get(counter);
        String question = ((Question) Quiz).getInfo();
        String optionA = ((Question) Quiz).getOption_1();
        String optionB = ((Question) Quiz).getOption_2();
        String optionC = ((Question) Quiz).getOption_3();
        String optionD = ((Question) Quiz).getOption_4();

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        questionTV.setText(question);
        radioButton1.setText(optionA);
        radioButton2.setText(optionB);
        radioButton3.setText(optionC);
        radioButton4.setText(optionD);

        clearColourRadioGroup(radioButton1, radioButton2, radioButton3, radioButton4);
    }
//Method to identify the radioButton selected
    public void checkButton(View V){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        Button buttonApply = findViewById(R.id.nextQ);
        buttonApply.setVisibility(View.VISIBLE);
    }
//Method to go to QuizActivity Finished and to finish this activity
    public void goToFinished(){
        Intent intent = new Intent(this,QuizFinishedActivity.class);
        intent.putExtra("Score", score);
        startActivity(intent);
        QuizActivity.this.finish();
    }


//This methods checks to see if the answers selected are correct
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkAnswer(List<Question> list){
        int radioID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioID);
        //Gets the int of the index of the radio button selected
        int idx = radioGroup.indexOfChild(radioButton);
        //Message saying whether you're right or wrong is set to be visible
        reviewTV.setVisibility(View.VISIBLE);
        Question Quiz = list.get(counter);
        //Extract what is the correct option for that Question
        String correctOption = Quiz.getCorrect_option();
        //Converts the correct option string into a int which is used to compare against the idx
        int correctIndex = correctConverter(correctOption);
        View test = radioGroup.getChildAt(correctIndex);
        int correctRadioId = test.getId();
        //Test to see if option selected is correct. Will visual elements if correct
        if(idx == correctIndex){
            reviewTV.setText("Correct. Nice work!");
            reviewTV.setTextColor(getColor(R.color.Green));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Green));
            score++;
            String text = "Score: "+score+"/10";
            scoreTV.setText(text);

        }
        //Test to see if option selected is correct. Will change visual elements if incorrect
        else if(idx != correctIndex){
            reviewTV.setText("You have chosen the wrong answer.");
            reviewTV.setTextColor(getColor(R.color.Red));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Red));
            RadioButton radioButtontemp = findViewById(correctRadioId);
            radioButtontemp.setBackgroundColor(getColor(R.color.Green));
            radioButtontemp.setTextColor(getResources().getColor(R.color.secondaryColor));
        }
        //Changes the state to 1 to allow the user to proceeds to the next question and geneate Q if they are under 10
        UserValueCapture.setQuizActivityState(1);
    }
    //When called it resets the visual state of the radio buttons
    public void clearColourRadioGroup(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4){

        radioGroup.clearCheck();

        radioButton1.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton2.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton3.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton4.setBackgroundResource(R.drawable.radio_flat_selector);

        radioButton1.setTextColor(getColorStateList(R.color.radio_flat_text_selector));
        radioButton2.setTextColor(getColorStateList(R.color.radio_flat_text_selector));
        radioButton3.setTextColor(getColorStateList(R.color.radio_flat_text_selector));
        radioButton4.setTextColor(getColorStateList(R.color.radio_flat_text_selector));

    }
    //Coverts the string formate of the correct option into an int value we can work with
    public int correctConverter(String correctOption){
        Log.d("Correct Converter", correctOption);
        String option1 = "option_1";
        String option2 = "option_2";
        String option3 = "option_3";
        String option4 = "option_4";

        if(correctOption.equalsIgnoreCase(option1)){
            int index = 0;
            Log.d("Correct If Statement", option1);
            return index;
        }
        if(correctOption.equalsIgnoreCase(option2)){
            int index = 1;
            Log.d("Correct If Statement", option2);
            return index;
        }
        if(correctOption.equalsIgnoreCase(option3)){
            int index = 2;
            Log.d("Correct If Statement", option3);
            return index;
        }
        else {
            int index = 3;
            Log.d("Correct If Statement", option4);
            return index;
        }

    }
    //Method for the progressbar, which increments every time a question is answered. This is a discriminate progress bar.
    public void progressBarThread(final Handler mHandler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(counter*10 < 100){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(counter*10);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }
}
