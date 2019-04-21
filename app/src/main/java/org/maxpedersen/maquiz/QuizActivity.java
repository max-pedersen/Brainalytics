package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maquiz.R;

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
    TextView reivewTV;
    TextView scoreTV;
    List<Question> questionList;
    static int counter=0;
    static int score=0;

    //final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Overall Database")
    //.allowMainThreadQueries().build();
    // this throws an error and causes the app to crash completely, as need to set up database as per Room properly



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = findViewById(R.id.radioGroup);
        questionTV = findViewById(R.id.question);
        counterTV = findViewById(R.id.counter);
        reivewTV = findViewById(R.id.reviewTV);
        scoreTV = findViewById(R.id.scoreTV);
        reivewTV.setVisibility(View.GONE);
        Button buttonApply = findViewById(R.id.nextQ);
        buttonApply.setVisibility(View.GONE);
        UserValueCapture.setQuizActivityState(0);
        onClick(buttonApply);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Overall Database").allowMainThreadQueries().build();

        Question testQuestion = new Question(300, 1, "Which of the following is not a part of the simple approach to build a good chart?",
                "Understand and Create",
                "Refine", "Present and Practice",
                "Re-design", "option_4", "multiple_choice",
                "https://www.youtube.com/watch?v=IGXVaVWD_3I",
                        "Good Charts Ch1-4");

        //Use the extra in the intent to select which weeks quiz to use

        questionList = getQuizList();
        generateQ(questionList);
    }

    private void onClick(Button buttonApply){
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserValueCapture.quizActivityState == 0) {
                    counter++;
                    questionList = getQuizList();
                    checkAnswer(questionList);
                    String text = counter + "/10 finished";
                    counterTV.setText(text);
                    Button buttonApply = findViewById(R.id.nextQ);
                    buttonApply.setText("Review Question");
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
                    //counter++;
                    if (counter == 10) {
                        buttonApply.setText("Finish Question");
                        questionList = getQuizList();
                        generateQ(questionList);
                        reivewTV.setVisibility(View.GONE);
                        //Insert data into the database for the quiz result including the session id
                    }
                    if (counter > 10) {
                        goToFinished();
                    } else {
                        buttonApply.setText("Review Question");
                        questionList = getQuizList();
                        generateQ(questionList);
                        reivewTV.setVisibility(View.GONE);
                        //Insert data into the database for the quiz result including the session id

                    }
                    UserValueCapture.setQuizActivityState(0);
                    Log.d("State 1", "The state at the end is "+ UserValueCapture.quizActivityState);
                }
                }

        });
    }

    private void generateQ(List<Question> list){
        Question Quiz = list.get(counter);
        String question = ((Question) Quiz).getInfo();
        String optionA = ((Question) Quiz).getOption_1();
        String optionB = ((Question) Quiz).getOption_2();
        String optionC = ((Question) Quiz).getOption_3();
        String optionD = ((Question) Quiz).getOption_4();
        String counterTV = counter + "/10 Answered";

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        clearColourRadioGroup(radioButton1, radioButton2, radioButton3, radioButton4);

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

    public List<Question> getQuizList(){
        //List<Question> questionList = db.questionDAO().getSelectedQuiz("Week 5");
        //return questionList;
        List<Question> questionlist = new ArrayList<>();
        questionlist.add(new Question(3333, 3, "What is a chart?",
                "Chart", "Dog", "Fish", "Moo", "option_1", "mc",
                "youtube", "chart"));

        questionlist.add(new Question(3334, 3, "What is a chart?2",
                "Chart", "Dog", "Fish", "Moo", "option_1", "mc",
                "youtube", "chart"));

        questionlist.add(new Question(3335, 3, "What is a chart?3",
                "Chart", "Dog", "Fish", "Moo", "option_1", "mc",
                "youtube", "chart"));

        questionlist.add(new Question(3336, 3, "What is a chart?4",
                "Chart", "Dog", "Fish", "Moo", "option_1", "mc",
                "youtube", "chart"));

        questionlist.add(new Question(3337, 3, "What is a chart?5",
                "Chart", "Dog", "Fish", "Moo", "option_1", "mc",
                "youtube", "chart"));

        return questionlist;

    }

    public void checkAnswer(List<Question> list){
        int radioID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioID);
        int idx = radioGroup.indexOfChild(radioButton);
        reivewTV.setVisibility(View.VISIBLE);
        int correctIndex = 5;
        Question Quiz = list.get(counter);
        if(Quiz.getCorrect_option()== "option_1"){
            correctIndex = 0;
        }

        if(Quiz.getCorrect_option()== "option_2"){
            correctIndex = 1;
        }

        if(Quiz.getCorrect_option()== "option_3"){
            correctIndex = 2;
        }

        else if(Quiz.getCorrect_option()== "option_4"){
            correctIndex = 3;
        }

        View test = radioGroup.getChildAt(correctIndex);
        int correctRadioId = test.getId();

        Log.d("index", "radio idx: " + idx);
        Log.d("Correct index", "crt idx: " + correctIndex);
        Log.d("Correct id", "crt id: " + correctRadioId);

        if(idx == correctIndex){
            reivewTV.setText("You have choosen the correct answer");
            reivewTV.setTextColor(getColor(R.color.Green));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Green));
            score++;
            String text = "Score: "+score+"/10";
            scoreTV.setText(text);

        }
        else if(idx != correctIndex){
            reivewTV.setText("You have choosen the wrong answer");
            reivewTV.setTextColor(getColor(R.color.Red));
            radioButton = findViewById(radioID);
            radioButton.setBackgroundColor(getColor(R.color.Red));
            radioButton2 = findViewById(correctRadioId);
            radioButton2.setBackgroundColor(getColor(R.color.Green));
        }
        UserValueCapture.setQuizActivityState(1);
        Log.d("Answer Checker", "State of button is " + UserValueCapture.quizActivityState);
    }

    public void clearColourRadioGroup(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4){
        radioButton1.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton2.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton3.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButton4.setBackgroundResource(R.drawable.radio_flat_selector);
    }

}
