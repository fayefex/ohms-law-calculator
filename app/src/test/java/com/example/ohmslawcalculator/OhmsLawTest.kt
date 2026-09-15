package com.example.ohmslawcalculator

import org.junit.Test

import org.junit.Assert.*

class OhmsLawTest {
    @Test
    fun calculateVoltage_returnsCorrectResult() {
        val actualVoltage = calculateVoltage(2.0, 2.0)
        assertEquals(4.0, actualVoltage, 0.000001)
    }

    @Test
    fun calculateCurrent_returnsCorrectResult() {
        val actualCurrent = calculateCurrent(2.0, 1.0)
        assertEquals(2.0, actualCurrent, 0.000001)
    }

    @Test
    fun calculateResistance_returnsCorrectResult() {
        val actualResistance = calculateResistance(1.0, 2.0)
        assertEquals(0.5, actualResistance, 0.000001)
    }
}