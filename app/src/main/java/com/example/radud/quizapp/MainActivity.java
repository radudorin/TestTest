package com.example.radud.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    }

    private void onPlayerButtonClicked() {
        Log.d("TAGTAG", "onPlayerButtonClicked: ");
    }

    private void onAdminButtonClicked() {
        Log.d("TAGTAG", "onAdminButtonClicked: ");
    }
}
