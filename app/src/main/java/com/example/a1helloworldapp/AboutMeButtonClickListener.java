package com.example.a1helloworldapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.content.Context;

public class AboutMeButtonClickListener implements View.OnClickListener {

    private Context context;

    public AboutMeButtonClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(context, "Name: Yanlu He\nEmail: he.yanl@northeastern.edu", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, AboutMeActivity.class);
            context.startActivity(intent);
    }
}
