package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent

sealed class SetPieceEvent(source: Any, open val setPiece: SetPiece, open val winningTeam: TeamName) : ApplicationEvent(source)

class ScrumWasPlayed(source: Any, override val setPiece: SetPiece, override val winningTeam: TeamName): SetPieceEvent(source, setPiece, winningTeam)

class LineOutWasPlayed(source: Any, override val setPiece: SetPiece, override val winningTeam: TeamName): SetPieceEvent(source, setPiece, winningTeam)
