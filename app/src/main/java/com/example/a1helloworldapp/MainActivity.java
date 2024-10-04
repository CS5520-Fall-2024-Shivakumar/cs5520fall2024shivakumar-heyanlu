package com.example.a1helloworldapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //about me button
        Button aboutMeButton = findViewById(R.id.button);
        aboutMeButton.setOnClickListener(new AboutMeButtonClickListener(this));

        //quick cal button
        Button quickCalButton = findViewById(R.id.button_cal);
        quickCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent helps to move from the current MainActivity to the new activity
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

    }
}