package com.moringaschool.medi_finder.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.medi_finder.ui.MainActivity;
import com.moringaschool.medi_finder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView (R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.logInButton) Button mLogInButton;
    @BindView(R.id.createAccountTextView) TextView mCreateAccountTextView;
    @BindView(R.id.firebaseProgressBar) ProgressBar mSignInProgressBar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mCreateAccountTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressbar();
                        Log.d(TAG, "SignInWithEmailAndPassWord:onComplete" + task.isSuccessful());
                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void showProgressbar(){
        mSignInProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar(){
        mSignInProgressBar.setVisibility(View.GONE);
    }
}
