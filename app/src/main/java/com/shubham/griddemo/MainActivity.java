package com.shubham.griddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText speed, width, height;
    private Button config;
    private String mSpeed, mHeight, mWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configureDefaults();
                Intent intent = new Intent(MainActivity.this, GridActivity.class);
                intent.putExtra("speed", mSpeed);
                intent.putExtra("height", mHeight);
                intent.putExtra("width", mWidth);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        speed = (EditText) findViewById(R.id.cf_speed);
        width = (EditText) findViewById(R.id.cf_column_width_et);
        height = (EditText) findViewById(R.id.cf_column_height_et);
        config = (Button) findViewById(R.id.cf_config_bt);
    }

    private void configureDefaults() {
        if (speed.getText().toString().isEmpty())
            mSpeed = "1";
        else
            mSpeed = speed.getText().toString();

        if (height.getText().toString().isEmpty())
            mHeight = "160";
        else
            mHeight = height.getText().toString();

        if (width.getText().toString().isEmpty())
            mWidth = "80";
        else
            mWidth = width.getText().toString();
    }
}