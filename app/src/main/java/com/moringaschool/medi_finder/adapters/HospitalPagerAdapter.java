package com.moringaschool.medi_finder.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.medi_finder.models.Business;
import com.moringaschool.medi_finder.ui.HospitalDetailsActivity;
import com.moringaschool.medi_finder.ui.HospitalDetailsFragment;

import java.util.List;

public class HospitalPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mHospitals;

    public HospitalPagerAdapter(@NonNull FragmentManager fm,int behavior, List<Business> hospitals){
        super(fm, behavior);
        mHospitals = hospitals;
    }

    @Override
    public Fragment getItem(int position){
        return HospitalDetailsFragment.newInstance(mHospitals.get(position));
    }
    @Override
    public int getCount() {
        return mHospitals.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mHospitals.get(position).getName();
    }
}
