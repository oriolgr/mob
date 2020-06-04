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
        inputPosition = Position(x.toInt(), y.toInt(), HeadingFactory.create(valueOf(heading)))
    }
}

object HeadingFactory {

    fun create(cardinalPoints: CardinalPoints): Heading =
            when (cardinalPoints) {
                N -> HeadingNorth()
                E -> HeadingEast()
                S -> HeadingSouth()
                W -> HeadingWest()
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

abstract class Heading {

    fun turn(command: Commands): Heading {
        if (command == L)
            return turnLeft()

        if (command == R)
            return turnRight()

        return this
    }

    abstract fun turnRight(): Heading
    abstract fun turnLeft(): Heading
}

class HeadingNorth : Heading() {

    override fun turnLeft(): Heading {
        return HeadingWest()
    }

    override fun turnRight(): Heading {
        return HeadingEast()
    }

    override fun toString(): String {
        return "N"
    }

}

class HeadingEast : Heading() {

    override fun turnRight(): Heading {
        return HeadingSouth()
    }

    override fun turnLeft(): Heading {
        return HeadingNorth()
    }

    override fun toString(): String {
        return "E"
    }

}

class HeadingSouth : Heading() {

    override fun turnRight(): Heading {
        return HeadingWest()
    }

    override fun turnLeft(): Heading {
        return HeadingEast()
    }

    override fun toString(): String {
        return "S"
    }

}

class HeadingWest : Heading() {

    override fun turnRight(): Heading {
        return HeadingNorth()
    }

    override fun turnLeft(): Heading {
        return HeadingSouth()
    }

    override fun toString(): String {
        return "W"
    }

}

data class Position(var x: Int = 0,
                    var y: Int = 0,
                    var heading: Heading = HeadingNorth()) {

    override fun toString() = "$x $y $heading"

    fun executeCommand(command: Commands) {
        if (command != M)
            heading = heading.turn(command)
        else
            y++
    }
}