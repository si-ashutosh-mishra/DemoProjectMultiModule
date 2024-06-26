package com.example.feature_fixtures.data.mapper


import com.example.base.helper.EntityMapper
import com.example.feature_fixtures.business.domain.model.masthead.Participant
import com.example.feature_fixtures.data.model.mastheadscore.ParticipantEntity
import com.example.feature_fixtures.data.remote.FixtureConfigContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParticipantEntityMapper @Inject constructor(
    private val fixtureConfigContract: FixtureConfigContract
) : EntityMapper<ParticipantEntity, Participant> {


    override fun toDomain(entity: ParticipantEntity): Participant {
        return Participant(
            id = entity.id,
            name = entity.name,
            shortName = entity.shortName,
            value = entity.value,
            now = entity.now,
            firstUp = entity.firstUp,
            teamImageUrl = entity.id?.let { fixtureConfigContract.getTeamLogo(entity.id)  },
            highlight = entity.highlight,
            teamBackground = ""
        )
    }

}