package com.example.bahn_zeitkarten_tracker.ui

import android.R.attr.top
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.R
import classes_and_Functions.createLineGraph
import java.time.LocalDateTime


@Composable
fun CO2Layout(
    modifier: Modifier = Modifier,
    zeitkarten: List<Zeitkarte>,
    fahrten: List<VgFahrt>,
    onZeitkarteAdd: (Zeitkarte) -> Unit,
    onFahrtAdd: (VgFahrt) -> Unit

) {


    // CO2 Berechnungen
    val co2Zug = fahrten.sumOf { it.co2 }
    val kmGesamt = fahrten.sumOf { it.dist } / 10.0
    val anzFahrten = fahrten.size
    val co2Auto = kmGesamt * 0.2173  // 217.3g/km in kg für Auto
    val co2Ersparnis = co2Auto - co2Zug
    val seit = fahrten.minByOrNull { it.dayt }?.dayt ?: LocalDateTime.now()
    val bis = fahrten.maxByOrNull { it.dayt }?.dayt ?: LocalDateTime.now()

    Column(
        modifier = modifier//.verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "CO2 Berechnung",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppPrimary,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Km gesamt: ${kmGesamt} km",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(start=16.dp)
        )

        Text(
            text = "Fahrten gesamt: ${anzFahrten}",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(start = 16.dp)
        )
        HorizontalDivider(
            color = AppTextMuted,
            modifier = Modifier.padding(end=8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) //dass devider nicht ganze seite braucht
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                Text(
                    text = "Zug/Öffis",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("CO2 Verbrauch: ${String.format("%.2f", co2Zug)} kg")
            }
            VerticalDivider(
                color = AppTextMuted,
                modifier = Modifier.padding(end=8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                Text(
                    text = "Auto",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("CO2 Verbrauch: ${String.format("%.2f", co2Auto)} kg")
            }

        }
        Text(
            text = "CO2 Ersparnis: ${String.format("%.2f", co2Ersparnis)} kg",
            fontSize = 16.sp,
            color = AppPrimary,
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(bottom = 32.dp)
                .padding(start = 16.dp)
        )

        HorizontalDivider(
            color = AppTextMuted,
            modifier = Modifier.padding(end=8.dp)
        )

        Text(
            text = "CO2 Verbrauch: ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AppPrimary,
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(bottom = 32.dp)
                .padding(start = 16.dp)
        )

        LineGraphView(
            entries = fahrten,
            seit = seit,
            bis = bis,
            zeitkartenPreis = 0.0,
            showCo2 = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        )

        Text(
            text = "CO2 Verbrauch Auto ... rot",
            modifier = Modifier.padding(top = 16.dp).padding(start = 16.dp)
        )
        Text(
            text = "CO2 Verbrauch Zug ... grün",
            modifier = Modifier.padding(top = 4.dp).padding(start = 16.dp)
        )


    }
}



