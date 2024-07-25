package com.example.photo_listing.presentation.photolist.typetwo.photodetails

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lb_content_listing.business.domain.model.AssetItem
import com.example.photo_listing.presentation.LifeCycleObserver
import com.example.photo_listing.presentation.photolist.typetwo.ListingOfPhotos
import com.example.photo_listing.presentation.theme.Black
import com.example.photo_listing.presentation.viewmodel.PhotoDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailsTypeTwo(
    navController: NavController,
    titleAlias: String?,
    @DrawableRes likeLogo: Int? = null,
    @DrawableRes clockLogo: Int? = null,
    @DrawableRes shareLogo: Int? = null,
    displayReaction: Boolean = false,
    corousalTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    corousalPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    timeTitleStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes corousalBackgroundImage: Int? = null,
    @ColorRes corousalBackgroundColor: Int? = null,
    corousalborderColorStyle: TextStyle =
        TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    corousalReactionTextStyle: TextStyle =
        TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    activeColorIndicator: Color? = null,
    inactiveColorIndicator: Color? = null,
    @DrawableRes matchPhotoBackgroundImage: Int? = null,
    @ColorRes matchPhotoBackgroundColor: Int? = null,
    matchPhotoTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    matchMoreButton: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    matchPhotoListingTitle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes matchClockIcon: Int? = null,
    matchClockTextStyle: TextStyle = TextStyle(
        color = Color.DarkGray,
        textAlign = TextAlign.Center
    ),
    displayMatchReaction: Boolean = false,
    matchBorderColorStyle: TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon: Int? = null,
    reactionTextStyle: TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes matchShareLogo: Int? = null,
    matchPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    matchMoreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes trainingPhotoBackgroundImage: Int? = null,
    @ColorRes trainingPhotoBackgroundColor: Int? = null,
    trainingPhotoTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    trainingMoreButton: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    trainingPhotoListingTitleStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes trainingClockIcon: Int? = null,
    trainingClockTextStyle: TextStyle = TextStyle(
        color = Color.DarkGray,
        textAlign = TextAlign.Center
    ),
    trainingdisplayMatchReaction: Boolean = false,
    trainingborderColorStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes trainingreactionIcon: Int? = null,
    trainingreactionTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes trainingShareLogo: Int? = null,
    trainingPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black,
        letterSpacing = 0.3.em
    ),
    trainingMoreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes behindScenePhotoBackgroundImage: Int? = null,
    @ColorRes behindScenePhotoBackgroundColor: Int? = null,
    behindScenePhotoTitleStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    behindSceneMoreButton: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    behindScenePhotoListingTitleStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes behindSceneClockIcon: Int? = null,
    behindSceneTextStyle: TextStyle = TextStyle(
        color = Color.DarkGray,
        textAlign = TextAlign.Center
    ),
    behindSceneMatchReaction: Boolean = false,
    behindSceneBorderStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes behindScenereactionIcon: Int? = null,
    behindScenereactionTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes behindScenematchShareLogo: Int? = null,
    behindScenematchPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    behindSceneMoreButtonTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    )
) {
    val viewModel: PhotoDetailViewModel = hiltViewModel()
    val photoDetails by viewModel.photoDetails.observeAsState()
    val morePhotoList by viewModel.morePhotoList.observeAsState()
    val moreVideoList by viewModel.moreVideosList.observeAsState()
    val moreNewsList by viewModel.moreNewsList.observeAsState()

    LifeCycleObserver(fetchData = {
        viewModel.fetchPhotoDetails(titleAlias ?: "")
        viewModel.moreVideoList()
        viewModel.morePhotoList()
        viewModel.moreNewsList()
    }) {
        viewModel.cancelApiCoroutine()
    }

    val photoState : MutableState<List<AssetItem>> = remember {
        mutableStateOf(morePhotoList.orEmpty())
    }

    val videoState : MutableState<List<AssetItem>> = remember {
        mutableStateOf(moreVideoList.orEmpty())
    }

    val newsState : MutableState<List<AssetItem>> = remember {
        mutableStateOf(moreNewsList.orEmpty())
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


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
        ) {
            item {
                PhotosDetailsCorousal(
                    data = photoDetails?.albumItems,
                    corousalTitleStyle = corousalTitleStyle,
                    corousalPhotosNumberStyle = corousalPhotosNumberStyle,
                    timeTitleStyle = timeTitleStyle,
                    corousalborderColorStyle = corousalborderColorStyle,
                    corousalReactionTextStyle = corousalReactionTextStyle
                )
            }
            item {
                PhotoDetailsListing(
                    data = morePhotoList.orEmpty(),
                    title = "Photos",
                    matchPhotoTitleStyle = matchPhotoTitleStyle,
                    matchMoreButton = matchMoreButton,
                    matchPhotoListingTitleStyle = matchPhotoTitleStyle,
                    matchClockIcon = matchClockIcon,
                    matchTimeTextStyle = timeTitleStyle,
                    borderColorStyle = matchBorderColorStyle,
                    reactionTextStyle = reactionTextStyle,
                    matchPhotosNumberStyle = matchPhotosNumberStyle,
                    matchMoreButtonTextStyle = matchMoreButtonTextStyle,
                    navController = navController,
                    photoType = true
                )
            }

            item {
                PhotoDetailsListing(
                    data = moreVideoList.orEmpty(),
                    title = "Videos",
                    matchPhotoTitleStyle = matchPhotoTitleStyle,
                    matchMoreButton = matchMoreButton,
                    matchPhotoListingTitleStyle = matchPhotoTitleStyle,
                    matchClockIcon = matchClockIcon,
                    matchTimeTextStyle = timeTitleStyle,
                    borderColorStyle = matchBorderColorStyle,
                    reactionTextStyle = reactionTextStyle,
                    matchPhotosNumberStyle = matchPhotosNumberStyle,
                    matchMoreButtonTextStyle = matchMoreButtonTextStyle,
                    navController = navController
                )
            }
            item {
                PhotoDetailsListing(
                    data = moreNewsList.orEmpty(),
                    title = "News",
                    matchPhotoTitleStyle = matchPhotoTitleStyle,
                    matchMoreButton = matchMoreButton,
                    matchPhotoListingTitleStyle = matchPhotoTitleStyle,
                    matchClockIcon = matchClockIcon,
                    matchTimeTextStyle = timeTitleStyle,
                    borderColorStyle = matchBorderColorStyle,
                    reactionTextStyle = reactionTextStyle,
                    matchPhotosNumberStyle = matchPhotosNumberStyle,
                    matchMoreButtonTextStyle = matchMoreButtonTextStyle,
                    navController = navController,
                    photoType = true
                )
            }
        }
    }
}
