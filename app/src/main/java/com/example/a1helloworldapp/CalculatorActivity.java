package com.example.a1helloworldapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    private TextView calcText;
    private StringBuilder expression = new StringBuilder();
    private boolean isNewCalculation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        calcText = findViewById(R.id.calcText);

        calButtonListeners();
    }

    private void calButtonListeners() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
                R.id.buttonDelete, R.id.buttonEqual
        };

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();

                switch (buttonText) {
                    case "=":
                        getResult();
                        isNewCalculation = true;
                        break;
                    case "X":
                        if (expression.length() > 0) {
                            expression.deleteCharAt(expression.length() - 1);
                        }
                        break;
                    default:
                        if (isNewCalculation) {
                            expression.setLength(0);
                            isNewCalculation = false;
                        }
                        expression.append(buttonText);
                        break;
                }
                calcText.setText(expression.toString());
            }
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(onClickListener);
        }

    }


}
