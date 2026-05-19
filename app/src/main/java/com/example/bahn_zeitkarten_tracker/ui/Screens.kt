package com.example.bahn_zeitkarten_tracker.ui

import androidx.annotation.DrawableRes
import com.example.bahn_zeitkarten_tracker.R

//Liste aller möglichen screes mit Titel und label(für Menubar) und icon.
//Statt in menubar + 100 Wiederholungen
sealed class Screen( //sealed - nur das was hier definiert ist
    val title: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    data object Home : Screen(
        title = "Bahn-Zeitkarten-Tracker",
        label = "Tickets",
        icon = R.drawable.ticket_icon
    )

    data object Map : Screen(
        title = "Route anzeigen",
        label = "Karte",
        icon = R.drawable.map_icon
    )

    data object BreakEven : Screen(
        title = "Break-even-Analyse",
        label = "Analyse",
        icon = R.drawable.euro_icon
    )

    data object CO2 : Screen(
        title = "CO₂-Analyse",
        label = "CO₂",
        icon = R.drawable.leaf_icon
    )
}

val bottomNavigationScreens = listOf(
    Screen.Home,
    Screen.Map,
    Screen.BreakEven,
    Screen.CO2
)