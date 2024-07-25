package com.example.feature_video_listing.presentation.videolist.typetwo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_video_listing.business.domain.VideosListingItem
import com.example.feature_video_listing.presentation.LifeCycleObserver
import com.example.feature_video_listing.presentation.videolist.VideosItemViewType
import com.example.feature_video_listing.presentation.viewmodel.VideoListingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListingTypeTwo(
    navController: NavController,
    @DrawableRes likeLogo: Int? =null,
    @DrawableRes clockLogo: Int?=null,
    @DrawableRes shareLogo: Int?=null,
    displayReaction: Boolean = false,
    corousalTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    corousalPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center,
        background = Black
    ),
    timeTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes corousalBackgroundImage: Int?=null,
    @ColorRes corousalBackgroundColor: Int? =null,
    corousalborderColorStyle : TextStyle =
        TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    corousalReactionTextStyle : TextStyle =
        TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    activeColorIndicator : Color?=null,
    inactiveColorIndicator : Color?=null,
    @DrawableRes backGroundImage1 : Int?=null,
    @ColorRes backgroundColor1 : Int?=null,
    titleStyle1: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreBtnStyle1: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    videoTitleStyle1 : TextStyle = TextStyle(
        color = Color.White, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon1 : Int?=null,
    durationTextStyle1 : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    displayMatchReaction : Boolean = false,
    borderColorStyle1 : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon : Int?=null,
    reactionTextStyle1 : TextStyle = TextStyle(color = Color.White, textAlign = TextAlign.Center),
    @DrawableRes shareLogo1 : Int?=null,
    photoNumberStyle1: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreBtnTextStyle1 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage2 : Int?=null,
    @ColorRes backgroundColor2 : Int?=null,
    titleStyle2: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonStyle2: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    videoTitleStyle2 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon2 : Int?=null,
    durationTextStyle2 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    trainingdisplayMatchReaction : Boolean = false,
    borderColorStyle2 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon2 : Int?=null,
    reactionStyle2 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo2 : Int?=null,
    photosNumberStyle2: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black,
        letterSpacing = 0.3.em
    ),
    moreBtnTextStyle2 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage3 : Int?=null,
    @ColorRes backgroundColor3 : Int?=null,
    titleStyle3: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonStyle3: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    videoTitleStyle3 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon3 : Int?=null,
    durationTextStyle3 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    behindSceneMatchReaction : Boolean = false,
    borderStyle3 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon3 : Int?=null,
    reactionTextStyle3 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo3 : Int?=null,
    photosNumberStyle3: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreBtnTextStyle3 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage4 : Int?=null,
    @ColorRes backgroundColor4 : Int?=null,
    titleStyle4: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonStyle4: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    videoTitleStyle4 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon4 : Int?=null,
    durationTextStyle4 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    behindSceneMatchReaction4 : Boolean = false,
    borderStyle4 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon4 : Int?=null,
    reactionTextStyle4 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo4 : Int?=null,
    photosNumberStyle4 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreBtnTextStyle4 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage5 : Int?=null,
    @ColorRes backgroundColor5 : Int?=null,
    titleStyle5: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonStyle5: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    videoTitleStyle5 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon5 : Int?=null,
    durationTextStyle5 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    behindSceneMatchReaction5 : Boolean = false,
    borderStyle5 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon5 : Int?=null,
    reactionTextStyle5 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo5 : Int?=null,
    photosNumberStyle5 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreBtnTextStyle5 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    )
) {
    val viewModel: VideoListingViewModel = hiltViewModel()
    val videoListing by viewModel.videoListing.observeAsState(initial = emptyList())

    LifeCycleObserver(fetchData = {
        viewModel.fetchVideoListing()
    }) {
        viewModel.cancelApiCoroutine()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(text = "Photos")
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(videoListing){
                when(it.type){
                    VideosItemViewType.CAROUSEL -> {
                        VideoCorousal(likeLogo,
                            clockLogo,
                            shareLogo,
                            displayReaction,
                            corousalTitleStyle,
                            corousalPhotosNumberStyle,
                            timeTitleStyle,
                            corousalBackgroundImage,
                            corousalBackgroundColor,
                            corousalborderColorStyle,
                            corousalReactionTextStyle)
                    }
                    VideosItemViewType.BTS -> {
                        (it as VideosListingItem.BTS).let {
                            VideoListing(
                                navController,
                                it.title,
                                it.items,
                                matchPhotoBackgroundImage =backGroundImage1,
                                matchPhotoBackgroundColor = backgroundColor1,
                                matchPhotoTitleStyle = titleStyle1,
                                matchMoreButton = moreBtnStyle1,
                                matchPhotoListingTitleStyle = videoTitleStyle1,
                                matchClockIcon = durationIcon1,
                                matchTimeTextStyle = durationTextStyle1,
                                borderColorStyle = borderColorStyle1,
                                reactionIcon = reactionIcon,
                                reactionTextStyle = reactionTextStyle1,
                                matchShareLogo = shareLogo1,
                                matchPhotosNumberStyle = photoNumberStyle1,
                                matchMoreButtonTextStyle =moreBtnTextStyle1
                            )
                        }
                    }
                    VideosItemViewType.FILMS -> {
                        (it as VideosListingItem.Films)?.let {
                            VideoListing(
                                navController,
                                it.title,
                                it.items,
                                matchPhotoBackgroundImage =backgroundImage2,
                                matchPhotoBackgroundColor = backgroundColor2,
                                matchPhotoTitleStyle = titleStyle2,
                                matchMoreButton = moreButtonStyle2,
                                matchPhotoListingTitleStyle = videoTitleStyle2,
                                matchClockIcon = durationIcon2,
                                matchTimeTextStyle = durationTextStyle2,
                                borderColorStyle = borderColorStyle2,
                                reactionIcon = reactionIcon2,
                                reactionTextStyle = reactionStyle2,
                                matchShareLogo = shareLogo2,
                                matchPhotosNumberStyle = photosNumberStyle2,
                                matchMoreButtonTextStyle =moreBtnTextStyle2
                            )
                        }
                    }
                    VideosItemViewType.HIGHLIGHTS -> {
                        (it as VideosListingItem.Highlights).let {
                            VideoListing(
                                navController,
                                it.title,
                                it.items,
                                matchPhotoBackgroundImage =backgroundImage3,
                                matchPhotoBackgroundColor = backgroundColor3,
                                matchPhotoTitleStyle = titleStyle3,
                                matchMoreButton = moreButtonStyle3,
                                matchPhotoListingTitleStyle = videoTitleStyle3,
                                matchClockIcon = durationIcon3,
                                matchTimeTextStyle = durationTextStyle3,
                                borderColorStyle = borderStyle3,
                                reactionIcon = reactionIcon3,
                                reactionTextStyle = reactionTextStyle3,
                                matchShareLogo = shareLogo3,
                                matchPhotosNumberStyle = photosNumberStyle3,
                                matchMoreButtonTextStyle =moreBtnTextStyle3
                            )
                        }
                    }
                    VideosItemViewType.SUGGESTIONS -> {
                        (it as VideosListingItem.Suggestions).let {
                            VideoListing(
                                navController,
                                it.title,
                                it.items,
                                matchPhotoBackgroundImage =backgroundImage4,
                                matchPhotoBackgroundColor = backgroundColor4,
                                matchPhotoTitleStyle = titleStyle4,
                                matchMoreButton = moreButtonStyle4,
                                matchPhotoListingTitleStyle = videoTitleStyle4,
                                matchClockIcon = durationIcon4,
                                matchTimeTextStyle = durationTextStyle4,
                                borderColorStyle = borderStyle4,
                                reactionIcon = reactionIcon4,
                                reactionTextStyle = reactionTextStyle4,
                                matchShareLogo = shareLogo4,
                                matchPhotosNumberStyle = photosNumberStyle4,
                                matchMoreButtonTextStyle = moreBtnTextStyle4
                            )
                        }
                    }
                    VideosItemViewType.SHORTS -> {
                        (it as VideosListingItem.Shorts).let {
                            ShortVideoListing(
                                it.title,
                                it.items,
                                matchPhotoBackgroundImage =backgroundImage5,
                                matchPhotoBackgroundColor = backgroundColor5,
                                matchPhotoTitleStyle = titleStyle5,
                                matchMoreButton = moreButtonStyle5,
                                matchPhotoListingTitleStyle = videoTitleStyle5,
                                matchClockIcon = durationIcon5,
                                matchTimeTextStyle = durationTextStyle5,
                                borderColorStyle = borderStyle5,
                                reactionIcon = reactionIcon5,
                                reactionTextStyle = reactionTextStyle5,
                                matchShareLogo = shareLogo5,
                                matchPhotosNumberStyle = photosNumberStyle5,
                                matchMoreButtonTextStyle =moreBtnTextStyle5
                            )
                        }
                    }
                    VideosItemViewType.BANNER -> {
                        (it as VideosListingItem.Banner).let {
                            Banner(data = it)
                        }
                    }
                    VideosItemViewType.UNKNOWN -> {

                    }
                }
            }
        }
    }
}