package com.example.feature_app_home.business.interactor

import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.data.mapper.AppHomeModuleEntityMapper
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.business.interceptor.GetListOfMatches
import com.example.feature_fixtures.presentation.fixture.utils.FixturesType
import com.example.lb_content_listing.business.repository.LBRepository
import com.example.standing.business.interactor.GetStandingData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetAppHomeListing @Inject constructor(
    private val lbRepository: LBRepository,
    private val appHomeModuleEntityMapper: AppHomeModuleEntityMapper,
    private val getStandingsData: GetStandingData,
    private val appHomeConfigContract: AppHomeConfigContract,
    private val getListOfMatches: GetListOfMatches,
) {

    private var _cacheHomeListing: List<HomeListingItem>? = null

    private var _cachedScoreCard: List<IPLMatch>? = null

    operator fun invoke(
        url: String,
        forceRefresh: Boolean,
    ): Flow<Resource<List<HomeListingItem>?>> {
        return flow {

            if (forceRefresh) {

                val result = lbRepository.getLBListing(url).first { it !is Resource.Loading }.data
                _cacheHomeListing = appHomeModuleEntityMapper.toDomain(result)
                    .filter { it !is HomeListingItem.Unknown }

                emit(Resource.Success(data = _cacheHomeListing?.filter { homeListingItem -> homeListingItem.dataAvailable }))

                val externalDataItems = _cacheHomeListing?.filter { !it.dataAvailable }.orEmpty()

                coroutineScope {
                    val deferredList = mutableListOf<Deferred<HomeListingItem?>>()
                    externalDataItems.forEach { homeListingItem ->
                        deferredList.add(async {
                            loadExternalData(homeListingItem).first { it !is Resource.Loading }.data
                        })
                    }

                    deferredList.forEach {
                        val item = it.await() ?: return@forEach
                        updateListItem(item = item)
                        emit(Resource.Success(data = _cacheHomeListing?.filter { homeListingItem -> homeListingItem.dataAvailable }))
                    }


                    val scoreCardIndex =
                        _cacheHomeListing?.indexOfFirst { it is HomeListingItem.HomeFixtures }

                    _cacheHomeListing =
                        removeOrSetMasterHead(scoreCardIndex, _cacheHomeListing?.toMutableList())

                    if (scoreCardIndex != null && scoreCardIndex != -1) {

                        var isLiveMatch = false

                        do {
                            val allListOfMatches = getListOfMatches(
                                FixturesType.HOME.id,
                                url = appHomeConfigContract.getFixturesUrl(),
                                teamId = appHomeConfigContract.getCurrentTeamID().toString(),
                                itemCount = 5
                            ).first { it !is Resource.Loading }.data?.allListOfMatches.orEmpty()

                            if (!allListOfMatches.isNullOrEmpty()) {

                                _cachedScoreCard = allListOfMatches

                                isLiveMatch =
                                    allListOfMatches.find { it.eventState == EventState.LIVE } != null

                                _cacheHomeListing =
                                    addOrSetMasterHead(scoreCardIndex, allListOfMatches)

                                emit(Resource.Success(_cacheHomeListing ?: listOf()))

                                delay(appHomeConfigContract.getFixturesPollingInterval())
                            }
                        } while (isLiveMatch)
                    }
                }
            }

        }
    }

    private fun updateListItem(item: HomeListingItem) {
        _cacheHomeListing = _cacheHomeListing?.map {
            if (it.type == item.type) {
                item
            } else it
        }
    }

    private suspend fun loadExternalData(homeListingItem: HomeListingItem): Flow<Resource<HomeListingItem>> {
        return when (homeListingItem) {
            is HomeListingItem.HomeStandingData -> {
                getStandingsData(
                    url = appHomeConfigContract.getStandingUrl(),
                    teamCount = appHomeConfigContract.getHomeTeamCount(),
                    currentTeamId = appHomeConfigContract.getCurrentTeamID(),
                    isSwapRequired = appHomeConfigContract.isSwap(),
                    swapPosition = appHomeConfigContract.swapPos()
                ).map {
                    when (it) {
                        is Resource.Loading -> {
                            Resource.Loading()
                        }

                        is Resource.Error -> {
                            Resource.Error(throwable = it.throwable)
                        }

                        else -> if (it.data.isNullOrEmpty()) {
                            Resource.Error(
                                throwable = NetworkThrowable(
                                    null, ""
                                )
                            )
                        } else {
                            Resource.Success(
                                data = homeListingItem.copy(
                                    items = it.data.orEmpty(), dataAvailable = true
                                )
                            )
                        }
                    }
                }
            }

            else -> {
                flow { emit(Resource.Success(homeListingItem)) }
            }
        }
    }

    private fun removeOrSetMasterHead(
        masterHeadIndex: Int?,
        updatedData: MutableList<HomeListingItem>?,
    ): List<HomeListingItem>? {
        return updatedData?.toMutableList().apply {
            if (masterHeadIndex != null && masterHeadIndex != -1) {
                if (_cachedScoreCard != null) {
                    (this?.getOrNull(masterHeadIndex) as? HomeListingItem.HomeFixtures)?.let { it ->
                        this.set(masterHeadIndex, it.copy(matches = it.matches))
                    }
                } else {
                    this?.removeAt(masterHeadIndex)
                }
            }
        }
    }

    private fun addOrSetMasterHead(
        masterHeadIndex: Int,
        masterHead: List<IPLMatch>,
    ): List<HomeListingItem>? {
        return _cacheHomeListing?.toMutableList().apply {
            if (this?.find { it is HomeListingItem.HomeFixtures } == null) {
                //if master head not present in list then adding it
                this?.add(
                    masterHeadIndex, HomeListingItem.HomeFixtures(
                        matches = masterHead, dataAvailable = true
                    )
                )
            } else {
                val index = _cacheHomeListing?.indexOfFirst { it is HomeListingItem.HomeFixtures }
                //if master head is present then just update it
                this[index!!] = HomeListingItem.HomeFixtures(
                    matches = masterHead, dataAvailable = true
                )
            }

        }?.toList()
    }

}