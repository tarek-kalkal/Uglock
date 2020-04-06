package com.example.tarek.u_glock;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Calendar;


public class signUp extends AppCompatActivity {

    EditText editTextEmail, editTextPassword,editTextPassword2,firstName,lastName;
    ProgressBar progressbar;

    // for the calender dialog
    TextView date,gd;
    DatePickerDialog.OnDateSetListener pop;

    TextView already;
    RadioGroup gender;

    String gndr="";
    Button button;
    String dat="";



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        date = (TextView) findViewById(R.id.date);
        already = (TextView) findViewById(R.id.already);
        gender = (RadioGroup) findViewById(R.id.gender);
        button = (Button) findViewById(R.id.button);
        gd = (TextView) findViewById(R.id.gd);


// Date picker start
        date.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        signUp.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        pop,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        pop = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                dat=dayOfMonth+"/"+month+"/"+year;
                date.setText(dat);
            }
        };
// Date picker end


// user already have an acount
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signUp.this,log.class);
                startActivity(intent);
            }
        });
// user already have an acount end


// Choose gender

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.man:
                        gndr ="man";
                        break;
                    case R.id.woman:
                        gndr ="woman";
                        break;
                }
            }
        });


    }

    public void signUp(View view){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextPassword2.getText().toString().trim();
        String firstname = firstName.getText().toString().trim();
        String lastname = lastName.getText().toString().trim();

        if(firstname.isEmpty()){
            firstName.setError("field is empty");
            firstName.requestFocus();
            return;
        }

        if(lastname.isEmpty()){
            lastName.setError("field is empty");
            lastName.requestFocus();
            return;
        }

        if(gndr.isEmpty()){
            gd.setError("gender is required");
            gd.requestFocus();
            return;
        }
        if(dat.isEmpty()){
            date.setError("date is required");
            date.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(!confirmPassword.equals(password)){
            Toast.makeText(getApplicationContext(),confirmPassword+" - "+password, Toast.LENGTH_SHORT).show();
            editTextPassword2.setError("Passwords not match");
            editTextPassword2.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Pleas inter a valide email!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User registred Succesfull", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(signUp.this, Dashboard.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registred", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
