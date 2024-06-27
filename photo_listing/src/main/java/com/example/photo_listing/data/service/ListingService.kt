package com.example.photo_listing.data.service

import com.example.photo_listing.business.domain.model.photolisting.Content
import com.example.photo_listing.business.domain.model.reaction.ReactionCount
import com.example.photo_listing.business.domain.model.reaction.ReactionCountRequest
import com.example.photo_listing.business.domain.model.userreaction.UserReaction
import com.example.photo_listing.data.model.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface ListingService {

    @GET
    suspend fun getPhotosPageListing(@Url url: String): BaseResponse<Content>

    @POST
    suspend fun getReactionCount(
        @Header("x-game-name") gameName: String,
        @Header("user-guid") userGuid: String,
        @Url url: String,
        @Body reactionCountRequest: ReactionCountRequest
    ): ReactionCount

    @POST
    suspend fun getUserReactionCount(
        @Header("x-game-name") gameName: String,
        @Header("user-guid") userGuid: String,
        @Url url: String,
        @Body reactionCountRequest: ReactionCountRequest
    ): UserReaction
}