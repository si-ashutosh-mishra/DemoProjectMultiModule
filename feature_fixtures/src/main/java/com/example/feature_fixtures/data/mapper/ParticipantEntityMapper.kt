package com.example.feature_fixtures.data.mapper


import com.example.base.helper.BaseInfo
import com.example.base.helper.EntityMapper
import com.example.feature_fixtures.business.domain.model.masthead.Participant
import com.example.feature_fixtures.data.model.mastheadscore.ParticipantEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParticipantEntityMapper @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<ParticipantEntity, Participant> {


    override fun toDomain(entity: ParticipantEntity): Participant {
        return Participant(
            id = entity.id,
            name = entity.name,
            shortName = entity.shortName,
            value = entity.value,
            now = entity.now,
            firstUp = entity.firstUp,
            teamImageUrl = entity.id?.let { "" },
            highlight = entity.highlight,
            teamBackground = ""
        )
    }

}