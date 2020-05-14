package com.wallapop.marsrover

import com.wallapop.marsrover.CardinalPoints.*
import com.wallapop.marsrover.Commands.L
import com.wallapop.marsrover.Commands.M
import com.wallapop.marsrover.Commands.R

class MarsRover {
    private var position = Position()

    // switch statement
    // duplication
    // open/close
    // primitive obsession
    // single responsibility
    private fun move(input: List<Commands>): String {
        input.forEach { c ->
            position.executeCommand(c)
        }
        return "$position"
    }

    fun execute(input: String): String {
        val (_, position, movements) = input.lines()
        definePosition(position)
        return move(movements.map {movement -> Commands.valueOf(movement.toString()) })
    }

    private fun definePosition(inputPosition: String) {
        val (x, y, heading) = inputPosition.split(' ')
        this.position = Position(x.toInt(), y.toInt(), valueOf(heading))
    }
}

enum class Commands {
    L,
    R,
    M
}

enum class CardinalPoints {
    N,
    E,
    S,
    W
}

data class Position(var x: Int = 0,
                    var y: Int = 0,
                    var heading: CardinalPoints = N) {

    override fun toString() = "$x $y $heading"

    fun executeCommand(command: Commands) {
        if (command == L && heading == N)
            heading = W
        else if (command == L && heading == E)
            heading = N
        else if (command == L && heading == S)
            heading = E
        else if (command == L && heading == W)
            heading = S
        else if (command == R && heading == N)
            heading = E
        else if (command == R && heading == E)
            heading = S
        else if (command == R && heading == S)
            heading = W
        else if (command == R && heading == W)
            heading = N
        else if (command == M)
            y++
    }
}