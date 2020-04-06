package com.example.tarek.u_glock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class slide1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide1);
    }
    public void next1(View view){
        Intent intent = new Intent(slide1.this, slide2.class);
        startActivity(intent);
    }
}
