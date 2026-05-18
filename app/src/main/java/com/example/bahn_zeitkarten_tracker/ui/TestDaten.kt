package com.example.bahn_zeitkarten_tracker.ui

import classes_and_Functions.VgFahrt
import classes_and_Functions.Zeitkarte
import java.time.LocalDate
import java.time.LocalDateTime

// Demo Zeitkarten: von KI erstellen lassen
// Preisquellen:
// Klimaticket Ö: https://de.wikipedia.org/wiki/Klimaticket
// Wiener Linien Monatskarte: 467€/Jahr = ~38.92€/Monat (Stand 2026)
val zeitkarten = listOf(
    Zeitkarte( // index 0 - Klimaticket 2025 (abgelaufen, Break-Even erreicht)
        name = "Klimaticket Österreich",
        preis = 117930, // 1179.30€ (Preis 2025)
        firma = "ÖBB",
        link = "",
        giltv = LocalDate.of(2025, 1, 1),
        giltb = LocalDate.of(2025, 12, 31)
    ),
    Zeitkarte( // index 1 - Wiener Linien Monatskarte Feb 2026 (~70% rentiert)
        name = "Wiener Linien Monatskarte",
        preis = 3892, // 38.92€ (467€/12)
        firma = "Wiener Linien",
        link = "",
        giltv = LocalDate.of(2026, 2, 1),
        giltb = LocalDate.of(2026, 2, 28)
    ),
    Zeitkarte( // index 2 - Klimaticket 2026 (aktiv, Break-Even noch nicht erreicht)
        name = "Klimaticket Österreich",
        preis = 140000, // 1400€ (Preis ab Jänner 2026)
        firma = "ÖBB",
        link = "",
        giltv = LocalDate.of(2026, 3, 1),
        giltb = LocalDate.of(2027, 2, 28)
    )
)

