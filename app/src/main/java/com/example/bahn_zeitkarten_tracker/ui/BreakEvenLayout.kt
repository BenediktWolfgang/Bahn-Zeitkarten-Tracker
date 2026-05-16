package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BreakEvenLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Break-even Analyse")
        Text("Hier wird später berechnet, ab wann sich die Zeitkarte lohnt.")
    }
}



@Preview( //vorschau in Android studio
    name = "Vorschau BreakEvenLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun BreakEvenLayoutPreview() {
    BreakEvenLayout(
        modifier = Modifier.fillMaxSize()
    )
}