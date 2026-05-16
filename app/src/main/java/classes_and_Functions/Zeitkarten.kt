package classes_and_Functions

import java.util.Date

class Zeitkarten (
    val name:       String,
    val Preis:      Int,
    val firma:      String?,
    val link:       String?,
    val giltv: Date,
    val giltb: Date

    // giltvon und giltbis sind inklusive, d.h. deren Datum ist noch in der Gültigkeit inkludiert
    // alle Daten sind vom User selbst bestimmbar.
)