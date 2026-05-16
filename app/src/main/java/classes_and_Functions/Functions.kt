package classes_and_Functions

import classes_and_Functions.VgFahrt
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//gibt Anzahl an zusätzlichen Fahrten wieder die man zurücklegen muss um den Preis
// seiner Zeitkarte wieder drinne zu haben.
fun BreakEvenSim(Fahrten: List<VgFahrt>, Karte: Zeitkarten): Double {

    val avg = AvgPrice(Fahrten)
    val sum = SumPrice(Fahrten)

    if (sum > Karte.Preis)
        return 0.0

    else
        return Karte.Preis/avg.toDouble()

    return -1.0
}

//returns average Price of a List of past Travels
fun AvgPrice(Fahrten : List<VgFahrt>): Int {

    return Fahrten.map{it.price}.average().toInt()

}

//returns summed Price of a List of past Travels
fun SumPrice(Fahrten : List<VgFahrt>): Int {

    return Fahrten.map{it.price}.sum()

}

fun converttoDate(futuredate: Int): LocalDate {

    val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")

    val date = LocalDate.parse(
        futuredate.toString(),
        formatter
    )

    return date

}