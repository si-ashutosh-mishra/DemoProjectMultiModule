package com.example.photo_listing.presentation.photolist.typetwo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.photo_listing.business.model.PhotoListingItem
import com.example.photo_listing.presentation.LifeCycleObserver
import com.example.photo_listing.presentation.PhotoItemViewType
import com.example.photo_listing.presentation.viewmodel.PhotoListingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListingTypeTwo(
    navController: NavController
){
    val viewModel : PhotoListingViewModel = hiltViewModel()
    val photoListing by viewModel.photoListing.observeAsState(initial = emptyList())

    LifeCycleObserver(fetchData = {
        viewModel.fetchPhotoListing()
    }) {
        viewModel.cancelApiCoroutine()
    }
    Log.d("Photo_Listing", "PhotoListingTypeTwo: "+photoListing)
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

        LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(photoListing){
                    when(it.type){
                        PhotoItemViewType.CAROUSEL->{

                        }
                        PhotoItemViewType.TRAINING->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it)
                            }

                        }
                        PhotoItemViewType.MATCHPHOTOS->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it)
                            }
                        }
                        PhotoItemViewType.BEHINDSCENES->{
                            (it as? PhotoListingItem.PhotosArticle)?.let {
                                PhotoListing(it)
                            }
                        }
                        PhotoItemViewType.BANNER->{

                        }
                        PhotoItemViewType.UNKNOWN->{

                        }
                    }
            }

        }
    }
}