package com.example.tarek.u_glock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class slide2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide2);
    }
    public void next2(View view){
        Intent intent = new Intent(slide2.this,slide3.class);
        startActivity(intent);
    }
}
