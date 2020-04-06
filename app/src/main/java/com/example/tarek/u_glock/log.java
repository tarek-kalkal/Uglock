package com.example.tarek.u_glock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log extends AppCompatActivity {

    EditText password , email;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        password =(EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();

    }

    public void register(View view){
        Intent intent = new Intent(log.this, signUp.class);
        startActivity(intent);
    }

    public void signIn(View view){
        String em = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(em.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("Pleas inter a valide email!");
            email.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
             if(task.isSuccessful()){
                 Intent intent = new Intent(log.this, Dashboard.class);
                 intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
             }
             else {
                 Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
             }
            }
        });


    }
}