// Demo Fahrten
// Klimaticket 2025: Summe 118100 Cent > 117930 -> Break-Even erreicht ✅
// Monatskarte Feb 2026: Summe 2760 Cent = ~70.9% von 3892 ✅
// Klimaticket 2026: Summe 42900 Cent = ~30.6% von 140000 ✅
val vgfahrten = listOf(

    // --- Klimaticket Österreich 2025 (44 Fahrten, Break-Even erreicht) ---

    // Jänner 2025
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2025, 1, 6, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2025, 1, 8, 17, 30), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2025, 1, 20, 9, 0), zeitkarte = zeitkarten[0]),

    // Februar 2025
    VgFahrt(von = "Graz", bis = "Wien", dist = 2000,
        dayt = LocalDateTime.of(2025, 2, 3, 16, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Innsbruck", dist = 5000,
        dayt = LocalDateTime.of(2025, 2, 14, 7, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Innsbruck", bis = "Wien", dist = 5000,
        dayt = LocalDateTime.of(2025, 2, 16, 18, 0), zeitkarte = zeitkarten[0]),

    // März 2025
    VgFahrt(von = "Wien", bis = "Linz", dist = 1900,
        dayt = LocalDateTime.of(2025, 3, 5, 10, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Linz", bis = "Salzburg", dist = 1300,
        dayt = LocalDateTime.of(2025, 3, 6, 11, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2025, 3, 10, 15, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Bregenz", dist = 6500,
        dayt = LocalDateTime.of(2025, 3, 22, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Bregenz", bis = "Wien", dist = 6500,
        dayt = LocalDateTime.of(2025, 3, 25, 17, 0), zeitkarte = zeitkarten[0]),

    // April 2025
    VgFahrt(von = "Wien", bis = "Bregenz", dist = 6500,
        dayt = LocalDateTime.of(2025, 4, 2, 6, 30), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Bregenz", bis = "Wien", dist = 6500,
        dayt = LocalDateTime.of(2025, 4, 5, 14, 0), zeitkarte = zeitkarten[0]),

    // Mai 2025
    VgFahrt(von = "Wien", bis = "Klagenfurt", dist = 3200,
        dayt = LocalDateTime.of(2025, 5, 3, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Innsbruck", dist = 5000,
        dayt = LocalDateTime.of(2025, 5, 3, 7, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Innsbruck", bis = "Wien", dist = 5000,
        dayt = LocalDateTime.of(2025, 5, 5, 16, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Klagenfurt", bis = "Graz", dist = 1400,
        dayt = LocalDateTime.of(2025, 5, 12, 13, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Graz", bis = "Wien", dist = 2000,
        dayt = LocalDateTime.of(2025, 5, 15, 17, 0), zeitkarte = zeitkarten[0]),

    // Juni 2025
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2025, 6, 7, 9, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Salzburg", bis = "Innsbruck", dist = 1800,
        dayt = LocalDateTime.of(2025, 6, 8, 11, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Innsbruck", bis = "Wien", dist = 5000,
        dayt = LocalDateTime.of(2025, 6, 12, 16, 0), zeitkarte = zeitkarten[0]),

    // Juli 2025
    VgFahrt(von = "Wien", bis = "Bregenz", dist = 6500,
        dayt = LocalDateTime.of(2025, 7, 5, 7, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Bregenz", bis = "Innsbruck", dist = 5500,
        dayt = LocalDateTime.of(2025, 7, 10, 12, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Innsbruck", bis = "Wien", dist = 5500,
        dayt = LocalDateTime.of(2025, 7, 14, 15, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2025, 7, 20, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2025, 7, 22, 17, 0), zeitkarte = zeitkarten[0]),

    // August 2025
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2025, 8, 4, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Graz", bis = "Klagenfurt", dist = 1400,
        dayt = LocalDateTime.of(2025, 8, 5, 12, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Klagenfurt", bis = "Wien", dist = 3200,
        dayt = LocalDateTime.of(2025, 8, 10, 15, 0), zeitkarte = zeitkarten[0]),

    // September 2025
    VgFahrt(von = "Wien", bis = "Bregenz", dist = 6500,
        dayt = LocalDateTime.of(2025, 9, 3, 9, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2025, 9, 3, 9, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Bregenz", bis = "Salzburg", dist = 2800,
        dayt = LocalDateTime.of(2025, 9, 15, 13, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Graz", bis = "Wien", dist = 2000,
        dayt = LocalDateTime.of(2025, 9, 5, 18, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Salzburg", bis = "Graz", dist = 500,
        dayt = LocalDateTime.of(2025, 9, 17, 10, 0), zeitkarte = zeitkarten[0]),

    // Oktober 2025
    VgFahrt(von = "Wien", bis = "Linz", dist = 1900,
        dayt = LocalDateTime.of(2025, 10, 3, 9, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Linz", bis = "Wien", dist = 1900,
        dayt = LocalDateTime.of(2025, 10, 4, 18, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2025, 10, 20, 7, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Linz", dist = 1900,
        dayt = LocalDateTime.of(2025, 10, 15, 10, 0), zeitkarte = zeitkarten[0]),

    // November 2025
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2025, 11, 3, 17, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Innsbruck", dist = 5000,
        dayt = LocalDateTime.of(2025, 11, 15, 8, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Innsbruck", bis = "Salzburg", dist = 1800,
        dayt = LocalDateTime.of(2025, 11, 17, 14, 0), zeitkarte = zeitkarten[0]),

    // Dezember 2025
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2025, 12, 1, 16, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2025, 12, 20, 10, 0), zeitkarte = zeitkarten[0]),
    VgFahrt(von = "Graz", bis = "Wien", dist = 2000,
        dayt = LocalDateTime.of(2025, 12, 27, 15, 0), zeitkarte = zeitkarten[0]),

    // --- Wiener Linien Monatskarte Februar 2026 (~70% rentiert, 8 Fahrten) ---
    VgFahrt(von = "Wien Westbahnhof", bis = "Wien Hauptbahnhof", dist = 380,
        dayt = LocalDateTime.of(2026, 2, 2, 8, 0), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Hauptbahnhof", bis = "Wien Meidling", dist = 370,
        dayt = LocalDateTime.of(2026, 2, 4, 17, 30), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Meidling", bis = "Wien Floridsdorf", dist = 360,
        dayt = LocalDateTime.of(2026, 2, 7, 9, 0), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Floridsdorf", bis = "Wien Westbahnhof", dist = 350,
        dayt = LocalDateTime.of(2026, 2, 10, 18, 0), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Hauptbahnhof", bis = "Wien Floridsdorf", dist = 340,
        dayt = LocalDateTime.of(2026, 2, 13, 8, 30), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Westbahnhof", bis = "Wien Meidling", dist = 330,
        dayt = LocalDateTime.of(2026, 2, 17, 16, 0), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Meidling", bis = "Wien Hauptbahnhof", dist = 320,
        dayt = LocalDateTime.of(2026, 2, 21, 9, 0), zeitkarte = zeitkarten[1]),
    VgFahrt(von = "Wien Hauptbahnhof", bis = "Wien Westbahnhof", dist = 310,
        dayt = LocalDateTime.of(2026, 2, 25, 17, 0), zeitkarte = zeitkarten[1]),

    // --- Klimaticket Österreich 2026 (15 Fahrten, ~30% rentiert) ---
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2026, 3, 5, 8, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2026, 3, 7, 17, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2026, 3, 20, 9, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Graz", bis = "Wien", dist = 2000,
        dayt = LocalDateTime.of(2026, 3, 22, 16, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Linz", dist = 1900,
        dayt = LocalDateTime.of(2026, 4, 3, 10, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Linz", bis = "Wien", dist = 1900,
        dayt = LocalDateTime.of(2026, 4, 4, 18, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Innsbruck", dist = 5000,
        dayt = LocalDateTime.of(2026, 4, 18, 7, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Innsbruck", bis = "Wien", dist = 5000,
        dayt = LocalDateTime.of(2026, 4, 20, 16, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Klagenfurt", dist = 3200,
        dayt = LocalDateTime.of(2026, 5, 2, 8, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Klagenfurt", bis = "Wien", dist = 3200,
        dayt = LocalDateTime.of(2026, 5, 4, 17, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Salzburg", dist = 3000,
        dayt = LocalDateTime.of(2026, 5, 11, 15, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Graz", dist = 2000,
        dayt = LocalDateTime.of(2026, 5, 15, 12, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Graz", bis = "Salzburg", dist = 2800,
        dayt = LocalDateTime.of(2026, 5, 16, 10, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Salzburg", bis = "Wien", dist = 3000,
        dayt = LocalDateTime.of(2026, 5, 17, 14, 0), zeitkarte = zeitkarten[2]),
    VgFahrt(von = "Wien", bis = "Linz", dist = 1900,
        dayt = LocalDateTime.of(2026, 5, 18, 9, 0), zeitkarte = zeitkarten[2])
)