package com.example.feature_app_home.business.interactor

import com.example.base.helper.NetworkThrowable
import com.example.base.helper.Resource
import com.example.feature_app_home.business.domain.model.home.HomeListingItem
import com.example.feature_app_home.data.mapper.AppHomeModuleEntityMapper
import com.example.feature_app_home.data.remote.AppHomeConfigContract
import com.example.lb_content_listing.business.repository.LBRepository
import com.example.standing.business.interactor.GetStandingData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
) {

    private var _cacheHomeListing: List<HomeListingItem>? = null

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
}