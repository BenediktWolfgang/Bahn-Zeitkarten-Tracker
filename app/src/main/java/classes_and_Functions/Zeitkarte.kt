package classes_and_Functions

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Zeitkarte (
    val name:       String,
    val preis:      Int,
    val firma:      String?,
    val link:       String?,
    val giltv: LocalDate,
    val giltb: LocalDate

    // giltvon und giltbis sind inklusive, d.h. deren Datum ist noch in der Gültigkeit inkludiert
    // alle Daten sind vom User selbst bestimmbar.
    //Preis safed in cents.

){
    fun giltNochTage(): Int {
        //Anzahl tage von heute bis gültigbis
        val tage = ChronoUnit.DAYS.between(LocalDate.now(), giltb).toInt()
        return if (tage < 0) 0 else tage//max null -> nicht negativ

        //berechnet Tage die das Ticket noch gültig ist
    }


    fun giltNochProzent(): Float {

        val gesamteTage = ChronoUnit.DAYS.between(giltv, giltb).toFloat()
        val verbleibendeTage = ChronoUnit.DAYS.between(LocalDate.now(), giltb).toFloat()

        if (gesamteTage <= 0f) return 0f

        return (verbleibendeTage / gesamteTage).coerceIn(0f, 1f)
    //Wert zwischen 100% und 0% -> damit nicht größer als 100 oder kleiner als 0
    //Berechnet Prozent wie lange das Ticket noch gültig ist (für darstellung bei Meine Zeitkarten)
    }
}