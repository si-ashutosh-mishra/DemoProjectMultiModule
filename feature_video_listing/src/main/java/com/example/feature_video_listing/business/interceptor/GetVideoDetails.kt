package com.example.feature_video_listing.business.interceptor

import com.example.base.helper.Resource
import com.example.feature_video_listing.business.model.VideoDetails
import com.example.feature_video_listing.business.repository.VideoDetailRepository
import com.example.lb_content_listing.business.domain.model.AssetItem
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetVideoDetails @Inject constructor(
    val videoDetailRepository: VideoDetailRepository
) {

    operator fun invoke(titleAlias : String) : Flow<Resource<VideoDetails?>>{
        val url = "https://stg-kc.sportz.io/apiv3/video/{title_alias}?is_app=1".replace("{title_alias}",titleAlias)
        return videoDetailRepository.getVideoDetailRepository(url)
    }

    fun getMoreVideos() : Flow<Resource<List<AssetItem>?>>{
        return videoDetailRepository.getMoreVideos("https://stg-kc.sportz.io/apiv3/listing?entities=3,4,174&otherent=&exclent=&pgnum=1&inum=10&pgsize=10")
    }

}