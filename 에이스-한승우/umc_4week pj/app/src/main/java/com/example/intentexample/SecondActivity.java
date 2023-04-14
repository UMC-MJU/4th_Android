package com.example.intentexample;

import  android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_second = findViewById(R.id.tv_second);

        Intent intent = getIntent();
        String str = intent.getStringExtra( "str");

        tv_second.setText(str);
    }
}