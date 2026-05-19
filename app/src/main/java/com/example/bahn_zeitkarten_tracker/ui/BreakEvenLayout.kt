package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import classes_and_Functions.AvgPrice
import classes_and_Functions.BreakEvenSim
import classes_and_Functions.SumPrice
import classes_and_Functions.SumRides
import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted

@Composable
fun BreakEvenLayout(
    modifier: Modifier = Modifier,
    zeitkarten: List<Zeitkarte>,
    fahrten: List<VgFahrt>,
    onZeitkarteAdd: (Zeitkarte) -> Unit,
    onFahrtAdd: (VgFahrt) -> Unit
) {
    var selectedZeitkarte by remember { mutableStateOf<Zeitkarte?>(null) }

    val gefilterteFahrten = if (selectedZeitkarte != null) {
        fahrten.filter { it.zeitkarte == selectedZeitkarte }
    } else emptyList() //Suche

    val fahrtkostenGesamt = SumPrice(gefilterteFahrten)
    val durchschnitt = AvgPrice(gefilterteFahrten)
    val breakEven = selectedZeitkarte?.let { BreakEvenSim(gefilterteFahrten, it) }
    val anzFahrten = SumRides(gefilterteFahrten)

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        //Suchfeld für Zeitkarte
        ZeitkartenSuche(
            zeitkarten = zeitkarten,
            onZeitkarteSelected = { zeitkarte ->
                selectedZeitkarte = zeitkarte
            }
        )

        Column( //Text
            modifier = modifier
        ) {
            Text(
                text = "Break-Even Berechnung",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppPrimary,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            //(nur wenn Zeitkarte ausgewählt sonst defaulttext)
            if(selectedZeitkarte != null){
                Text(
                    text = ("Kosten Zeitkarte: ${selectedZeitkarte?.preis?.div(100.00) ?: "-" } €"),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "Fahrten gesamt: ${anzFahrten}",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )
                Text(
                    text = "Kosten pro Fahrt: ${durchschnitt/100.00} €",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "Fahrtkosten gesamt: ${fahrtkostenGesamt/100.00} €",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )

                HorizontalDivider(
                    color = AppTextMuted,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "noch ${breakEven?.toInt()} Fahrten um Break-Even zu erreichen",
                    fontSize = 16.sp,
                    color = AppPrimary,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(bottom = 32.dp)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "Preisvergleich: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppPrimary,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(bottom = 32.dp)
                        .padding(start = 16.dp)
                )


                // Graph anzeigen:
                LineGraphView(
                    entries = gefilterteFahrten,
                    seit = selectedZeitkarte!!.giltv.atStartOfDay(),
                    bis = selectedZeitkarte!!.giltb.atStartOfDay(),
                    zeitkartenPreis = selectedZeitkarte!!.preis.toDouble(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                Text( //legende:
                    text = "Kosten Zeitkarte ... blau",
                    modifier = Modifier.padding(top = 16.dp).padding(start = 16.dp)
                )
                Text(
                    text = "Kosten Einzeltickets ... grün",
                    modifier = Modifier.padding(top = 4.dp).padding(start = 16.dp)
                )
            } else {
                Text(
                    text = "Zeitkarte auswählen um Berechnung anzuzeigen",
                    color = AppTextMuted,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


