package com.example.radud.quizapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText body;
    private EditText a;
    private EditText b;
    private EditText c;
    private EditText d;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        setTitle("Fill the fields below");

        body = (EditText) findViewById(R.id.body_edit_text);
        a = (EditText) findViewById(R.id.a_edit_text);
        b = (EditText) findViewById(R.id.b_edit_text);
        c = (EditText) findViewById(R.id.c_edit_text);
        d = (EditText) findViewById(R.id.d_edit_text);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        Button button = (Button) findViewById(R.id.save_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String bodyText = body.getText().toString();
        String aText = a.getText().toString();
        String bText = b.getText().toString();
        String cText = c.getText().toString();
        String dText = d.getText().toString();

        // TODO: 20/05/2017 De facut validari dupa exemplu :
        if (bodyText.length() == 0) {
            Toast.makeText(this, "Please enter body first.", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> stringList = new ArrayList<>();
        stringList.add(aText);
        stringList.add(bText);
        stringList.add(cText);
        stringList.add(dText);

        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        Question question = new Question(bodyText, stringList, checkedRadioButtonId);

        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        List<Question> questionList = getQuestionList();
        questionList.add(question);

        preferences.edit().putString(Constants.QUESTIONS_KEY, new Gson().toJson(questionList)).apply();

        Toast.makeText(this, "Question saved!", Toast.LENGTH_SHORT).show();
        finish();
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
