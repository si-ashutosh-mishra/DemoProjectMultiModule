package com.example.feature_squad.data.service

import com.example.feature_squad.data.model.SquadList
import retrofit2.http.GET
import retrofit2.http.Url

interface SquadService {

    @GET
    suspend fun getSquadListing(@Url url: String): SquadList?

}