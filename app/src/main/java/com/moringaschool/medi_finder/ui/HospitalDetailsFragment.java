package com.moringaschool.medi_finder.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.medi_finder.R;
import com.moringaschool.medi_finder.models.Business;
import com.moringaschool.medi_finder.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HospitalDetailsFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.hospitalImageView) ImageView mImageLabel;
    @BindView(R.id.hospitalNameTextView) TextView mNameLabel;
    @BindView(R.id.categoryTextView) TextView mCategoriesLabel;
    @BindView(R.id.workingHoursTextView) TextView mWorkingHoursLabel;
    @BindView(R.id.reviewCountTextView) TextView mReviewCountLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.distanceTextView) TextView mDistanceLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveHospitalButton) TextView mSaveRestaurantButton;

    private Business mHospital;

    public HospitalDetailsFragment() {
        // Required empty public constructor
    }

    public static HospitalDetailsFragment newInstance(Business hospital) {
        HospitalDetailsFragment hospitalDetailsFragment = new HospitalDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("hospital", Parcels.wrap(hospital));
        hospitalDetailsFragment.setArguments(args);
        return hospitalDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments()!= null;
        mHospital = Parcels.unwrap(getArguments().getParcelable("hospital"));

//        mSaveRestaurantButton.setOnClickListener(this);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital_details, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mHospital.getImageUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

        for(Category category: mHospital.getCategories()){
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mHospital.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(",", categories));
        mWorkingHoursLabel.setText(Boolean.toString(mHospital.getIsClosed()));
        mReviewCountLabel.setText(Integer.toString(mHospital.getReviewCount()));
        mRatingLabel.setText(Double.toString(mHospital.getRating()));
        mWebsiteLabel.setText(mHospital.getUrl());
        mPhoneLabel.setText(mHospital.getPhone());
        mDistanceLabel.setText(Double.toString(mHospital.getDistance()));
        mAddressLabel.setText(mHospital.getLocation().toString());

        inflater.inflate(R.layout.fragment_hospital_details, container, false);

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(v==mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mHospital.getUrl()));
            startActivity(webIntent);
        }
        if(v==mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(mHospital.getPhone()));
            startActivity(phoneIntent);
        }
        if(v==mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+mHospital.getCoordinates().getLatitude() +","+mHospital.getCoordinates().getLongitude()+"?q=("+mHospital.getName() + ")"));
            startActivity(mapIntent);
        }

    }
}