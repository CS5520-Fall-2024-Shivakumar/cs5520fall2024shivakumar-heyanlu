package com.example.a1helloworldapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * Add new contact to the contactActivity
 */
public class AddContactActivity extends AppCompatActivity {
    private EditText name;
    private EditText phoneNumber;
    private Button saveContactButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.editTextName);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);
        saveContactButton = findViewById(R.id.buttonSave);

        addContactButtonListener();

        Button backButton = findViewById(R.id.backButton3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addContactButtonListener() {
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactName = name.getText().toString().trim();
                String contactPhoneNumber = phoneNumber.getText().toString().trim();

                if (contactName.isEmpty() || contactPhoneNumber.isEmpty()) {
                    Snackbar.make(findViewById(R.id.buttonSave), "Name or Phone number cannot be empty", Snackbar.LENGTH_SHORT).show();
                } else {
                    //Adds an extra piece of data to the resultIntent (pass data back to the calling activity)
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", contactName);
                    resultIntent.putExtra("phone_number", contactPhoneNumber);

                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
