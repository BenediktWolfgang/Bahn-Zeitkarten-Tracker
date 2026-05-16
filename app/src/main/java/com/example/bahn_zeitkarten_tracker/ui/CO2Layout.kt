package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CO2Layout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Umweltanalyse")
        Text("Hier wird später die CO₂-Ersparnis angezeigt.")
    }
}



@Preview( //vorschau in Android studio
    name = "Vorschau HomeLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun CO2LayoutPreview() {
    CO2Layout(
        modifier = Modifier.fillMaxSize()
    )
}