package com.example.feature_app_home.presentation.apphome

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_app_home.business.domain.model.home.HomeItemViewType
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.presentation.apphome.viewmodel.AppHomeViewModel
import com.example.feature_fixtures.presentation.fixture.LifeCycleObserver
import com.example.feature_fixtures.presentation.fixture.typeone.FixturesHorizontalScrollTypeOne
import com.example.feature_squad.presentation.squadhome.typeone.SquadHomeTypeOne
import com.example.feature_squad.presentation.squadhome.typetwo.SquadHomeTypeTwo
import com.example.standing.presentation.standinghome.StandingHome

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppHome(
    onStandingViewMoreClick: () -> Unit,
    onFixtureViewMoreClick: () -> Unit,
    onFixtureItemClick: () -> Unit,
) {

    val viewModel: AppHomeViewModel = hiltViewModel()

    val currentTeamID = viewModel.appHomeConfigContract.getCurrentTeamID()

    LifeCycleObserver(fetchData = {
        viewModel.fetchHomeListing(true)
    }) {
        viewModel.cancelApiCoroutine()
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

                HomeItemViewType.HOME_FIXTURES.id -> {
                    (home as? HomeListingItem.HomeFixtures)?.let { data ->
                        FixturesHorizontalScrollTypeOne(list = data.matches,
                            onViewMoreClick = { onFixtureViewMoreClick.invoke() },
                            onClickItem = { onFixtureItemClick.invoke() })
                    }

                }

                HomeItemViewType.HOME_SQUAD.id -> {
                    (home as? HomeListingItem.HomeSquad)?.let { data ->
                        data.items.orEmpty().let {
                            SquadHomeTypeOne(
                                players = it,
                                onPlayerItemClick = { player ->
                                    Log.d("onClick PlayerDetail", player.toString())
                                },
                                onMoreClick = {
                                    Log.d("onClick More", "======================")
                                }
                            )
                            /*SquadHomeTypeTwo(
                                players = it,
                                onPlayerItemClick = { player ->
                                    Log.d("onClick PlayerDetail", player.toString())
                                },
                                onMoreClick = {
                                    Log.d("onClick More", "======================")
                                }
                            )*/
                        }
                    }

                }
            }
        }
    }
}