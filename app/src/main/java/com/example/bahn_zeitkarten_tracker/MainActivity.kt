package com.example.bahn_zeitkarten_tracker

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Date
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import androidx.activity.compose.setContent
import androidx.core.view.KeyEventDispatcher

//für ui
import com.example.bahn_zeitkarten_tracker.ui.MainLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import org.mapsforge.map.android.graphics.AndroidGraphicFactory

//Funktionen und Klassenimporte

//import classes_and_Functions.*
//import com.example.bahn_zeitkarten_tracker.databinding.ActivityMainBinding
//import com.jjoe64.graphview.GraphView


//Main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create graphics factory for mapsforge to display maps
        AndroidGraphicFactory.createInstance(this)

        //ui einbinden
        setContent {
            MainLayout()
        }

    }
}

fun al(){


}


// Funktion nimmt zwei Datumswerte auf und gibt die verbleibende Dauer an Tagen wieder, die zwischen
// den Datumswerten verbleibt.
fun RemainingTime(a: Date, b: Date): Int {
    val da = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val db = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val x = ChronoUnit.DAYS.between(da, db)
    return x.toInt()
}

