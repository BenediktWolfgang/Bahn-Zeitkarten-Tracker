package com.example.bahn_zeitkarten_tracker.ui

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
import classes_and_Functions.Zeitkarte
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted

@Composable
fun BreakEvenLayout(
    modifier: Modifier = Modifier
) {
    var selectedZeitkarte by remember { mutableStateOf<Zeitkarte?>(null) }

    Column(
        modifier = modifier
    ) {

        //Suchfeld für Zeitkarte
        ZeitkartenSuche(
            zeitkarten = zeitkarten,
            onZeitkarteSelected = { zeitkarte ->
                selectedZeitkarte = zeitkarte
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
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
                Text(
                    text = "Kosten Zeitkarte: €",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "Fahrten gesamt: ",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )
                Text(
                    text = "Kosten pro Fahrt: €",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "Fahrtkosten gesamt: €",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 16.dp)
                )

                HorizontalDivider(
                    color = AppTextMuted,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "CO2 Ersparnis: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppPrimary,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(bottom = 4.dp)
                        .padding(start = 16.dp)
                )

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(IntrinsicSize.Min) //dass devider nicht ganze seite braucht
//                        .padding(16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .weight(1f)
//                    ) {
//                        Text(
//                            text = "Zug/Öffis",
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(bottom = 8.dp)
//                        )
//                        Text("CO2 Verbrauch: ")
//                    }
//                    VerticalDivider(
//                        color = AppTextMuted,
//                        modifier = Modifier.padding(end = 8.dp)
//                    )
//
//
//                    Column(
//                        modifier = Modifier
//                            .weight(1f)
//                    ) {
//                        Text(
//                            text = "Auto",
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(bottom = 8.dp)
//                        )
//                        Text("CO2 Verbrauch: ")
//                    }
//
//                }
            }
        }
    }
}



@Preview( //vorschau in Android studio
    name = "Vorschau BreakEvenLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es  in Preview anzeigt
fun BreakEvenLayoutPreview() {
    BreakEvenLayout(
        modifier = Modifier.fillMaxSize()
    )
}