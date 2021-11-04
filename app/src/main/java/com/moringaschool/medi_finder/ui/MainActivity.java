package com.moringaschool.medi_finder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.medi_finder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentHospital;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mSearchedlocationReference;

    @BindView(R.id.findHospital)
    Button findHospital;
    @BindView(R.id.savedHospitalButton)
    Button saved;
    @BindView(R.id.locationEditText)
    EditText mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        findHospital.setOnClickListener(this);
        saved.setOnClickListener(this);
        mRecentHospital= mLocation.getText().toString().trim();


    }

    @Override
    public void onClick(View v) {

        if (v==findHospital){
            mRecentHospital= mLocation.getText().toString().trim();
            if(mRecentHospital.equals("")){
                Toast.makeText(getApplicationContext(),"must be filled",Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent(MainActivity.this,HospitalListActivity.class);
                intent.putExtra("hospital",mRecentHospital);
                saveLocationToFirebase(mRecentHospital);
                startActivity(intent);
            }
        }
    }

    private void addToSharedPreferences(String hospital) {
    }

    private void saveLocationToFirebase(String hospital) {

    }
}