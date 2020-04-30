package com.wallapop.marsrover

class MarsRover {

    private var y = 0
    private var x = 0

    private var heading = "N"

    fun move(input: String): String {
        input.chars().forEach { c ->
            if (c == 'L'.toInt() && heading == "N")
                heading = "W"
            else if (c == 'L'.toInt() && heading == "E")
                heading = "N"
            else if (c == 'L'.toInt() && heading == "S")
                heading = "E"
            else if (c == 'L'.toInt() && heading == "W")
                heading = "S"
            else if (c == 'R'.toInt() && heading == "N")
                heading = "E"
            else if (c == 'R'.toInt() && heading == "E")
                heading = "S"
            else if (c == 'R'.toInt() && heading == "S")
                heading = "W"
            else if (c == 'R'.toInt() && heading == "W")
                heading = "N"
            else y++
        }
        return "$x $y $heading"
    }

    fun execute(input: String): String {
        val (map, position, movements) = input.lines()
        configureMap(map)
        definePosition(position)
        return move(movements)
    }

    private fun definePosition(position: String) {
        val (x, y, heading) = position.split(' ')
        this.x = x.toInt();
        this.y = y.toInt();
        this.heading = heading;

    }


    fun configureMap(input: String) {

    }

}
