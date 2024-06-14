package com.example.feature_fixtures.data.mapper



import com.example.base.helper.EntityMapper
import com.example.feature_fixtures.business.domain.model.fixture.FixtureItems
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.data.model.mastheadscore.MasterHeadResponse
import com.example.feature_fixtures.data.model.mastheadscore.MatchEntity
import com.example.feature_fixtures.data.model.mastheadscore.ParticipantEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MasterHeadEntityMapper @Inject constructor(
    private val matchEntityMapper: MatchEntityMapper,
) : EntityMapper<MasterHeadResponse, FixtureItems> {

    override fun toDomain(entity: MasterHeadResponse): FixtureItems {
        val filteredList = mutableListOf<MatchEntity>()
        val filteredMatch = mutableListOf<IPLMatch>()
        val upcomingMatch = mutableListOf<IPLMatch>()

        val listOfAllMatches = entity.teamId?.let { teamId ->
            entity.matches?.filter { match ->
                match?.participantEntities?.any { it?.id == teamId } == true
            }
        }?:run {
            entity.matches
        }

        val liveMatchList = listOfAllMatches?.filter { it.eventState == "L" }?.sortedBy { it.startDate }
        val upcomingMatchList = listOfAllMatches?.filter { it.eventState == "U" }?.sortedBy { it.startDate }
        val resultMatchList = listOfAllMatches?.filter { it.eventState == "R" }?.sortedBy { it.startDate }?.reversed()

        if (liveMatchList?.size!! > 0){
            filteredList.addAll(liveMatchList)
            if (upcomingMatchList?.size!!>0 && resultMatchList?.size!! <=0){
                upcomingMatchList.forEach {
                    if (filteredList.size<entity.itemCount){
                        filteredList.add(it)
                    }
                }
            }else if (upcomingMatchList?.size!!>0){
                filteredList.add(upcomingMatchList[0])
            }
            if (resultMatchList?.size!!>0 && upcomingMatchList?.size!!<=0){
                resultMatchList.forEach {
                    if (filteredList.size<entity.itemCount){
                        filteredList.add(it)
                    }
                }
            }else if (resultMatchList?.size!!>0){
                filteredList.add(resultMatchList[0])
            }
        }else if (liveMatchList?.size==0 && resultMatchList?.size==0 && upcomingMatchList?.size!!>0){
            upcomingMatchList.forEach {
                if (filteredList.size<entity.itemCount){
                    filteredList.add(it)
                }
            }
        }else if (liveMatchList?.size==0 && resultMatchList?.size!!>0 && upcomingMatchList?.size!!>0){
            if (upcomingMatchList.size>1){
            upcomingMatchList.forEach {
                if (filteredList.size<(entity.itemCount-1)){
                    filteredList.add(it)
                }
            }
            }else{
                filteredList.add(upcomingMatchList[0])
            }
            if (filteredList.size==(entity.itemCount-1)){
                filteredList.add(resultMatchList[0])
            }else{
                resultMatchList.forEach {
                    if (filteredList.size<entity.itemCount){
                        filteredList.add(it)
                    }
                }
            }
        }else if (liveMatchList?.size==0 && upcomingMatchList?.size==0 && resultMatchList?.size!!>0){
            resultMatchList.forEach {
                if (filteredList.size<entity.itemCount){
                    filteredList.add(it)
                }
            }
        }

        filteredList.forEach {
            filteredMatch.add(matchEntityMapper.toDomain(it))
        }
        upcomingMatchList?.forEach {
            upcomingMatch.add(matchEntityMapper.toDomain(it))
        }
        return FixtureItems(
            allListOfMatches = filteredMatch.sortedBy { it.startDate },
            allLiveMatches = emptyList(),
            allUpcomingMatches = upcomingMatch.sortedBy { it.startDate },
            allRecentMatches = emptyList()
        )
    }
}