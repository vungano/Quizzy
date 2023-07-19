package com.example.quizzy.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.Data.QuestionsBank;
import com.example.quizzy.Models.QuestionModel;
import com.example.quizzy.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    List<QuestionModel> questionsArrayList = new ArrayList<>();
    public int quizId;
    TextView question;
    private RelativeLayout optionA, optionB, optionC, optionD;
    private TextView optionAText, optionBText, optionCText, optionDText;
    private TextView letterA, letterB, letterC, letterD;
    private int currentQuestionPosition = 0;
    private String selectedOption = "";
    private final int TIME_DELAY = 1200; //TIME DELAY BETWEEN EACH QUESTION AND THE NEXT

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        optionAText = findViewById(R.id.optionAText);
        optionBText = findViewById(R.id.optionBText);
        optionCText = findViewById(R.id.optionCText);
        optionDText = findViewById(R.id.optionDText);

        letterA = findViewById(R.id.letterA);
        letterB = findViewById(R.id.letterB);
        letterC = findViewById(R.id.letterC);
        letterD = findViewById(R.id.letterD);

        /*
        If the user presses the redo button a quiz id will get
        passed into the activity and that same quiz will be returned
        else a new random quiz will be selected
         */
        int passedQuizId = getIntent().getIntExtra("repeatQuizId",-1);

        if (passedQuizId != -1){
            quizId = passedQuizId;
        }
        else{
            Random random = new Random();
            quizId = random.nextInt(11);
        }

        questionsArrayList = QuestionsBank.getQuestions(quizId);

        question.setText(questionsArrayList.get(0).getQuestion());
        optionAText.setText(questionsArrayList.get(0).getOptionA());
        optionBText.setText(questionsArrayList.get(0).getOptionB());
        optionCText.setText(questionsArrayList.get(0).getOptionC());
        optionDText.setText(questionsArrayList.get(0).getOptionD());

        /*
        Whenever an option is clicked the colour changes to red for that option and the answer
        is saved to the Questions list. After that the correct answer is revealed and if the selected option
        is the correct answer it will turn green else another option turns green
        */

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = optionAText.getText().toString();
                    optionA.setBackgroundResource(R.drawable.red_answer);
                    optionAText.setTextColor(Color.WHITE);
                    letterA.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsArrayList.get(currentQuestionPosition).setUserAnswer(selectedOption);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            changeToNextQuestion();
                        }
                    },TIME_DELAY);
                }
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = optionBText.getText().toString();
                    optionB.setBackgroundResource(R.drawable.red_answer);
                    optionBText.setTextColor(Color.WHITE);
                    letterB.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsArrayList.get(currentQuestionPosition).setUserAnswer(selectedOption);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            changeToNextQuestion();
                        }
                    },TIME_DELAY);
                }
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = optionCText.getText().toString();
                    optionC.setBackgroundResource(R.drawable.red_answer);
                    optionCText.setTextColor(Color.WHITE);
                    letterC.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsArrayList.get(currentQuestionPosition).setUserAnswer(selectedOption);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            changeToNextQuestion();
                        }
                    },TIME_DELAY);
                }
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = optionDText.getText().toString();
                    optionD.setBackgroundResource(R.drawable.red_answer);
                    optionDText.setTextColor(Color.WHITE);
                    letterD.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsArrayList.get(currentQuestionPosition).setUserAnswer(selectedOption);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            changeToNextQuestion();
                        }
                    },TIME_DELAY);
                }
            }
        });
    }

    //Change to next question if there is still a next question else quiz is over
    private void changeToNextQuestion(){
        currentQuestionPosition++;
        if (currentQuestionPosition<questionsArrayList.size()){
            selectedOption = "";

            optionA.setBackgroundResource(R.drawable.long_white_button);
            optionAText.setTextColor(Color.BLACK);

            optionB.setBackgroundResource(R.drawable.long_white_button);
            optionBText.setTextColor(Color.BLACK);

            optionC.setBackgroundResource(R.drawable.long_white_button);
            optionCText.setTextColor(Color.BLACK);

            optionD.setBackgroundResource(R.drawable.long_white_button);
            optionDText.setTextColor(Color.BLACK);

            question.setText(questionsArrayList.get(currentQuestionPosition).getQuestion());
            optionAText.setText(questionsArrayList.get(currentQuestionPosition).getOptionA());
            optionBText.setText(questionsArrayList.get(currentQuestionPosition).getOptionB());
            optionCText.setText(questionsArrayList.get(currentQuestionPosition).getOptionC());
            optionDText.setText(questionsArrayList.get(currentQuestionPosition).getOptionD());

        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(QuestionActivity.this,ResultsActivity.class);
                    intent.putExtra("score",getCorrectAnswers());
                    intent.putExtra("quizId",quizId);
                    startActivity(intent);
                    finish();
                }
            },TIME_DELAY);
        }
    }

    //Reveal the correct answer
    private void revealAnswer(){
        final String getAnswer = questionsArrayList.get(currentQuestionPosition).getCorrectAnswer();

        if (optionAText.getText().toString().equals(getAnswer)){
            optionA.setBackgroundResource(R.drawable.green_answer);
            optionAText.setTextColor(Color.WHITE);
            letterA.setTextColor(Color.WHITE);
        }
        else if(optionBText.getText().toString().equals(getAnswer)){
            optionB.setBackgroundResource(R.drawable.green_answer);
            optionBText.setTextColor(Color.WHITE);
            letterB.setTextColor(Color.WHITE);
        }
        else if(optionCText.getText().toString().equals(getAnswer)){
            optionC.setBackgroundResource(R.drawable.green_answer);
            optionCText.setTextColor(Color.WHITE);
            letterC.setTextColor(Color.WHITE);
        }
        else if(optionDText.getText().toString().equals(getAnswer)){
            optionD.setBackgroundResource(R.drawable.green_answer);
            optionDText.setTextColor(Color.WHITE);
            letterD.setTextColor(Color.WHITE);
        }
    }


    //After quiz is over get correct answers
    private int getCorrectAnswers(){
        int correctAnswers = 0;
        for (int i =0; i<questionsArrayList.size();i++){
            final String getUserSelectedAnswer = questionsArrayList.get(i).getUserAnswer();
            final String correctAnswer = questionsArrayList.get(i).getCorrectAnswer();

            if (getUserSelectedAnswer.equals(correctAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }
}