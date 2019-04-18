package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.LineOut
import com.paulienvanalst.rugbymatch.game.Scrum
import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.game.Type
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent
import org.springframework.context.event.EventListener

sealed class SetPieceEvent(source: Any, val setPiece: SetPiece, val winningTeam: TeamName) : ApplicationEvent(source)

fun List<SetPieceEvent>.wonBy(team: TeamName) = this.filter { it.winningTeam == team }
fun List<SetPieceEvent>.lineOutEvents() = this.filter { it is LineOutWasPlayed }
fun List<SetPieceEvent>.scrumEvents() = this.filter { it is ScrumWasPlayed }
fun List<SetPieceEvent>.lostBy(team: TeamName) = this.filter { it.winningTeam != team }

class ScrumWasPlayed(source: Any,  scrum: Scrum, winningTeam: TeamName) : SetPieceEvent(source, scrum, winningTeam)
class LineOutWasPlayed(source: Any, lineOut: LineOut, winningTeam: TeamName) : SetPieceEvent(source, lineOut, winningTeam)

class ScoringEvent(source: Any, val type: Type, val team: TeamName) : ApplicationEvent(source)