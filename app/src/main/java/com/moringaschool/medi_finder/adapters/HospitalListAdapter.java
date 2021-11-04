package com.moringaschool.medi_finder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.medi_finder.R;
import com.moringaschool.medi_finder.models.Business;
import com.moringaschool.medi_finder.ui.HospitalDetailsActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.HospitalViewHolder > {
    public List<Business> mHospitals;
    private Context mContext;

    public HospitalListAdapter(Context context, List<Business> hospitals) {
        mContext = context;
        mHospitals = hospitals;
    }
    @Override
    public HospitalListAdapter.HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hospital_list_adapter, parent, false);
        HospitalViewHolder viewHolder = new HospitalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HospitalListAdapter.HospitalViewHolder holder, int position) {
        holder.bindHospitals(mHospitals.get(position));
    }

    @Override
    public int getItemCount() {
        return mHospitals.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.hospitalImageView) ImageView mHospitalImageView;
        @BindView(R.id.hospitalNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public HospitalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindHospitals(Business hospital) {
            mNameTextView.setText(hospital.getName());
            mCategoryTextView.setText(hospital.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + hospital.getRating() + "/5");

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HospitalDetailsActivity.class);
            intent.putExtra("position", itemPosition);

            intent.putExtra("hospitals", Parcels.wrap(mHospitals));
            mContext.startActivity(intent);
        }
    }
}