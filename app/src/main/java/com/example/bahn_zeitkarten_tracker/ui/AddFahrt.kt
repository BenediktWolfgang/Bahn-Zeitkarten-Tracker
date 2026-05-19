package com.example.bahn_zeitkarten_tracker.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import classes_and_Functions.converttoDate
import classes_and_Functions.converttoDateTime
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import java.time.LocalDateTime

@Composable
fun AddFahrt(
    modifier: Modifier = Modifier,
    zeitkarten: List<Zeitkarte>, //Liste zum Auswählen
    onBackClick: () -> Unit,
    onSaveClick: (VgFahrt) -> Unit
){
    var von by remember { mutableStateOf("") }
    var bis by remember { mutableStateOf("") }
    var dist by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var zeitkarte by remember {mutableStateOf<Zeitkarte?>(null)}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(
            onClick = onBackClick
        ) {
            Text(
                text= "← Zurück",
                color = AppPrimary
            )
        }


        Text(
            text = "Neue Fahrt",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppPrimary
        )

        OutlinedTextField(
            value = von,
            onValueChange = { von = it },
            label = { Text("Startlocation der Fahrt") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = bis,
            onValueChange = { bis = it },
            label = { Text("Endlocation der Fahrt") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = dist,
            onValueChange = { dist = it },
            label = { Text("Distanz ") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Datum der Fahrt (z.B: 01052026)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Uhrzeit der Fahrt (z.B: 1200)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        ZeitkartenSuche(
            zeitkarten = zeitkarten,
            onZeitkarteSelected = {zeitkarte = it}
        )



        OutlinedButton(
            onClick = {
                //wen datum und/oder Uhrzeit leer -> Zeitpunkt jetzt
                //sonst datum formattieren
                val fahrtZeitpunkt = if(date.isBlank() || time.isBlank()) {
                    LocalDateTime.now()
                } else {
                    converttoDateTime(date, time)
                }


                val neueFahrt = VgFahrt(
                    von = von,
                    bis = bis,
                    dist = dist.toIntOrNull() ?: 0,
                    dayt = fahrtZeitpunkt,
                    zeitkarte = zeitkarte
                )
                onSaveClick(neueFahrt)
            },
            border = BorderStroke(
                width = 1.dp,
                color = AppPrimary
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = AppPrimary
            )

        ) {
            Text("Fahrt hinzufügen")
        }
    }
}


