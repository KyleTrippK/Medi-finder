package com.moringaschool.medi_finder.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.medi_finder.Constants;
import com.moringaschool.medi_finder.R;
import com.moringaschool.medi_finder.adapters.HospitalListAdapter;
import com.moringaschool.medi_finder.models.Business;
import com.moringaschool.medi_finder.models.YelpBusinessesSearchResponse;
import com.moringaschool.medi_finder.network.MediFinderApi;
import com.moringaschool.medi_finder.network.MediFinderClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalListActivity extends AppCompatActivity {
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HospitalListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private void addToSharedPreferences(String location){
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    public List<Business> locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String hospitalLocation = intent.getStringExtra("hospital");


        Log.d("location","welcome to "+hospitalLocation);
        MediFinderApi client = MediFinderClient.getClient();
        Call<YelpBusinessesSearchResponse> call = client.getHospitals("hospital", hospitalLocation);

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    locations = response.body().getBusinesses();
                    mAdapter = new HospitalListAdapter(HospitalListActivity.this, locations);
//
//                    ItemTouchHelper.Callback callback =  new PetItemTouchHelper(mAdapter);
//                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//                    mAdapter.setItemTouchHelper(itemTouchHelper);
//                    itemTouchHelper.attachToRecyclerView(mRecyclerView);

                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(HospitalListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

//

                    showRestaurants();

//                    mErrorTextView.setText("yeii");
//                    mErrorTextView.setVisibility(View.VISIBLE);
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
//        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}