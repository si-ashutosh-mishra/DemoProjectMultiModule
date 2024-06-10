package com.example.feature_fixtures.data.mapper

import com.example.base.helper.EntityMapper
import com.example.feature_fixtures.business.domain.model.fixture.FixtureItems
import com.example.feature_fixtures.data.model.mastheadscore.MasterHeadResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MasterHeadEntityListingMapper @Inject constructor(
    private val matchEntityMapper: MatchEntityMapper,
) : EntityMapper<MasterHeadResponse, FixtureItems> {

    override fun toDomain(entity: MasterHeadResponse): FixtureItems {

        val listOfAllMatches = entity.teamId?.let { clubId ->
            entity.matches?.filter { match ->
                match?.participantEntities?.any { it?.id == clubId } == true
            }
        }?:run {
            entity.matches
        }
        val allLiveMatch = listOfAllMatches?.sortedBy { it.startDate }?.map { matchEntityMapper.toDomain(it) }
        val liveMatchList =
            listOfAllMatches?.filter { it.eventState == "L" }?.sortedBy { it.startDate }?.map { matchEntityMapper.toDomain(it) }
        val upcomingMatchList = listOfAllMatches?.filter { it.eventState == "U" }?.sortedBy { it.startDate }?.map { matchEntityMapper.toDomain(it) }
        val resultMatchList = listOfAllMatches?.filter { it.eventState == "R" }?.sortedBy { it.startDate }?.map { matchEntityMapper.toDomain(it) }


        return FixtureItems(
            allListOfMatches = allLiveMatch,
            allLiveMatches = liveMatchList,
            allUpcomingMatches = upcomingMatchList,
            allRecentMatches = resultMatchList
        )
    }


}