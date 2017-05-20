package com.example.radud.quizapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.radud.quizapp.Question;
import com.example.radud.quizapp.adapter.QuestionsAdapter;
import com.example.radud.quizapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radud on 20/05/2017.
 */

public class AdminHomeActivity extends AppCompatActivity {

    private QuestionsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        setTitle("Existing questions");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.questions_list_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionsAdapter(getQuestionList());
        recyclerView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.add_question_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateQuestions(getQuestionList());
    }

    private void onAddButtonClicked() {
        Intent intent = new Intent(this, CreateQuestionActivity.class);
        startActivity(intent);
    }

    private List<Question> getQuestionList() {
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        String questionsJson = preferences.getString(Constants.QUESTIONS_KEY, "");

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Question>>() {
        }.getType();
        return gson.fromJson(questionsJson, listType);
    }
}
