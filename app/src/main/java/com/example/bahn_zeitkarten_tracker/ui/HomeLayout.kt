package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import classes_and_Functions.Zeitkarte
import androidx.compose.foundation.lazy.items
import classes_and_Functions.VgFahrt
import classes_and_Functions.formatDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//dummy Zeitkarten:
val zeitkarten = listOf(
    Zeitkarte(
        name = "Klimaticket Österreich Jugend",
        preis = 1095,
        firma = null,
        link = "",
        giltv = LocalDate.of(2024, 1, 1),
        giltb = LocalDate.of(2025, 12, 31)
    ),
    Zeitkarte(
        name = "Wiener Linien Jahreskarte",
        preis = 365,
        firma = "Wiener Linien",
        link = "",
        giltv = LocalDate.of(2025,5,31),
        giltb = LocalDate.of(2026,5,30)
    ),
    Zeitkarte(
        name = "Klimaticket Österreich Jugend",
        preis = 1095,
        firma = "ÖBB",
        link = "",
        giltv = LocalDate.of(2025, 1, 1),
        giltb = LocalDate.of(2026, 12, 31)
    )
)

val vgfahrten = listOf(
    VgFahrt(
        von = "Wien",
        bis = "Salzburg",
        dist = 300,
        dayt = LocalDateTime.of(2026, 5, 11, 15, 22)
    ),
    VgFahrt(
        von = "Wien",
        bis = "Graz",
        dist = 200,
        dayt = LocalDateTime.of(2026, 5, 15, 12, 22)
    )
)

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        ZeitkartenSection(
            modifier = modifier.weight(1f)
        )

        FahrtenSection(
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun ZeitkartenSection(
    modifier:Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SectionHeader(
            title = "Meine Zeitkarten",
            buttonText = "+ Neue Zeitkarte",
            onButtonClick = {
                //TODO: PopUp verknüpfen
            }
        )

        LazyColumn( //weil Scrollbar
            verticalArrangement = Arrangement.spacedBy(16.dp) //abstand zwischen Listenelementen -> Titel ganz links, Button ganz rechts
        ) {
            items(zeitkarten) { zeitkarte ->
                TicketCard(zeitkarte = zeitkarte)
            }
        }
    }

}


@Composable
fun FahrtenSection(
    modifier:Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SectionHeader (
            title= "Meine Fahrten",
            buttonText = "+ Neue Fahrt",
            onButtonClick ={
                //TODO: PopUp verknüpfen
            }
        )

        LazyColumn( //weil Scrollbar
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(vgfahrten) { vgfahrt ->
                FahrtenCard(vgfahrt = vgfahrt)
            }

        }
    }
}

@Composable
fun SectionHeader (
    title:String,
    buttonText: String,
    onButtonClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        OutlinedButton (
            onClick = onButtonClick,
            border = BorderStroke(
                width = 1.dp,
                color = AppPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = AppPrimary
            )
        )
        {
            Text(
                text= buttonText,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun TicketCard(
zeitkarte: Zeitkarte
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            //linker Bereich
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(130.dp),
                contentAlignment = Alignment.BottomCenter
            ){
//                giltv
//                giltb
                Text (
                    text = "${zeitkarte.giltNochTage()} Tage",
                    maxLines = 1,
                    fontSize = 13.sp,
                    softWrap = false,
                    modifier = Modifier
                        .graphicsLayer(rotationZ = -90f)
                        .align(Alignment.Center)
                )
            }

            DashedVerticalDivider()

            //rechter Bereich
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f) //restlicher Platz
            ) {
                Text(
                    text = zeitkarte.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppPrimary,
                    modifier = Modifier.padding(bottom = 4.dp)

                )

                Text(
                    text = zeitkarte.firma?: "",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("Gültig von: ${formatDate(zeitkarte.giltv)}")
                Text("Gültig bis: ${formatDate(zeitkarte.giltb)}")
                Text("Preis: ${zeitkarte.preis} €")

            }
        }
    }
} //end TicketCard

@Composable
fun DashedVerticalDivider() { //vertikale linie
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    ) {
        drawLine(
            color = AppTextMuted,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = 0f, y = size.height),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(30f, 30f),
                0f
            )
        )
    }
}

@Composable
fun FahrtenCard(
    vgfahrt: VgFahrt
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .padding(start= 32.dp)
                .weight(1f) //restlicher Platz
        ) {
            Text(
                text = "Von - Bis",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppPrimary,
                modifier = Modifier.padding(bottom = 4.dp)

            )

            Text(
                text = "Klimaticket Österreich",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text("Datum: ")
            Text("Km: ")
            Text("Preis: 1095 €")

        }
    }
} //end FahrtenCard


@Preview( //vorschau in Android studio
    name = "Vorschau HomeLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es  in Preview anzeigt
fun HomeLayoutPreview() {
    HomeLayout(
        modifier = Modifier.fillMaxSize()
    )
}
