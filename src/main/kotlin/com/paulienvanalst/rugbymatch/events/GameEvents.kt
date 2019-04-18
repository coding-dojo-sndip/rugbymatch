package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.LineOut
import com.paulienvanalst.rugbymatch.game.Scrum
import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.team.Team
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent

sealed class SetPieceEvent(source: Any, open val setPiece: SetPiece, open val winningTeam: TeamName) : ApplicationEvent(source)

class ScrumWasPlayed(source: Any, val scrum: SetPiece, override val winningTeam: TeamName): SetPieceEvent(source, scrum, winningTeam)

class LineOutWasPlayed(source: Any, lineOut: SetPiece, override val winningTeam: TeamName): SetPieceEvent(source, lineOut, winningTeam)

fun List<SetPieceEvent>.wonBy(teamName: TeamName) : List<SetPiece> = this.filter { it.winningTeam == teamName }.map { it.setPiece }

fun List<SetPieceEvent>.lostBy(teamName: TeamName) : List<SetPiece> = this
    .filter { teamName == it.setPiece.attackingTeam.name || teamName == it.setPiece.defendingTeam.name }
    .filter { it.winningTeam != teamName }
    .map { it.setPiece }

fun List<SetPieceEvent>.scrumEvents() : List<SetPiece> = this.filter { it.setPiece is Scrum }.map { it.setPiece }

fun List<SetPieceEvent>.lineOutEvents() : List<SetPiece> = this.filter { it.setPiece is LineOut }.map { it.setPiece }