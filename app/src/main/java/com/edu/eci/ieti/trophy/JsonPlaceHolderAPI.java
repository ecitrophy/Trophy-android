package com.edu.eci.ieti.trophy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("apimatch/matcheslist")
    Call<List<MatchList>> getMatches();

}
