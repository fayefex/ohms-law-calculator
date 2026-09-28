package com.example.ohmslawcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ohmslawcalculator.ui.theme.OhmsLawCalculatorTheme
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OhmsLawCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OhmsLawScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OhmsLawScreen(modifier: Modifier = Modifier) {
    val inputState = remember {
        mutableStateOf("")
    }

    val secondInputState = remember {
        mutableStateOf("")
    }

    val selectedTargetState = remember {
        mutableStateOf("")
    }

    val resultState = remember {
        mutableStateOf("")
    }

    val firstInputLabel = when (selectedTargetState.value) {
        "V" -> "Current (A)"
        "I", "R" -> "Voltage (V)"
        else -> "Input 1"
    }

    val secondInputLabel = when (selectedTargetState.value) {
        "V", "I" -> "Resistance (Ω)"
        "R" -> "Current (A)"
        else -> "Input 2"
    }

    Column(
        modifier = modifier.padding(24.dp)
    ) {
        Text(text = "Ohm's Law Calculator")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Button(
                onClick = {
                    selectedTargetState.value = "V"
                }
            ) {
                Text(text = "V")
            }

            Button(
                onClick = {
                    selectedTargetState.value = "I"
                }
            ) {
                Text(text = "I")
            }

            Button(
                onClick = {
                    selectedTargetState.value = "R"
                }
            ) {
                Text(text = "R")
            }

        }

        Text(text = "Selected target:")

        Text(text = selectedTargetState.value)

        OutlinedTextField(
            value = inputState.value,
            onValueChange = { enteredText ->
                inputState.value = enteredText
            },
            label = {
                Text(text = firstInputLabel)
            }
        )

        OutlinedTextField(
            value = secondInputState.value,
            onValueChange = { enteredText ->
                secondInputState.value = enteredText
            },
            label = {
                Text(text = secondInputLabel)
            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    val firstNumber = inputState.value.toDoubleOrNull()
                    val secondNumber = secondInputState.value.toDoubleOrNull()

                    if (firstNumber == null || secondNumber == null) {
                        resultState.value = "Please enter valid numbers"
                    } else if (
                        (selectedTargetState.value == "I" ||
                                selectedTargetState.value == "R") &&
                        secondNumber == 0.0
                    ) {
                        resultState.value = "Cannot divide by zero"
                    } else {
                        resultState.value = when (selectedTargetState.value) {
                            "I" -> "%.2f A".format(
                                calculateCurrent(
                                    voltage = firstNumber,
                                    resistance = secondNumber
                                )
                            )

                            "V" -> "%.2f V".format(
                                calculateVoltage(
                                    current = firstNumber,
                                    resistance = secondNumber
                                )
                            )

                            "R" -> "%.2f Ω".format(
                                calculateResistance(
                                    voltage = firstNumber,
                                    current = secondNumber
                                )
                            )

                            else -> "Please select V, I, or R"
                        }
                    }
                }
            ) {
                Text(text = "Calculate")
            }

            Button(
                onClick = {
                    inputState.value = ""
                    secondInputState.value = ""
                    selectedTargetState.value = ""
                    resultState.value = ""
                }
            ) {
                Text(text = "Clear")
            }
        }

        OutlinedTextField(
            value = resultState.value,
            onValueChange = { enteredText ->
                resultState.value = enteredText
            },
            readOnly = true,
            label = {
                Text(text = "Result :")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OhmsLawScreenPreview() {
    OhmsLawCalculatorTheme {
        OhmsLawScreen()
    }
}