package com.example.bahn_zeitkarten_tracker

import android.os.Bundle
import java.util.Date
import java.time.ZoneId
import java.time.temporal.ChronoUnit


//für ui
import androidx.activity.ComponentActivity

//Funktionen und Klassenimporte

import classes_and_Functions.*
//Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

