package com.example.tarek.u_glock;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static int delay=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Welcome !",Toast.LENGTH_LONG).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prf = getSharedPreferences("variable",Context.MODE_PRIVATE);
                String res = prf.getString("var","");


                if (res=="") {
                    Intent intent1 = new Intent(MainActivity.this, slide1.class);
                    startActivity(intent1);

                    SharedPreferences.Editor editor = prf.edit();
                    editor.putString("var","true");
                    editor.apply();

                    finish();
                }
                else{
                    Intent intent2 = new Intent(MainActivity.this,slide1.class);
                    startActivity(intent2);

                    finish();

                }
            }
        },delay);
    }
}
