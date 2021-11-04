package com.moringaschool.medi_finder.network;

import com.moringaschool.medi_finder.models.YelpBusinessesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MediFinderApi {
    @GET("businesses/search")
    Call<YelpBusinessesSearchResponse> getHospitals(
            @Query("term") String term,
            @Query("location") String location
    );
}