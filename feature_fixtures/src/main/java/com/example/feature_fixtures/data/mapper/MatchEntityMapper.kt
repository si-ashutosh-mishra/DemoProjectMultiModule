package com.example.feature_fixtures.data.mapper




import com.example.base.helper.EntityMapper
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import com.example.feature_fixtures.business.domain.model.masthead.toMatchEventState
import com.example.feature_fixtures.data.model.mastheadscore.MatchEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchEntityMapper @Inject constructor(
    private val participantEntityMapper: ParticipantEntityMapper,
) : EntityMapper<MatchEntity, IPLMatch> {

    override fun toDomain(entity: MatchEntity): IPLMatch {

        val eventState = entity.eventState.toMatchEventState()
        val eventStatusText: String? = when (eventState) {
            EventState.RESULT -> entity.eventSubStatus
            EventState.UPCOMING -> {
                entity.venueName
            }
            EventState.LIVE -> {
                entity.eventSubStatus
            }
        }

        return IPLMatch(
            endDate = entity.endDate,
            eventDurationLeft = entity.eventDurationLeft,
            eventGroup = entity.eventGroup,
            eventIsDaynight = entity.eventIsDaynight,
            eventIslinkable = entity.eventIslinkable,
            eventLivecoverage = entity.eventLivecoverage,
            eventName = entity.eventName,
            eventStage = entity.eventStage,
            eventState = eventState,
            eventStateText = entity.eventState?:"",
            eventStatus = entity.eventStatus,
            eventStatusId = entity.eventStatusId,
            eventSubStatus = entity.eventSubStatus,
            gameId = entity.gameId,
            leagueCode = entity.leagueCode,
            participants = entity.participantEntities?.map { participantEntityMapper.toDomain(it) },
            resultCode = entity.resultCode,
            resultSubCode = entity.resultSubCode,
            seriesId = entity.seriesId,
            seriesName = entity.seriesName,
            sport = entity.sport,
            startDate = entity.startDate,
            tourId = entity.tourId,
            tourName = entity.tourName,
            venueGmtOffset = entity.venueGmtOffset,
            venueId = entity.venueId,
            venueName = entity.venueName,
            winningMargin = entity.winningMargin,
            eventStatusText = eventStatusText,
            matchCenterWebUrl = "p",
        )
    }
}
