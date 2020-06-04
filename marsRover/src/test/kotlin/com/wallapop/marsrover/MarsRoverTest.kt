package com.wallapop.marsrover

import junit.framework.Assert.assertEquals
import org.junit.Test

class MarsRoverTest {
    @Test
    fun `Void input does not make rover move`() {
        val input = "5 5\n" +
                "0 0 N\n";
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 0 N")
    }

    @Test
    fun `move command makes rover goes north`() {
        val input = "5 5\n0 0 N\nM"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 1 N")
    }

    @Test
    fun `L command makes rover turn west when facing north`() {
        val input = "5 5\n" +
                "0 0 N\nL"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals("0 0 W", result)
    }

    @Test
    fun `LL command makes rover turn south when facing north`() {
        val input = "5 5\n" +
                "0 0 N\nLL"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 0 S")
    }

    @Test
    fun `LLL command makes rover turn east when facing north`() {
        val input = "5 5\n" +
                "0 0 N\nLLL"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 0 E")
    }

    @Test
    fun `LLLL command makes rover turn north when facing north`() {
        val input = "5 5\n" +
                "0 0 N\nLLLL"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 0 N")
    }

    @Test
    fun `RRRRR command makes rover turn east when facing north`() {
        val input = "5 5\n" +
                "0 0 N\nRRRRR"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "0 0 E")
    }

    @Test
    fun `define initial postion`() {
        val input = "5 5\n" +
                "1 1 E\n"
        val rover = MarsRover()
        val result = rover.execute(input)
        assertEquals(result, "1 1 E")
    }

//    @Test
//    fun `LM command makes rover move to east when facing north`() {
//        val input = "LM"
//        val rover = MarsRover()
//        val result = rover.execute(input)
//        assertEquals(result, "1 0 E")
//    }

    @Test
    fun `2 consecutive move commands make rover goes north 2 positions`() {
        val input = "5 5\n" +
                "0 0 N\nMM"
        val rover = MarsRover()
        val result = rover.execute(input)

        assertEquals(result, "0 2 N")
    }

    @Test
    fun `5 consecutive move commands make rover goes north 5 positions`() {
        val input = "5 5\n" +
                "0 0 N\nMMMMM"
        val rover = MarsRover()
        val result = rover.execute(input)

        assertEquals(result, "0 5 N")
    }


}