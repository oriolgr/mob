package com.wallapop.marsrover

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class MarsRoverLondonTest {
    @Test
    fun `MarsRover full movement`() {
        val input = "5 5\n1 2 N\nLMLMLMLMM"

        val emisor: Emisor = mockk()
        val parser = ParserAsync()

        val rover = MarsRoverAsync(parser)
        rover.execute(input)

        verify { emisor.send("1 3 N") }
    }

    @Test
    fun `MarsRover should understand input`() {
        val input = "5 5\n1 2 N\nL"

        val emisor: Emisor = mockk()
        val parser: ParserAsync = mockk()

        every { parser.parse("5 5\n1 2 N\nL") } just runs

        val rover = MarsRoverAsync(parser)
        rover.execute(input)

        verify { parser.parse("5 5\n1 2 N\nL") }
    }

    @Test
    fun `MarsRover should turn left`() {
        val input = "5 5\n1 2 N\nL"

        val emisor: Emisor = mockk()
        val parser: ParserAsync = mockk()

        val commandLeft: CommandAsync = mockk()
        val position = PositionAsync(CoordinateAsync(1, 2), HeadingAsync.N)

        every { parser.parse("5 5\n1 2 N\nL") } just runs
        every { parser.commands() } returns listOf(commandLeft)
        every { parser.initialPosition() } returns position

        val rover = MarsRoverAsync(parser)
        rover.execute(input)

        verify { commandLeft.execute(position) }
    }

    @Test
    fun `ParseAsync should parse initial position`() {
        val input = "5 5\n1 2 N\nL"
        val parser = ParserAsync()

        parser.parse(input)

        assertEquals(PositionAsync(CoordinateAsync(1, 2), HeadingAsync.N), parser.initialPosition())
    }

    @Test
    fun `ParseAsync should parse initial position heading west`() {
        val input = "5 5\n1 2 W\nL"
        val parser = ParserAsync()

        parser.parse(input)

        assertEquals(PositionAsync(CoordinateAsync(1, 2), HeadingAsync.W), parser.initialPosition())
    }
}
