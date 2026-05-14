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
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal


// Graph Layout Shit:

class VgFahrt (
    val von:    Loc,
    val bis:    Loc,
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


//TODO: beschriften und schöner machen
//Diese Funktion erstellt einen Graphen und stellt den dann dar,
fun createLineGraph(graph: GraphView,
                    entries: List<VgFahrt>,
                    seit: LocalDateTime,
                    ZeitkartenPreis: Double
    ) {

    graph.removeAllSeries()
    val series = LineGraphSeries(

        entries.map { entries ->

            DataPoint(

                 ChronoUnit.HOURS.between(seit,entries.dayt).toDouble(),
                 ZeitkartenPreis

            )

        }.toTypedArray()

    )

    var runningSum = 0.0

    val series2 = LineGraphSeries(

        entries.map { entries ->

            runningSum += entries.price.toDouble()

            DataPoint(

                ChronoUnit.HOURS.between(seit,entries.dayt).toDouble(),
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