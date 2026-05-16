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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextDark
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import java.util.Date

@Composable
fun HomeLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn( //weil Scrollbar
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp) //abstand zwischen Listenelementen -> Titel ganz links, Button ganz rechts
        ){
            item{
                SectionHeader (
                    title= "Meine Zeitkarten",
                    buttonText = "+ Neue Zeitkarte",
                    onButtonClick ={
                        //TODO: PopUp verknüpfen
                    }
                )
            }
            item{
                TicketCard()
            }
            item{
                SectionHeader (
                    title= "Meine Fahrten",
                    buttonText = "+ Neue Fahrt",
                    onButtonClick ={
                        //TODO: PopUp verknüpfen
                    }
                )
            }
            item{
                FahrtenCard()
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
                    "83 Tage",
                    modifier = Modifier
                        //.padding(bottom = 20.dp)
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
                    text = "Klimaticket Österreich Jugend",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppPrimary,
                    modifier = Modifier.padding(bottom = 4.dp)

                )

                Text(
                    text = "ÖBB",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("Gültig von: giltv")
                Text("Gültig bis: giltb")
                Text("Preis: 1095 €")

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
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun HomeLayoutPreview() {
    HomeLayout(
        modifier = Modifier.fillMaxSize()
    )
}
