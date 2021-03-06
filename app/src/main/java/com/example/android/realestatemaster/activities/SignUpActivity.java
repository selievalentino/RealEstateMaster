package com.example.android.realestatemaster.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.android.realestatemaster.HelperClasses.Utilities;
import com.example.android.realestatemaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import okhttp3.internal.Util;

public class SignUpActivity extends AppCompatActivity {
private EditText emailEt, passwordEt;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        firebaseAuth = FirebaseAuth.getInstance();

    }
    public void signUp(View view){
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Utilities.showToast(getApplicationContext(), "Enter email address!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Utilities.showToast(getApplicationContext(), "Enter password!");
            return;
        }

        if (password.length() < 6) {
            Utilities.showToast(getApplicationContext(), "Password too short, enter minimum 6 characters!");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Utilities.showToast(getApplicationContext(),"Error signing up");
                }
                else{
                    startActivity(new Intent(getApplicationContext(),LoginSignUpPage.class));
                    finish();
                }
            }
        });
    }
}
