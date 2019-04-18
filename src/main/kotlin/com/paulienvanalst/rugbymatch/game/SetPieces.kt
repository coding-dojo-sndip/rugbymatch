package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.team.NotImplementedException
import com.paulienvanalst.rugbymatch.team.Team


interface SetPiece {
    val attackingTeam: Team
    val defendingTeam: Team

    fun isValid() : Boolean
}

data class Scrum(override val attackingTeam: Team, override val defendingTeam: Team) : SetPiece {

    /**
     * A scrum is valid when both teams participating have a scrum
     */
    override fun isValid()= attackingTeam.hasEnoughScrumPlayersInPlay() && defendingTeam.hasEnoughScrumPlayersInPlay()
}

data class LineOut(override val attackingTeam: Team, override val defendingTeam: Team) : SetPiece {

    /**
     * A lineout is valid when both team participating have the same number of players playing the line-out
     */
    override fun isValid() = attackingTeam.nrOfPlayersInlineOut == defendingTeam.nrOfPlayersInlineOut
}
