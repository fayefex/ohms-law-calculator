package com.example.ohmslawcalculator

fun calculateVoltage(current: Double, resistance: Double): Double {
    return current * resistance
}

fun calculateCurrent(voltage: Double, resistance: Double): Double {
    return voltage / resistance
}

fun calculateResistance(voltage: Double, current: Double): Double {
    return voltage / current
}