package com.example.bahn_zeitkarten_tracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
//import androidx.activity.EdgeToEdge -> veraltet
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.bahn_zeitkarten_tracker.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Constructor
import java.util.Date
import java.time.ZoneId
import java.time.temporal.ChronoUnit

//für ui
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.KeyEventDispatcher
import com.example.bahn_zeitkarten_tracker.ui.MainLayout


//Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
class loc{

}

class Zeitkarten (
    val name:       String,
    val firma:      String?,
    val link:       String?,
    val giltv:      Date,
    val giltb:      Date

    // giltvon und giltbis sind inklusive, d.h. deren Datum ist noch in der Gültigkeit inkludiert
    // alle Daten sind vom User selbst bestimmbar.
)

class VgFahrt (
    val von:    loc,
    val bis:    loc,
    val dist:   Int,
    val dayt:   Date?,
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

// Funktion nimmt zwei Datumswerte auf und gibt die verbleibende Dauer an Tagen wieder, die zwischen
// den Datumswerten verbleibt.
fun RemainingTime(a: Date, b: Date): Int {
    val da = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val db = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val x = ChronoUnit.DAYS.between(da, db)
    return x.toInt()
}

