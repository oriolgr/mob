package com.wallapop.marsrover

import com.wallapop.marsrover.CardinalPoints.E
import com.wallapop.marsrover.CardinalPoints.N
import com.wallapop.marsrover.CardinalPoints.S
import com.wallapop.marsrover.CardinalPoints.W
import com.wallapop.marsrover.CardinalPoints.valueOf
import com.wallapop.marsrover.Commands.L
import com.wallapop.marsrover.Commands.M
import com.wallapop.marsrover.Commands.R

class MarsRover {
    private var position = Position()

    private fun move(input: List<Commands>): String {
        input.forEach { c ->
            position.executeCommand(c)
        }
        return "$position"
    }

    fun execute(input: String): String {
        val parser = Parser()
        parser.parse(input)
        this.position = parser.inputPosition
        return move(parser.movementsList)
    }
}

class Parser {

    lateinit var inputPosition: Position
    lateinit var movementsList: List<Commands>

    fun parse(input: String) {
        val (_, position, movements) = input.lines()
        val (x, y, heading) = position.split(' ')
        movementsList = movements.map { movement -> Commands.valueOf(movement.toString()) }
        inputPosition = Position(x.toInt(), y.toInt(), valueOf(heading))
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