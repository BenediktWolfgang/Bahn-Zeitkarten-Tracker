package classes_and_Functions

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.Date


// Graph Layout Shit:

class VgFahrt (
    val von:    Loc,
    val bis:    Loc,
    val dist:   Int,
    val dayt:   Date = Date(),
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


//TODO: nach Datum sortieren noch
fun createLineGraph(graph: GraphView, entries: List<VgFahrt>, seit: Date) {

    for(j in 1..entries.size) {

    }
    val series = LineGraphSeries(arrayOf(


        DataPoint(0.0, 1.0),
        DataPoint(1.0, 5.0),
        DataPoint(2.0, 3.0),
        DataPoint(3.0, 7.0),
        DataPoint(4.0, 4.0)
    ))

    graph.addSeries(series)

    // Optional settings
    graph.title = "Line Graph"

    graph.viewport.isScalable = true
    graph.viewport.isScrollable = true
}