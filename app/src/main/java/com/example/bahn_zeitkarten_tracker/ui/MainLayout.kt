package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextLight
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import java.util.Locale



//LayoutMain für Grundlayout (auf allen Seiten gleich)
@Composable
fun MainLayout(){
    val gespeicherteZeitkarten = remember {
        mutableStateListOf<Zeitkarte>().apply { addAll(zeitkarten)}
        // Ab .apply auskommentieren für App ohne Demodaten
    } //dass Zeitkarten gespeichert werden können

    val gespeicherteFahrten = remember {
        mutableStateListOf<VgFahrt>().apply { addAll(vgfahrten)}
        // Ab .apply auskommentieren für App ohne Demodaten
    } //dass Fahrten gespeichert werden können


    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) } //welcher Screen ist gerade aktiv + default Home als erstes
    Scaffold( //Layout - untereinander
        modifier = Modifier
            .fillMaxSize() //ganzer Bildschirm
            .background(Color.White)
            .systemBarsPadding(), //nicht unter Statusbar/Navigationsbar vom Gerät
        topBar = {
            AppHeader(title = selectedScreen.title, icon = selectedScreen.icon )
        },
        bottomBar = {
            MenuBar(
                selectedScreen = selectedScreen,
                onScreenSelected = { screen ->
                    selectedScreen = screen
                }
            )
        }
    ) {
                paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            when (selectedScreen) { //Inhalt zuweisen, je nach Screen
                Screen.Home -> HomeLayout(
                    modifier = Modifier.fillMaxSize(),
                    zeitkarten = gespeicherteZeitkarten,
                    fahrten = gespeicherteFahrten,
                    onZeitkarteAdd = { neueZeitkarte ->
                        gespeicherteZeitkarten.add(neueZeitkarte)
                    },
                    onFahrtAdd = { neueFahrt ->
                        gespeicherteFahrten.add(neueFahrt)
                    }
                )
                Screen.Map -> MapLayout(
                    modifier = Modifier.fillMaxSize()
                )

                Screen.BreakEven -> BreakEvenLayout(
                    modifier = Modifier.fillMaxSize(),
                    zeitkarten = gespeicherteZeitkarten,
                    fahrten = gespeicherteFahrten,
                    onZeitkarteAdd = { neueZeitkarte ->
                        gespeicherteZeitkarten.add(neueZeitkarte)
                    },
                    onFahrtAdd = { neueFahrt ->
                        gespeicherteFahrten.add(neueFahrt)
                    }
                )
                Screen.CO2 -> CO2Layout(
                    modifier = Modifier.fillMaxSize(),
                    zeitkarten = gespeicherteZeitkarten,
                    fahrten = gespeicherteFahrten,
                    onZeitkarteAdd = { neueZeitkarte ->
                        gespeicherteZeitkarten.add(neueZeitkarte)
                    },
                    onFahrtAdd = { neueFahrt ->
                        gespeicherteFahrten.add(neueFahrt)
                    }
                )
            }
        }

    }
} //Ende MainLayout


//AppHeader Funktion => UI Baustein "Header":
//Titel und Icon ändern sich je nachdem auf welchem screen man sich befindet.
//da der Ticket screen der Homescreen ist wird hier der App Titel angezeigt
//Daten zu den einzellnen screens ist in screens gespeichert.
@Composable
fun AppHeader(
    title: String,
    icon: Int
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp) //Platz vor inhalt
            .height(56.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Screen Icon", //Eigtl Burgermenu/Sidebar. jedoch nicht realisiert, deshalb Icon.
            modifier = Modifier
                .size(48.dp)
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
        )

        Text(
            text = title.uppercase(Locale.GERMAN), //TODO:Wenn App English ändern
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
} //Ende Header

//MenuBar - Navigationsleiste am unteren Rand:
@Composable
fun MenuBar(
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit
){
    NavigationBar {
        bottomNavigationScreens.forEach { screen ->
            MenuBarItem( //extra Funktion (siehe unten)
                selected = selectedScreen == screen,
                icon = screen.icon,
                label = screen.label,
                onClick = {
                    onScreenSelected(screen)
                }
            )
        }
    }
}//End Menubar

//MenuBarItem: wird 4x gebraucht für jeden screen der in der Menubar sein soll.
@Composable
fun RowScope.MenuBarItem(
    selected: Boolean,
    icon: Int,
    label: String,
    onClick: () -> Unit
){
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label
            )
        },
        label = {
            Text(label)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppTextLight,
            selectedTextColor = AppPrimary,
            indicatorColor = AppPrimary,
            unselectedIconColor = AppTextMuted,
            unselectedTextColor = AppTextMuted
        )
    )
}


//Vorschau für Layout erstellung verwendet
@Preview( //vorschau in Android studio
    name = "Vorschau MainLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun MainLayoutPreview() {
    MainLayout()
}

