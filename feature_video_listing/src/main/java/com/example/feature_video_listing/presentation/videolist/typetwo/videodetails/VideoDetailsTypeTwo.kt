package com.example.feature_video_listing.presentation.videolist.typetwo.videodetails

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_video_listing.presentation.LifeCycleObserver
import com.example.feature_video_listing.presentation.videolist.typetwo.ListingOfVideos
import com.example.feature_video_listing.presentation.viewmodel.VideoDetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoDetailsTypeTwo(
    navController: NavController,
    titleAlias : String,
    matchPhotoListingTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    matchMoreButton: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    matchPhotoListingTitle : TextStyle = TextStyle(
        color = Color.White, textAlign = TextAlign.Left
    ),
    @DrawableRes matchClockIcon : Int?=null,
    matchClockTextStyle : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    displayMatchReaction : Boolean = false,
    matchBorderColorStyle : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon : Int?=null,
    reactionTextStyle : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    @DrawableRes matchShareLogo : Int?=null,
    matchPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    matchMoreButtonTextStyle : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    )){

    val viewModel : VideoDetailViewModel = hiltViewModel()
    val videoDetails by viewModel.videoDetails.observeAsState()
    val moreVideos by viewModel.moreVideosList.observeAsState()

    Log.d("VIDEO_DETAILS", "VideoDetailsTypeTwo: "+videoDetails?.title)
    LifeCycleObserver(fetchData = {
        viewModel.fetchVideo(titleAlias)
        viewModel.moreVideoList()
    }) {

    }


    Column {
        AndroidView(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),factory = { context ->
            YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false
                val listener = object : AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoDetails?.videoUrl?:"",0f)
                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {
                        super.onError(youTubePlayer, error)

                    }

                }
                val iFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()

               initialize(listener,iFramePlayerOptions)

                addFullScreenListener(object : YouTubePlayerFullScreenListener{
                    override fun onYouTubePlayerEnterFullScreen() {

                    }

                    override fun onYouTubePlayerExitFullScreen() {

                    }

                })
            }
        })

        LazyRow (modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)){
            items(moreVideos.orEmpty()){
                ListingOfVideos(
                    assetItem = it,
                    itemCounts = moreVideos?.size ?: 0,
                    matchPhotoListingTitleStyle = matchPhotoListingTitleStyle,
                    matchTimeTextStyle = matchClockTextStyle,
                    borderStyle = matchBorderColorStyle,
                    reactionTextStyle = reactionTextStyle,
                    numberStyle =matchPhotosNumberStyle,
                    aspectRatio = 1f,
                    itemWidth = 200.dp,
                    onItemClick = { onItemSelected->
                        navController.navigate(
                            route = "VideoDetails/${onItemSelected.titleAlias}"
                        )
                    }
                )
            }
        }
    }

}



