package com.example.radud.quizapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.radud.quizapp.Question;
import com.example.radud.quizapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button playerButton;
    private Button adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerButton = (Button) findViewById(R.id.player_button);
        adminButton = (Button) findViewById(R.id.admin_button);

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayerButtonClicked();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdminButtonClicked();
            }
        });

        save();
    }

    private void save() {
//        List<String> stringList = new ArrayList<>();
//        stringList.add("Chisinau");
//        stringList.add("Ungheni");
//        stringList.add("Moscova");
//        stringList.add("Paris");
//        Question question = new Question("Capital of America", stringList, 1);
//        Question question1 = new Question("Capital of Azerbaijan", stringList, 2);
//        Question question2 = new Question("Capital of Russia", stringList, 3);
//        Question question3 = new Question("Capital of Moldova", stringList, 1);
//
//        List<Question> questions = new ArrayList<>();
//        questions.add(question);
//        questions.add(question1);
//        questions.add(question2);
//        questions.add(question3);
//
//        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
//        preferences.edit().putString(Constants.QUESTIONS_KEY, new Gson().toJson(questions)).apply();
    }

    private void onPlayerButtonClicked() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void onAdminButtonClicked() {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }
}
