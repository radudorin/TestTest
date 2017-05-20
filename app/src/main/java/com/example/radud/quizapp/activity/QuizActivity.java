package com.example.radud.quizapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radud.quizapp.Question;
import com.example.radud.quizapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radud on 20/05/2017.
 */

public class QuizActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton a;
    private RadioButton b;
    private RadioButton c;
    private RadioButton d;

    private RadioGroup radioGroup;

    private TextView totalQuestions;
    private TextView rightAnswers;
    private TextView body;

    private int currentQuestion;
    private int rightAnswersCount;
    private List<Question> questionList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionList = getQuestionList();
        if (questionList.size() == 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_quiz);

        a = (RadioButton) findViewById(R.id.a_radio_button);
        b = (RadioButton) findViewById(R.id.b_radio_button);
        c = (RadioButton) findViewById(R.id.c_radio_button);
        d = (RadioButton) findViewById(R.id.d_radio_button);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        totalQuestions = (TextView) findViewById(R.id.total_questions_text_view);
        rightAnswers = (TextView) findViewById(R.id.right_answers_text_view);
        body = (TextView) findViewById(R.id.body_text_view);

        setQuestionDetails(currentQuestion);
    }

    private List<Question> getQuestionList() {
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        String questionsJson = preferences.getString(Constants.QUESTIONS_KEY, "");

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Question>>() {
        }.getType();
        return gson.fromJson(questionsJson, listType);
    }

    private void setQuestionDetails(int position) {
        if (position == questionList.size()) {
            body.setText("Congratulations! You finished the quiz");
            radioGroup.setVisibility(View.GONE);
            return;
        }

        Question question = questionList.get(position);
        body.setText(question.getBody());
        a.setText(question.getResponseList().get(0));
        b.setText(question.getResponseList().get(1));
        c.setText(question.getResponseList().get(2));
        d.setText(question.getResponseList().get(3));

        rightAnswers.setText("Right answers : " + rightAnswersCount);
        totalQuestions.setText("Total questions : " + (currentQuestion + 1) + " / " + questionList.size());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton currentCheck = (RadioButton) findViewById(checkedId);
        if (currentCheck == null || !currentCheck.isChecked()) {
            return;
        }

        Question question = questionList.get(currentQuestion);

        int rightRadioButtonId = 0;

        switch (question.getRightResponse()) {
            case 0:
                rightRadioButtonId = R.id.a_radio_button;
                break;
            case 1:
                rightRadioButtonId = R.id.b_radio_button;
                break;
            case 2:
                rightRadioButtonId = R.id.c_radio_button;
                break;
            case 3:
                rightRadioButtonId = R.id.d_radio_button;
                break;
        }

        if (rightRadioButtonId == checkedId) {
            rightAnswersCount++;
            Toast.makeText(this, "You are correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry, you are wrong!", Toast.LENGTH_LONG).show();
            // TODO: 20/05/2017 De zis in toast care este raspunsul corect.
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestion++;
                radioGroup.clearCheck();
                setQuestionDetails(currentQuestion);
            }
        }, 1000);
    }
}
