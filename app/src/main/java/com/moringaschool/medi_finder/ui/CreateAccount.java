package com.moringaschool.medi_finder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.medi_finder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccount extends AppCompatActivity {
    @BindView(R.id.createAccountText) TextView mCreateAccountText;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @BindView(R.id.createUserButton) Button mSubmitButton;
    @BindView(R.id.firebaseProgressBar) ProgressBar mProgressBar;
    @BindView(R.id.loginTextView) TextView mLoginAccountText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);


        mSubmitButton.setOnClickListener(this);
        mLoginAccountText.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == mLoginAccountText) {
            Intent intent = new Intent(CreateAccount.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == mSubmitButton) {
            createUser();
        }


        public void createUser(){
            final String name = mNameEditText.getText().toString().trim();
            final String email = mEmailEditText.getText().toString().trim();
            String password = mPasswordEditText.getText().toString().trim();
            String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();



        }
    }
}