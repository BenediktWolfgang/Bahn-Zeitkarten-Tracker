package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import classes_and_Functions.createLineGraph
import classes_and_Functions.formatDate
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import com.jjoe64.graphview.GraphView
import java.time.LocalDateTime

//SuchBar für die Zeitkarte (CO2 und BreakEven)
@Composable
fun ZeitkartenSuche(
    zeitkarten: List<Zeitkarte>,
    onZeitkarteSelected: (Zeitkarte) -> Unit
) {
    var zeitkarteInput by remember { mutableStateOf("") } //Speichert Input aka Suchtext
    var zeigeResults by remember { mutableStateOf(false) } //wenn ausgewählt verschwindet liste von zeitkarten wieder

    //Filter: geht durch alle gespeicherten Zeitkarten. Wenn Name oder Firma zum Suchtext passt, wird es angezeigt
    val gefilterteZeitkarten = zeitkarten.filter { zeitkarte ->
        zeitkarte.name.contains(zeitkarteInput, ignoreCase = true) ||
                (zeitkarte.firma?.contains(zeitkarteInput, ignoreCase = true) == true)
    }
    Column() {
        OutlinedTextField(
            value = zeitkarteInput,
            onValueChange = { neuerText ->
                zeitkarteInput = neuerText
                zeigeResults = neuerText.isNotBlank() //zeige zeitkarten, wenn nicht leer
            },
            label = {
                Text("Zeitkarte")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true //nur eine zeile
        )

        //passende Zeitkarte als anklickbaren Text anzeigen
        if(zeigeResults && zeitkarteInput.isNotBlank()){ //nur anzeigen, wenn bereits ein suchtext eingegeben wurde
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ){
                gefilterteZeitkarten.forEach {zeitkarte ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                zeitkarteInput = "${zeitkarte.name}   (gültig bis: ${formatDate(zeitkarte.giltb)})"
                                zeigeResults = false //zeitkarten nicht mehr anzeigen
                                onZeitkarteSelected(zeitkarte)
                            }
                    ){
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(
                                text = zeitkarte.name,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "(gültig bis: ${formatDate(zeitkarte.giltb)})",
                                color = AppTextMuted
                            )


                        }
                    }


                }

            }
        }
    }
}


//Graphview(xml) in compose einbinden -> als AndroidView
//Code von ChatGPT kopiert und an unsere situation angepasst (Prompt siehe Readme - KI Nutzung)
@Composable
fun LineGraphView(
    entries: List<VgFahrt>,
    seit: LocalDateTime,
    bis: LocalDateTime,
    zeitkartenPreis: Double = 0.0,
    showCo2: Boolean = false,
    showCar: Boolean = false,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(end= 16.dp),
        factory = { context ->
            GraphView(context)
        },
        update = { graph ->
            createLineGraph(
                graph = graph,
                entries = entries,
                seit = seit,
                bis = bis,
                ZeitkartenPreis = zeitkartenPreis,
                showCo2 = showCo2
            )
        }
    )
}