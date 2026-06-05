package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import classes_and_Functions.Zeitkarte
import classes_and_Functions.converttoDate
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted

@Composable
fun AddZeitkarte(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClick: (Zeitkarte) -> Unit
){
    var name by remember { mutableStateOf("") }
    var firma by remember { mutableStateOf("") }
    var preis by remember { mutableIntStateOf(0) }
    var giltv by remember { mutableStateOf("") }
    var giltb by remember { mutableStateOf("") }
    var errormsg by remember { mutableStateOf("") }
    var showerror by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
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
            text = "Neue Zeitkarte",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppPrimary
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("*Name der Zeitkarte", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = firma,
            onValueChange = { firma = it },
            label = { Text("Firma") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = if (preis == 0) "" else preis.toString(),
            onValueChange = { preis = it.toIntOrNull() ?: 0 },
            label = { Text("Preis (in €)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        OutlinedTextField(
            value = giltv,
            onValueChange = { giltv = it },
            label = { Text("*Gültig von (DDMMYYYY)", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = giltb,
            onValueChange = { giltb = it },
            label = { Text("*Gültig bis (DDMMYYYY)", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(    text = "* Pflichtfelder",
            fontSize = 12.sp,
            color = AppTextMuted
        )

        OutlinedButton(
            onClick = {
                if (giltv.isBlank() || giltb.isBlank() || name.isBlank()) return@OutlinedButton


                val neueZeitkarte = Zeitkarte(
                    name = name,
                    preis = preis*100,
                    firma = firma,
                    link = null,
                    giltv = converttoDate(giltv),
                    giltb = converttoDate(giltb)
                )
                onSaveClick(neueZeitkarte)
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
            Text("Zeitkarte hinzufügen")
        }
    }
}



