package com.example.tarek.u_glock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class slide3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide3);
    }
    public void next3(View view){
        Intent intent = new Intent(slide3.this,log.class);
        startActivity(intent);
    }
}
