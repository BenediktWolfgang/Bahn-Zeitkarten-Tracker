package classes_and_Functions

import classes_and_Functions.VgFahrt

//returns average Price of a List of past Travels
fun AvgPrice(Fahrten : List<VgFahrt>): Int{
    return Fahrten.map{it.price}.average().toInt()
}

