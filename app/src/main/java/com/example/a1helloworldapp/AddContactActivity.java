package com.example.a1helloworldapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    private EditText name;
    private EditText phoneNumber;
    private Button saveContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.editTextName);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);
        saveContactButton = findViewById(R.id.buttonSave);

        addContactButtonListener();
    }

    private void addContactButtonListener() {
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactName = name.getText().toString().trim();
                String contactPhoneNumber = phoneNumber.getText().toString().trim();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", contactName);
                resultIntent.putExtra("phone_number", contactPhoneNumber);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
