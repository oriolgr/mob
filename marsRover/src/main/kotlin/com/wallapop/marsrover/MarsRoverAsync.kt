package com.wallapop.marsrover

open class Emisor {
    fun send(message: String) {
        TODO("Not yet implemented")
    }
}

open class ParserAsync {
    lateinit var initialPosition: PositionAsync
    lateinit var movementsList: List<CommandAsync>

    fun parse(input: String) {
        val (_, position, movements) = input.lines()
        val (x, y, heading) = position.split(' ')
        movementsList = movements.map { movement -> MovementFactoryAsync.create(enumValueOf(movement.toString())) }

        initialPosition = PositionAsync(CoordinateAsync(x.toInt(), y.toInt()), enumValueOf(heading))
    }

    fun initialPosition() = initialPosition
    fun commands() = movementsList
}

object MovementFactoryAsync {
    fun create(command: CommandsAsync) = when (command) {
        CommandsAsync.L -> TurnLeftAsync()
        CommandsAsync.R -> TurnRightAsync()
        CommandsAsync.M -> MoveAsync()
    }
}

interface CommandAsync {
    fun execute(position: PositionAsync)
}

class TurnLeftAsync : CommandAsync {

    override fun execute(position: PositionAsync) {
//        position.turn(Commands.L)
    }

}

class TurnRightAsync : CommandAsync {

    override fun execute(position: PositionAsync) {
//        position.turn(Commands.R)
    }

}

class MoveAsync : CommandAsync {

    override fun execute(position: PositionAsync) {
//        position.move()
    }

}

open class MarsRoverAsync(val parser: ParserAsync) {
    fun execute(input: String) {
        parser.parse(input)
        parser.initialPosition()
        parser.commands().forEach { c ->
            c.execute(parser.initialPosition())
        }
    }
}

enum class HeadingAsync {
    N,
    W,
    E,
    S
}

enum class CommandsAsync {
    L,
    R,
    M
}

data class CoordinateAsync(val x: Int, val y: Int)

data class PositionAsync(val coordinate: CoordinateAsync, val heading: HeadingAsync)
