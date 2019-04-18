package com.paulienvanalst.rugbymatch.team

data class Player(val position: Position, val backNumber: Int) {
    fun isStarting(): Boolean {
        throw NotImplementedException()
    }
}