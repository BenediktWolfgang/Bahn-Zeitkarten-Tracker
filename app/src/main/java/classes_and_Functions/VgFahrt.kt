package classes_and_Functions

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
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


class VgFahrt (
    val von:    String,//Loc,
    val bis:    String, //Loc,
    val dist:   Int,
    val dayt:   LocalDateTime = LocalDateTime.now(),
    val zeitkarte:  Zeitkarte? = null, //zuordnung zu Zeitkarte mit der gefahren wird
    val co2:    Double = dist*0.000571,  // dist in 1/10 km -> /10 * 5.71g -> in kg /1000
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
//Diese Funktion erstellt einen Graphen und stellt den dann dar.
// Einen Graphview muss man zuerst erstellen und mitgeben, dann erstellt es in diesem GraphView die
// x und y Achse basierend auf den addedSeries. Eine Linie ist die gerade Linie des ZeitkartenPreises
// dessen y-Wert über die Zeit gleich bleibt, die andere Linie ist der sich aufsummierende
// Wert von den Vergangenen Fahrten, die man dann mit dem Zeitkartenpreis graphisch vergeleichen
// kann. Es werden etweder die Fahrten für die Dauer der Zeitkarte angezeit oder von der Ersten bis zur letzten Fahrt (seit, bis).
// Bei ShowCo2 zeigt es den Co2 Verbrauch den man über die Zeit gemacht hat von Zug und Auto
// ZeitkartenPreis soll nur dann nicht benutzt werden wenn man Co2 mappt

// ! XML Graph in Compose eingebunden
// Dafür KI befragt, wie das geht (siehe KI Nutzung)

fun createLineGraph(
    graph: GraphView,
    entries: List<VgFahrt>,
    seit: LocalDateTime,
    bis: LocalDateTime,
    ZeitkartenPreis: Double = 0.0,
    showCo2: Boolean = false
) {
    val entries2 = limitbytime(entries, seit).sortedBy { it.dayt }
    if (entries2.isEmpty()) return

    graph.removeAllSeries()

    // X Achse: Tage Dauer der Zeitkarte
    val maxX = ChronoUnit.DAYS.between(seit, bis).toDouble()

    //weil Graph zu breit bzw nicht schön angezeigt wurde (von KI vorschläge geben lassen -> dieser hat funktioniert)
    graph.viewport.isXAxisBoundsManual = true
    graph.viewport.setMinX(0.0)
    graph.viewport.setMaxX(maxX)
    graph.viewport.isYAxisBoundsManual = false

    val maxFahrtkosten = entries2.sumOf { it.price.toDouble() / 100.0 }
    val maxY = if (showCo2) {
        entries2.sumOf { it.dist / 10.0 * 0.12 } * 1.2
    } else {
        maxOf(ZeitkartenPreis / 100.0, maxFahrtkosten) * 1.2
    }
    graph.viewport.setMaxY(maxY)


    // Linie 1: Zeitkartenpreis - gerade horizontale Linie
    val series = LineGraphSeries(
        arrayOf(
            DataPoint(0.0, ZeitkartenPreis / 100.0),  // Startpunkt
            DataPoint(maxX, ZeitkartenPreis / 100.0)   // Endpunkt
        )
    )
    series.color = android.graphics.Color.BLUE

    // Linie 2: aufsummierte Fahrtkosten oder Zug CO2
    var runningSum = 0.0
    val series2 = LineGraphSeries(
        entries2.map { entry ->
            if (!showCo2) runningSum += entry.price.toDouble() / 100.0
            else runningSum += entry.co2
            DataPoint(ChronoUnit.DAYS.between(seit, entry.dayt).toDouble(), runningSum)
        }.toTypedArray()
    )
    series2.color = android.graphics.Color.GREEN

    // Linie 3: Auto CO2 (nur bei CO2 Ansicht)
    var runningSumCar = 0.0
    val series3 = LineGraphSeries(
        entries2.map { entry ->
            runningSumCar += entry.dist / 10.0 * 0.2173 //Auto CO2 in kg
            DataPoint(ChronoUnit.DAYS.between(seit, entry.dayt).toDouble(), runningSumCar)
        }.toTypedArray()
    )
    series3.color = android.graphics.Color.RED

    // Wann wird was gezeichnet:
    if (!showCo2) {
        graph.addSeries(series)   // Zeitkartenpreis Linie - nur BreakEven
        graph.addSeries(series2)  // Fahrtkosten - nur BreakEven
    } else {
        graph.addSeries(series2)  // Zug CO2 - nur CO2 Ansicht
        graph.addSeries(series3)  // Auto CO2 - nur CO2 Ansicht
    }

    // Achsenbeschriftungen, je nach Anzeige
    graph.gridLabelRenderer.horizontalAxisTitle = if (showCo2) "Tage" else "Dauer der Zeitkarte"
    graph.gridLabelRenderer.verticalAxisTitle = if (showCo2) "CO2 (kg)" else "Preis (€)"

    //Graph wurde vorher nicht schön angezeigt - KI
    graph.gridLabelRenderer.horizontalAxisTitleColor = android.graphics.Color.BLACK
    graph.gridLabelRenderer.verticalAxisTitleColor = android.graphics.Color.BLACK
    graph.gridLabelRenderer.labelsSpace = 10
    graph.gridLabelRenderer.textSize = 28f

    graph.gridLabelRenderer.gridColor = android.graphics.Color.GRAY
    graph.gridLabelRenderer.isHighlightZeroLines = true
    graph.gridLabelRenderer.horizontalAxisTitleColor = android.graphics.Color.BLACK
    graph.gridLabelRenderer.verticalAxisTitleColor = android.graphics.Color.BLACK
    graph.gridLabelRenderer.horizontalLabelsColor = android.graphics.Color.BLACK
    graph.gridLabelRenderer.verticalLabelsColor = android.graphics.Color.BLACK

    graph.viewport.isScalable = true
    graph.viewport.isScrollable = true
}


//returns entries of VgFahrt ab einem bestimmten Datum
private fun limitbytime(entries: List<VgFahrt>, seit: LocalDateTime): List<VgFahrt> {

    return entries.filter{ !it.dayt.isBefore(seit)}

}

fun validateinputs(von: String, bis: String, dist: Int, errormsg: (String) -> Unit): Boolean {
    return when {
        von.isBlank() -> {errormsg("Bitte Startpunkt eingeben"); false}
        bis.isBlank() -> {errormsg("Bitte Endpunkt eingeben"); false}
        dist == 0 -> {errormsg("Bitte Distanz eingeben"); false}
        else -> true

    }
}
