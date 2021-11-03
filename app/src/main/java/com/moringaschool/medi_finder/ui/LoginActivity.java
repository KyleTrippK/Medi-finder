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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView (R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.logInButton) Button mLogInButton;
    @BindView(R.id.createAccountTextView) TextView mCreateAccountTextView;
    @BindView(R.id.firebaseProgressBar) ProgressBar mSignInProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mCreateAccountTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == mLogInButton){
            loginWithPassword();
            showProgressbar();

        }
        if( view == mCreateAccountTextView){
            Intent intent = new Intent(LoginActivity.this, CreateAccount.class);
            startActivity(intent);
            finish();
        }

    }

    private void loginWithPassword(){
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if(email.equals("")){
            mEmailEditText.setError("Please Enter a valid Email");
            return;
        }
        if(password.equals("")){
            mPasswordEditText.setError("Please Fill your Password");
            return;
        }
    }

    private void showProgressbar(){
        mSignInProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar(){
        mSignInProgressBar.setVisibility(View.GONE);
    }
}