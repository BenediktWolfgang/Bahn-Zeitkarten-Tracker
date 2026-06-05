package classes_and_Functions


import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


//gibt Anzahl an zusätzlichen Fahrten wieder die man zurücklegen muss um den Preis
// seiner Zeitkarte wieder drinne zu haben.
fun BreakEvenSim(Fahrten: List<VgFahrt>, Karte: Zeitkarte): Double {

    val avg = AvgPrice(Fahrten)
    val sum = SumPrice(Fahrten)

    return if (sum > Karte.preis)
        0.0

    else
        Karte.preis/avg.toDouble()

}

//returns average Price of a List of past Travels
fun AvgPrice(Fahrten : List<VgFahrt>): Int {

    return Fahrten.map{it.price}.average().toInt()

}

//returns summed Price of a List of past Travels
fun SumPrice(Fahrten : List<VgFahrt>): Int {

    return Fahrten.map{it.price}.sum()

}

//returns number of past Travels from a List of past Travels
fun SumRides(fahrten : List<VgFahrt>):Int{
    return fahrten.size
}

//fun converttoDate(futuredate: Int): LocalDate {
//
//    val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
//
//    val date = LocalDate.parse(
//        futuredate.toString(),
//        formatter
//    )
//
//    return date
//
//}

//Datum ohne Uhrzeit
fun converttoDate(futuredate: String): LocalDate{
    return try {
        //Crash behebung teils mithilfe von KI
        val clean = futuredate.replace(".", "").replace("/", "").replace("-", "")
        val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        LocalDate.parse(clean, formatter)
    } catch (e: Exception) {
        LocalDate.now()
    }
} //mit String, weil bei Int 1. Null verloren gehen würde


//Datum mit Uhrzeit
fun converttoDateTime(dateinput: String, timeinput: String): LocalDateTime {
    return try {
        val date = converttoDate(dateinput)
        val clean = timeinput.replace(".", "").replace(":", "")
        val timeFormatter = DateTimeFormatter.ofPattern("HHmm")
        val time = LocalTime.parse(clean, timeFormatter)
        LocalDateTime.of(date, time)
    }catch(e:Exception) {
        LocalDateTime.now()
    }
}
fun formatDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatter)
}

// Basic Function kopiert von
// https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-snackbar.html
// danach leicht abgeändert um es meinen Bedürfnissen anzupassen
@Composable
fun DspPopupMSG(text: String, onDismiss: () -> Unit = {}) {

// State for displaying Snackbar and tracking actions
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var actionPerformed by remember { mutableStateOf(false) }

    LaunchedEffect(text) {
        snackbarHostState.showSnackbar(
            message = text,
            duration = SnackbarDuration.Long
        )
        onDismiss()
    }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            // SnackbarHost positioned at the bottom
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 10.dp),
                snackbar = { data ->
                    Snackbar(
                        action =  null,
                        dismissAction =
                            {
                                IconButton(onClick = { data.dismiss() }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Dismiss"
                                    )
                                }
                            }

                    ) {
                        Text(data.visuals.message)
                    }
                }
            )
        }
    }

