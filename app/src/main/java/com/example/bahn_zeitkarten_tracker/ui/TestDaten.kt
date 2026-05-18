package com.example.bahn_zeitkarten_tracker.ui

import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import java.time.LocalDate
import java.time.LocalDateTime


//dummy Zeitkarten:
val zeitkarten = listOf(
    Zeitkarte(
        name = "Klimaticket Österreich Jugend",
        preis = 109509,
        firma = null,
        link = "",
        giltv = LocalDate.of(2024, 1, 1),
        giltb = LocalDate.of(2025, 12, 31)
    ),
    Zeitkarte(
        name = "Wiener Linien Jahreskarte",
        preis = 36500,
        firma = "Wiener Linien",
        link = "",
        giltv = LocalDate.of(2025,5,31),
        giltb = LocalDate.of(2026,5,30)
    ),
    Zeitkarte(
        name = "Klimaticket Österreich Jugend",
        preis = 109500,
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
        dist = 3000,
        dayt = LocalDateTime.of(2026, 5, 11, 15, 22)
    ),
    VgFahrt(
        von = "Wien",
        bis = "Graz",
        dist = 2000,
        dayt = LocalDateTime.of(2026, 5, 15, 12, 22)
    )
)