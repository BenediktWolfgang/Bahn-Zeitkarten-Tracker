package com.example.bahn_zeitkarten_tracker

import android.os.Bundle
import java.util.Date
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import androidx.activity.compose.setContent

//für ui
import com.example.bahn_zeitkarten_tracker.ui.MainLayout
import androidx.appcompat.app.AppCompatActivity
import org.mapsforge.map.android.graphics.AndroidGraphicFactory

//Main
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        supportActionBar?.hide() //schwarzen Header ausblenden
        //create graphics factory for mapsforge to display maps
        AndroidGraphicFactory.createInstance(this)
        //aus KI, weil es nach dem commit mit der Map eine seperaten header gab

        //ui einbinden
        setContent { //ui "einbinden"
            MainLayout()
        }
    }
}



// Funktion nimmt zwei Datumswerte auf und gibt die verbleibende Dauer an Tagen wieder, die zwischen
// den Datumswerten verbleibt.
// nicht verwendet.
fun RemainingTime(a: Date, b: Date): Int {
    val da = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val db = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val x = ChronoUnit.DAYS.between(da, db)
    return x.toInt()
}
