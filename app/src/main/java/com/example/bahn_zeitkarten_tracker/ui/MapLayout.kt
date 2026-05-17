package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppSurface
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextLight
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted

@Composable
fun MapLayout(
    modifier: Modifier = Modifier
) {
    //Speicher von Eingabefeld
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }

    //Default
    val distanceText = if (from.isNotBlank() && to.isNotBlank()) {
        "Distanz: wird berechnet..."
    } else {
        "Distanz: Start und Ziel eingeben"
    } //TODO: noch ändern!

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) //Platz zwischen elementen
    ) {
       FahrtInput(
           from = from,
           to = to,
           onFromChange = {from = it},
           onToChange = { to = it }
       )

        MapPlaceholder( //TODO: Map einbinden
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Text( //TODO: berechnung einbinden
            text = distanceText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        val aktiv = from.isNotBlank() && to.isNotBlank() //aktiver Button oder nicht

        Button (
            onClick = {
                // TODO: Fahrt loggen PopUp
            },
            enabled = aktiv,
            shape = RoundedCornerShape(12.dp),
            colors = outlinedButtonColors( //zuerst grau; dann farbig
                contentColor = AppTextLight,
                disabledContentColor = AppTextLight,
                containerColor = AppPrimary,
                disabledContainerColor = AppTextMuted
            ),
            modifier = Modifier.fillMaxWidth(),


        ) {
            Text(
                text = "+ Fahrt loggen",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FahrtInput( //eingabefelder
    from: String,
    to: String,
    onFromChange: (String) -> Unit,
    onToChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = from,
            onValueChange = onFromChange,
            label = {
                Text("Von")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true //nur eine zeile
        )

        OutlinedTextField(
            value = to,
            onValueChange = onToChange,
            label = {
                Text("Bis")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Composable
fun MapPlaceholder( //Nur platzhalter
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = AppSurface,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                border = BorderStroke(1.dp, AppTextMuted),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kartenplatzhalter",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppTextMuted
        )

        Text(
            text = "Hier wird später die Karte angezeigt",
            fontSize = 14.sp,
            color = AppTextMuted
        )
    }
}




@Preview( //vorschau in Android studio
    name = "Vorschau HomeLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es  in Preview anzeigt
fun MapLayoutPreview() {
    MapLayout(
        modifier = Modifier.fillMaxSize()
    )
}