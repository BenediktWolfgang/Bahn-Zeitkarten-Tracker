package classes_and_Functions

import android.R.id.message
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import classes_and_Functions.VgFahrt
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//gibt Anzahl an zusätzlichen Fahrten wieder die man zurücklegen muss um den Preis
// seiner Zeitkarte wieder drinne zu haben.
fun BreakEvenSim(Fahrten: List<VgFahrt>, Karte: Zeitkarten): Double {

    val avg = AvgPrice(Fahrten)
    val sum = SumPrice(Fahrten)

    return if (sum > Karte.Preis)
        0.0

    else
        Karte.Preis/avg.toDouble()

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

// Basic Function Scaffolding kopiert von
// https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-snackbar.html
@Composable
fun DspPopupMSG(text: String) {

// State for displaying Snackbar and tracking actions
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var actionPerformed by remember { mutableStateOf(false) }

// Use BoxWithConstraints to create a stable layout
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // Main content box that won't be affected by Snackbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight - 80.dp) // Reserve space for Snackbar
                .align(Alignment.TopCenter)
        ) {
            // Column to center our button vertically
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Use stable fixed width container for button
                Box(
                    modifier = Modifier.width(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                actionPerformed = false
                                val result = snackbarHostState.showSnackbar(
                                    message = text,
                                    duration = SnackbarDuration.Long
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    actionPerformed = true
                                }
                            }
                        }
                    ) {
                        Text("Show Snackbar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Fixed size box for action message
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (actionPerformed) {
                        Text(
                            text = "Action performed!",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        // Separate box for Snackbar with fixed height at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
        ) {
            // SnackbarHost positioned at the bottom
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp),
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
}