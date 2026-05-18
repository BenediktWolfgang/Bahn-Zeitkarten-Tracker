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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import classes_and_Functions.VgFahrt
import classes_and_Functions.formatDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomeLayout( //Grundlayout in Home
    modifier: Modifier = Modifier,
    zeitkarten: MutableList<Zeitkarte>, //weil ArrayList ist veränderbar
    fahrten: MutableList<VgFahrt>,
    onZeitkarteAdd: (Zeitkarte) -> Unit,
    onFahrtAdd: (VgFahrt) -> Unit
) {
//Zeitkarten:
    //Eingabe für Zeitkarten Defaultmäßig falsch (zeigt Homescreen)
    var showAddZeitkarte by remember { mutableStateOf(false) }

//    val gespeicherteZeitkarten = remember {
//        mutableStateListOf<Zeitkarte>().apply { //veränderbare Liste
//            addAll(zeitkarten)
//        }
//    } //dass Zeitkarten gespeichert werden können

//Fahrten
    //Eingabe für Fahrten Defaultmäßig falsch (zeigt Homescreen)
    var showAddFahrt by remember { mutableStateOf(false) }

//    val gespeicherteFahrten = remember {
//        mutableStateListOf<VgFahrt>().apply { //veränderbare Liste
//            addAll(vgfahrten)
//        }
//    } //dass Fahrten gespeichert werden können

    if(showAddZeitkarte) {
        AddZeitkarte(
            modifier = modifier,
            onBackClick = {
                showAddZeitkarte = false
            },
        ) { neueZeitkarte ->
            zeitkarten.add(neueZeitkarte)
            showAddZeitkarte = false
        }
    } else if(showAddFahrt) {
        AddFahrt (
            modifier = modifier,
            zeitkarten = zeitkarten,
            onBackClick = {showAddFahrt = false},
            onSaveClick = { neueFahrt ->
                fahrten.add(neueFahrt)
            showAddFahrt = false
        }
    )
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            ZeitkartenSection(
                modifier = Modifier.weight(1f),
                zeitkarten = zeitkarten,
                onAddClick = {
                    showAddZeitkarte = true
                } //Zeitkarte hinzufügen
            )

            FahrtenSection(
                modifier = Modifier.weight(1f),
                vgfahrten = fahrten,
                onAddClick = {
                    showAddFahrt = true
                } //Fahrt hinzufügen
            )
        }
    }
}

@Composable
fun ZeitkartenSection( //Ab Meine Zeitkarten
    modifier:Modifier = Modifier,
    zeitkarten: List<Zeitkarte>,
    onAddClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SectionHeader(
            title = "Meine Zeitkarten",
            buttonText = "+ Neue Zeitkarte",
            onButtonClick = onAddClick
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
fun FahrtenSection( //ab Meine Fahrten
    modifier:Modifier = Modifier,
    vgfahrten: List<VgFahrt>,
    onAddClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SectionHeader (
            title= "Meine Fahrten",
            buttonText = "+ Neue Fahrt",
            onButtonClick =onAddClick
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
fun SectionHeader ( //Meine Zeitkarten und Meine Fahrten "Header" mit Button
    title:String,
    buttonText: String,
    onButtonClick: () -> Unit
){
    Row( //Überschrift
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
        OutlinedButton ( //Button
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
fun TicketCard( //eine Box aka Card einer Zeitkarte
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

            DashedVerticalDivider() //Stricklierte Linie

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
                Text("Preis: ${zeitkarte.preis/100.00} €")

            }
        }
    }
} //end TicketCard

@Composable
fun DashedVerticalDivider() { //vertikale linie - strichliert
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
fun FahrtenCard( //eine Card für eine Fahrt
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
                text = "${vgfahrt.von} - ${vgfahrt.bis}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppPrimary,
                modifier = Modifier.padding(bottom = 4.dp)

            )

            Text( //Klimaticket //TODO: Noch pointer bauen und verknüpfen
                text = "Klimaticket Österreich",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text("Datum: ${formatDate(vgfahrt.dayt.toLocalDate())}")
            Text("Km: ${vgfahrt.dist/10.0}")
            Text("Preis: ${vgfahrt.price/100.0} €")

        }
    }
} //end FahrtenCard

