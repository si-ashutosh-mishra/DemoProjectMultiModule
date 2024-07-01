package com.example.feature_app_home.presentation.apphome

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_app_home.business.domain.model.home.HomeItemViewType
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.presentation.apphome.viewmodel.AppHomeViewModel
import com.example.standing.presentation.standinghome.StandingHome

@Composable
fun AppHome(
    onStandingViewMoreClick: () -> Unit,
) {

    val viewModel: AppHomeViewModel = hiltViewModel()

    val currentTeamID = viewModel.appHomeConfigContract.getCurrentTeamID()


    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHomeListing(true)
    }

    val homeItemList = viewModel.homeListingItems.observeAsState().value.orEmpty()

    LazyColumn {
        itemsIndexed(
            items = homeItemList,
            key = { _: Int, item: HomeListingItem ->
                item.type.id
            },
        ) { _, home ->
            when (home.type.id) {
                HomeItemViewType.HOME_STANDING.id -> {
                    (home as? HomeListingItem.HomeStandingData)?.let { data ->
                        StandingHome(
                            currentTeamID = currentTeamID, onViewMoreClick = {
                                onStandingViewMoreClick.invoke()
                            }, pointsTableList = data.items.orEmpty()
                        )
                    }

                }
            }
        }
    }
}