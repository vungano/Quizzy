package com.example.quizzy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.R;

public class ResultsActivity extends AppCompatActivity {

    TextView userMark;
    RelativeLayout newQuiz, redoQuiz, cancelApp;
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final int mark = getIntent().getIntExtra("score",0);
        final int quizNumber = getIntent().getIntExtra("quizId",0);

        userMark = findViewById(R.id.userMark);
        newQuiz = findViewById(R.id.new_quiz);
        redoQuiz = findViewById(R.id.redo);
        cancelApp = findViewById(R.id.cancel);

        try {
            userMark.setText(String.valueOf(mark));

            if (mark>=7){
                userMark.setBackgroundResource(Color.GREEN);
            }
            else if(mark<5){
                userMark.setBackgroundResource(Color.RED);
            }

        }
        catch (Exception e){
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this,QuestionActivity.class);
                intent.putExtra("repeatQuizId",-1);
                startActivity(intent);
                finish();
            }
        });

        redoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this,QuestionActivity.class);
                intent.putExtra("repeatQuizId",quizNumber);
                startActivity(intent);
                finish();
            }
        });

        cancelApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}