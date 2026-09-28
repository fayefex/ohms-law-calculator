package com.example.ohmslawcalculator

import org.junit.Test

import org.junit.Assert.*

class OhmsLawTest {
    @Test
    fun calculateVoltage_returnsCorrectResult() {
        val actualVoltage = calculateVoltage(2.0, 5.0)
        assertEquals(10.0, actualVoltage, 0.000001)
    }

    @Test
    fun calculateCurrent_returnsCorrectResult() {
        val actualCurrent = calculateCurrent(9.0, 4.0)
        assertEquals(2.25, actualCurrent, 0.000001)
    }

    @Test
    fun calculateResistance_returnsCorrectResult() {
        val actualResistance = calculateResistance(9.0, 2.0)
        assertEquals(4.5, actualResistance, 0.000001)
    }
}