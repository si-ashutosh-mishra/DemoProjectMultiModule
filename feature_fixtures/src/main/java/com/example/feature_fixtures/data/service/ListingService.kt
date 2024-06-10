package com.example.feature_fixtures.data.service

import com.example.feature_fixtures.data.model.mastheadscore.MasterHeadResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface FixturesService {

    @GET
    suspend fun getMatchMasterHead(@Url url: String): MasterHeadResponse

}