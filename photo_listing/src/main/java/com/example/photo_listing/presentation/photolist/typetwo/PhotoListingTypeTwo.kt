package com.example.photo_listing.presentation.photolist.typetwo

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.presentation.LifeCycleObserver
import com.example.photo_listing.presentation.PhotoItemViewType
import com.example.photo_listing.presentation.theme.Black
import com.example.photo_listing.presentation.viewmodel.PhotoListingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListingTypeTwo(
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
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    timeTitleStyle: TextStyle = TextStyle(
        color = Color.White,
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
    @DrawableRes backgroundImage1 : Int?=null,
    @ColorRes backgroundColor1 : Int?=null,
    titleStyle1: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonStyle: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    photoTitleStyle1 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon1 : Int?=null,
    durationTextStyle1 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    displayMatchReaction1 : Boolean = false,
    borderColorStyle1 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon1 : Int?=null,
    reactionTextStyle1 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo1 : Int?=null,
    photoNumberStyle1: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreButtonTextStyle1 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage2 : Int?=null,
    @ColorRes backgroundColor2 : Int?=null,
    titleStyle2: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreButtonColor2: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    photoTitleStyle2 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon2 : Int?=null,
    durationTextStyle2 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    trainingdisplayMatchReaction : Boolean = false,
    borderStyle2 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon2 : Int?=null,
    reactionTextStyle2 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo2 : Int?=null,
    photosNumberStyle2: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black,
        letterSpacing = 0.3.em
    ),
    moreButtonTextStyle2 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    @DrawableRes backgroundImage3 : Int?=null,
    @ColorRes backgrounfColor3 : Int?=null,
    titleStyle3: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Left
    ),
    moreBtnStyle: ButtonColors =
        ButtonDefaults.buttonColors(containerColor = Color.Black),
    photoTitleStyle3 : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    @DrawableRes durationIcon3 : Int?=null,
    durationTextStyle3 : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    behindSceneMatchReaction : Boolean = false,
    borderStyle3 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes reactionIcon3 : Int?=null,
    reactionTextStyle3 : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    @DrawableRes shareLogo3 : Int?=null,
    photoNumberStyle3: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),
    moreBtnTextStyle3 : TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center
    )
){
    val viewModel : PhotoListingViewModel = hiltViewModel()
    val photoListing by viewModel.photoListing.observeAsState(initial = emptyList())

    LifeCycleObserver(fetchData = {
        viewModel.fetchPhotoListing()
    }) {
        viewModel.cancelApiCoroutine()
    }

    Column (modifier = Modifier.fillMaxSize()){
        TopAppBar(title = {
            Text(text = "Photos")
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ))


        (photoListing as? PhotoListingItem.Training)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    photoListing,
                    key = { it.type.id }){
                    when(it.type){
                        PhotoItemViewType.CAROUSEL->{
                            (it as? PhotoListingItem.Carousel)?.let {
                                PhotosCorousal(data = it,
                                    likeLogo,clockLogo,shareLogo,displayReaction,
                                    corousalTitleStyle,
                                    corousalPhotosNumberStyle,
                                    timeTitleStyle,
                                    corousalBackgroundImage,
                                    corousalBackgroundColor,
                                    corousalborderColorStyle,
                                    corousalReactionTextStyle,
                                    activeColorIndicator,
                                    inactiveColorIndicator)
                            }
                        }
                        PhotoItemViewType.TRAINING->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it,backgroundImage2,
                                    backgroundColor2,
                                    titleStyle2,
                                    moreButtonColor2,
                                    photoTitleStyle2,
                                    durationIcon2,
                                    matchTimeTextStyle = durationTextStyle2,
                                    trainingdisplayMatchReaction,
                                    borderStyle2,
                                    reactionIcon2,
                                    reactionTextStyle2,
                                    shareLogo2,
                                    photosNumberStyle2,
                                    moreButtonTextStyle2,
                                    navController
                                )
                            }

                        }
                        PhotoItemViewType.MATCHPHOTOS->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it,backgroundImage1,
                                    backgroundColor1,
                                    titleStyle1,
                                    moreButtonStyle,
                                    photoTitleStyle1,
                                    durationIcon1,
                                    matchTimeTextStyle = durationTextStyle1,
                                    displayMatchReaction1,
                                    borderColorStyle1,
                                    reactionIcon1,
                                    reactionTextStyle1,
                                    shareLogo1,
                                    photoNumberStyle1,
                                    moreButtonTextStyle1,navController
                                )
                            }
                        }
                        PhotoItemViewType.BEHINDSCENES->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it,
                                    backgroundImage3,
                                    backgrounfColor3,
                                    titleStyle3,
                                    moreBtnStyle,
                                    photoTitleStyle3,
                                    durationIcon3,
                                    matchTimeTextStyle = durationTextStyle3,
                                    behindSceneMatchReaction,
                                    borderStyle3,
                                    reactionIcon3,
                                    reactionTextStyle3,
                                    shareLogo3,
                                    photoNumberStyle3,
                                    moreBtnTextStyle3,navController
                                )
                            }
                        }
                        PhotoItemViewType.BANNER->{
                            (it as? PhotoListingItem.Banner)?.let{
                                Banner(data = it)
                            }
                        }
                        PhotoItemViewType.UNKNOWN->{

                        }
                    }
            }

        }
    }
}