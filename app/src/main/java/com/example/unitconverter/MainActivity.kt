package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter (){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.00
        val result = (inputValueDouble * conversionFactor.value * 100.0 /oconversionFactor.value ).roundToInt() / 100.0
        outputValue = result.toString()
    }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text("Unit Converter" , style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue, onValueChange = {
                inputValue = it
                convertUnits()
            }, label = { Text("Enter Value")} )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                // Input Box
                Box{
                    // Input Box
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = {Text ("CentiMeters") }, onClick = {
                            iExpanded = false
                            inputUnit = "CentiMeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("Meters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("Feet") }, onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("MilliMeters") }, onClick = {
                            iExpanded = false
                            inputUnit = "MilliMeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Output Box
                Box{
                    // Output Button
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputunit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = {Text ("CentiMeters") }, onClick = {
                            oExpanded = false
                            outputunit = "CentiMeters"
                            oconversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("Meters") }, onClick = {
                            oExpanded = false
                            outputunit = "Meters"
                            oconversionFactor.value = 1.00
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("Feet") }, onClick = {
                            oExpanded = false
                            outputunit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text ("MilliMeters") }, onClick = {
                            oExpanded = false
                            outputunit = "MilliMeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        })
                    }
                }


            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Result $outputValue $outputunit", style = MaterialTheme.typography.titleMedium)
        }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview (){
    UnitConverter()
}