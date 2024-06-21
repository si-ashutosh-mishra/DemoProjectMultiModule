package com.example.satndings.data.service

import com.example.satndings.data.model.standings.StandingsData
import retrofit2.http.GET
import retrofit2.http.Url

interface StandingService {
    @GET
    suspend fun getStandingsData(@Url url: String): StandingsData
}