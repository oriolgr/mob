package com.wallapop.yatzee

import junit.framework.Assert.assertEquals
import org.junit.Test

class YatzeeTest {
    private val yatzee = Yatzee()

    @Test
    fun `cinco unos en categoria uno devuelve cinco`() {
        val resultado = yatzee.tirada(Die(1), Die(1), Die(1), Die(1), Die(1),
                                      Category("1"))
        assertEquals(5, resultado)
    }

    @Test
    fun `cuatro unos en categoria uno devuelve cuatro`() {
        val resultado = yatzee.tirada(Die(1), Die(1), Die(1), Die(1), Die(2),
                                      Category("1"))
        assertEquals(4, resultado)
    }

    @Test
    fun `ningun uno en categoria uno devuelve zero`() {
        val resultado = yatzee.tirada(Die(3), Die(4), Die(6), Die(5), Die(2),
                                      Category("1"))
        assertEquals(0, resultado)
    }

    @Test
    fun `cinco doses en categoria dos devuelve diez`() {
        val resultado = yatzee.tirada(Die(2), Die(2), Die(2), Die(2), Die(2),
                                      Category("2"))
        assertEquals(10, resultado)
    }

    @Test
    fun `tres doses en categoria dos devuelve seis`() {
        val resultado = yatzee.tirada(Die(2), Die(2), Die(2), Die(5), Die(4),
                                      Category("2"))
        assertEquals(6, resultado)
    }

    @Test
    fun `un par de doses en categoria pair devuelve 4`() {
        val resultado = yatzee.tirada(Die(2), Die(2), Die(3), Die(5), Die(4),
                                      Category("pair"))
        assertEquals(4, resultado)
    }

    @Test
    fun `un par de treses en categoria pair devuelve 6`() {
        val resultado = yatzee.tirada(Die(2), Die(3), Die(3), Die(5), Die(4),
                                      Category("pair"))
        assertEquals(6, resultado)
    }

    @Test
    fun `un trio de treses en categoria pair devuelve 6`() {
        val resultado = yatzee.tirada(Die(3), Die(3), Die(3), Die(5), Die(4),
                                      Category("pair"))
        assertEquals(6, resultado)
    }

    @Test
    fun `ningun par en categoria pair devuelve 0`() {
        val resultado = yatzee.tirada(Die(1), Die(2), Die(3), Die(5), Die(4),
                                      Category("pair"))
        assertEquals(0, resultado)
    }

    @Test
    fun `un par de doses y un par de treses en categoria pair devuelve 6`() {
        val resultado = yatzee.tirada(Die(2), Die(5), Die(2), Die(3), Die(3),
                                      Category("pair"))
        assertEquals(6, resultado)
    }

    @Test
    fun `un trio de cincos en categoria trios devuelve 15`() {
        val resultado = yatzee.tirada(Die(5), Die(5), Die(2), Die(5), Die(3),
                                      Category("three of a kind"))
        assertEquals(15, resultado)
    }

    @Test
    fun `ningun trio en categoria trios devuelve 0`() {
        val resultado = yatzee.tirada(Die(5), Die(1), Die(2), Die(5), Die(3),
                                      Category("three of a kind"))
        assertEquals(0, resultado)
    }

    @Test
    fun `un cuarteto de cincos en categoria trios devuelve 15`() {
        val resultado = yatzee.tirada(Die(5), Die(5), Die(5), Die(5), Die(3),
                                      Category("three of a kind"))
        assertEquals(15, resultado)
    }

    @Test
    fun `un cuarteto de cincos en categoria cuarteto devuelve 20`() {
        val resultado = yatzee.tirada(Die(5), Die(5), Die(5), Die(5), Die(3),
                                      Category("four of a kind"))
        assertEquals(20, resultado)
    }


}