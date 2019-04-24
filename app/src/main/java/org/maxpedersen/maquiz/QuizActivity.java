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
        buttonApply.setVisibility(View.GONE);
        buttonApply.setText("Review Question");
        UserValueCapture.setQuizActivityState(0);
        onClick(buttonApply);
        final Handler mHandler = new Handler();
        Intent intent = getIntent();
        int weekSpecified = intent.getIntExtra("arrayIdx", 1) +1;

        questionsFromCSV = null;
        try {
            questionsFromCSV = readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase().questionDAO().insertQuestionBatch(questionsFromCSV);
        //The following request would then show the variable i instead of week
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
                if (UserValueCapture.quizActivityState == 0) {
                    String counterString = Integer.toString(counter);
                    Log.d("Click counter increment", counterString);
                    questionList = randomQuestionsFromWeek;
                    checkAnswer(questionList);
                    counter++;
                    String text = counter + "/10 Answered";
                    counterTV.setText(text);
                    Button buttonApply = findViewById(R.id.nextQ);
                    if(counter == 10){
                        buttonApply.setText("Finish Quiz");
                    }
                    else{
                        buttonApply.setText("Next Question > ");
                    }
                    //Insert data into the database for the quiz result including the session id
                }
                //Ideally we should send the data of the radioButton selected to a DB along with the session id and the question id
                //This button clicked should also active generate() to go to the next question, however an if statement must be used to check if an option has selected then we increment counter
                //there should be also an if statement checking if the counter is > 10. If that is the case we need to diver to a method which will have an intent to go the quiz
                //finished activity
                else if(UserValueCapture.quizActivityState == 1){
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    //questionTV.setText("Your choice : " + radioButton.getText());
                    Button buttonApply = findViewById(R.id.nextQ);
                    buttonApply.setVisibility(View.GONE);
                    if (counter > 9) {
                        goToFinished();
                    } else {
                        buttonApply.setText("Review Question");
                        questionList = randomQuestionsFromWeek;
                        generateQ(questionList);
                        reviewTV.setVisibility(View.INVISIBLE);
                        //Insert data into the database for the quiz result including the session id

                    }
                    UserValueCapture.setQuizActivityState(0);
                    Log.d("State 1", "The state at the end is "+ UserValueCapture.quizActivityState);
                }
            }

        });
    }


    // Null pointer exception stems from here, questionList being returned is apparently blank
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Question> readCSV() throws IOException {
        List<Question> questionList = new ArrayList<>();


        InputStreamReader is = new InputStreamReader(getAssets()
                .open("Questions.csv"));

        BufferedReader reader = new BufferedReader(is);
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            int i = 20;
            String[] qAttrs = line.split(",");
            Log.d("hello", line);
            Question q = new Question(0, Integer.parseInt(qAttrs[0]), qAttrs[1],
                    qAttrs[2], qAttrs[3], qAttrs[4], qAttrs[5], qAttrs[6], qAttrs[7],
                    qAttrs[8], qAttrs[9]);
            Log.d("hello", q.toString());
            i = i + 1;
            questionList.add(q);
        }

        return questionList;
    }



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

    public void checkButton(View V){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        Button buttonApply = findViewById(R.id.nextQ);
        buttonApply.setVisibility(View.VISIBLE);
        //Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void goToFinished(){
        Intent intent = new Intent(this,QuizFinishedActivity.class);
        intent.putExtra("Score", score);
        startActivity(intent);
        QuizActivity.this.finish();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkAnswer(List<Question> list){
        int radioID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioID);
        int idx = radioGroup.indexOfChild(radioButton);
        reviewTV.setVisibility(View.VISIBLE);
        Question Quiz = list.get(counter);
        String correctOption = Quiz.getCorrect_option();
        int correctIndex = correctConverter(correctOption);
        Log.d("Correct Option", Quiz.getCorrect_option());
        View test = radioGroup.getChildAt(correctIndex);
        int correctRadioId = test.getId();
        Log.d("index", "radio idx: " + idx);
        Log.d("Correct index", "crt idx: " + correctIndex);
        Log.d("Correct id", "crt id: " + correctRadioId);

        if(idx == correctIndex){
            reviewTV.setText("Correct. Nice work!");
            reviewTV.setTextColor(getColor(R.color.Green));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Green));
            score++;
            String text = "Score: "+score+"/10";
            scoreTV.setText(text);

        }
        else if(idx != correctIndex){
            reviewTV.setText("You have chosen the wrong answer.");
            reviewTV.setTextColor(getColor(R.color.Red));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Red));
            RadioButton radioButtontemp = findViewById(correctRadioId);
            radioButtontemp.setBackgroundColor(getColor(R.color.Green));
            radioButtontemp.setTextColor(getResources().getColor(R.color.secondaryColor));
        }
        UserValueCapture.setQuizActivityState(1);
        Log.d("Answer Checker", "State of button is " + UserValueCapture.quizActivityState);
    }

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
