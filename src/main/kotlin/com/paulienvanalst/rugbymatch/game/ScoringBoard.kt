package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.events.ScoringEvent
import com.paulienvanalst.rugbymatch.events.StartGame
import com.paulienvanalst.rugbymatch.team.NotImplementedException
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
open class ScoringBoard {

    lateinit var hostingTeam: TeamName
    lateinit var visitingTeam: TeamName
    private var scoringHistory : List<Score> = emptyList()

    fun currentScore() = scoringHistory.getGameScore(hostingTeam, visitingTeam)

    fun clear () {
        scoringHistory = emptyList()
    }

    @EventListener
    fun handleGameStart(event: StartGame) {
        hostingTeam = event.hostingTeam
        visitingTeam = event.visitingTeam
    }

    @EventListener
    fun handleScoring(event: ScoringEvent) {
        scoringHistory += Score(event.team, event.type)
    }
}


