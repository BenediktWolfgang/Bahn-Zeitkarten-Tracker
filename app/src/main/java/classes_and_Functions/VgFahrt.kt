package classes_and_Functions

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.Date
//import kotlin.time.Duration
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal


// Graph Layout Shit:

class VgFahrt (
    val von:    String,//Loc,
    val bis:    String, //Loc,
    val dist:   Int,
    val dayt:   LocalDateTime = LocalDateTime.now(),
    val co2:    Double = dist*0.0174,
    val price:  Int = CalcPrice(dist)

    // Price is saved in Cents
    // distance is saved as 1/10 of a km so we can display it with a, without wasting space with double
    // dayt saves the time the journey officially started
    // Co2 saves kg of Co2 saved
    // Co2 Ersparnis Quelle: https://personenverkehr.oebb.at/de/pv-ag/nachhaltigkeit/nachhaltigkeit
    //
)

// Diese Funktion nimmt die Distanz der Strecke in Zehntel eines km auf und
// konvertiert sie zu Preis in Cent. Muss noch anpassbar gemacht werden für Leute die sich einen
// anderen Durchscnittswert erwarten, da dieser serh variieren kann von Sparschienen-Preis von
// 4 Cent pro km zu Buchungen am selben Tag die auch schon 30 Cent pro km kosten können.
// So Wenn mit 1 multipliziert wird wird Standart Wert von 10 Cent pro km angenommen
fun CalcPrice(dist: Int): Int{
    return dist
}


//TODO: beschriften und schöner machen -> nope können wir nicht verwenden wg Compose...
//Diese Funktion erstellt einen Graphen und stellt den dann dar.
// Einen Graphview muss man zuerst erstellen und mitgeben, dann erstellt es in diesem GraphView die
// x und y Achse basierend auf den addedSeries. Eine Linie ist die gerade Linie des ZeitkartenPreises
// dessen y-Wert über die Zeit gleich bleibt, die andere Linie ist der sich aufsummierende
// Wert von den Vergangenen Fahrten, die man dann mit dem Zeitkartenpreis graphisch vergeleichen
// kann. Außerdem kann man Fahrten limitieren basierend auf der Zeit mit seit.
// Bei ShowCo2 zeigt es die Co2 Ersparnisse die man über die Zeit gemacht hat.
// ZeitkartenPreis soll nur dann nicht benutzt werden wenn man Co2 mappt
fun createLineGraph(graph: GraphView, entries: List<VgFahrt>, seit: LocalDateTime,
                    ZeitkartenPreis: Double = 0.0, showCo2: Boolean = false) {

    val entries2 = limitbytime(entries, seit)
    graph.removeAllSeries()
    val series = LineGraphSeries(

        entries2.map { entries ->

            DataPoint(

                 ChronoUnit.HOURS.between(seit,entries.dayt)
                     .toDouble(),
                 ZeitkartenPreis

            )

        }.toTypedArray()

    )
    if(showCo2) graph.removeAllSeries()

    var runningSum = 0.0

    val series2 = LineGraphSeries(

        entries2.map { entries ->

            if(!showCo2)
                runningSum += entries.price.toDouble()

            else
                runningSum += entries.co2

            DataPoint(

                ChronoUnit.HOURS.between(seit,entries.dayt)
                    .toDouble(),
                runningSum

                )

        }.toTypedArray()

    )

    graph.addSeries(series)
    graph.addSeries(series2)

    // Optional settings
    graph.title = "Preisvergleich"

    graph.viewport.isScalable = true
    graph.viewport.isScrollable = true
}


//returns entries of VgFahrt nach einem bestimmten Datum
private fun limitbytime(entries: List<VgFahrt>, seit: LocalDateTime): List<VgFahrt> {

    return entries.filter{ it.dayt.isAfter(seit)}

}