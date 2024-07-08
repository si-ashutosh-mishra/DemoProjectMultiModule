package com.example.photo_listing.presentation.photolist.typeone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.presentation.LifeCycleObserver
import com.example.photo_listing.presentation.theme.Black
import com.example.photo_listing.presentation.viewmodel.PhotoListingViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayPhotoListingGridLayout(
    navController: NavController,
    trainingPhotoListingTitleStyle : TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Left
    ),
    trainingClockTextStyle : TextStyle = TextStyle(color = Color.DarkGray, textAlign = TextAlign.Center),
    trainingborderColorStyle : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    trainingreactionTextStyle : TextStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
    behindScenematchPhotosNumberStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        background = Black
    ),

    ) {

    val viewModel : PhotoListingViewModel = hiltViewModel()
    val photoListing by viewModel.photoListing.observeAsState(initial = emptyList())

    LifeCycleObserver(fetchData = {
        viewModel.fetchPhotoListing()
    }) {
        viewModel.cancelApiCoroutine()
    }

    Column {
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(photoListing) {

                    photodata ->
                (photodata as? PhotoListingItem.MatchPhotos)?.let {

                    for ( items in photodata.items
                    )
                    {
                        ListingItemGridLayout(
                            assetItem = items,
                            itemCounts = photodata.items.size ,
                            matchPhotoListingTitleStyle = trainingPhotoListingTitleStyle,
                            matchTimeTextStyle = trainingClockTextStyle,
                            borderColorStyle = trainingborderColorStyle,
                            reactionTextStyle = trainingreactionTextStyle,
                            matchPhotosNumberStyle = behindScenematchPhotosNumberStyle
                        )

                    }

                }



            }
        }
    }



}