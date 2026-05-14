package com.example.bahn_zeitkarten_tracker.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import classes_and_Functions.createLineGraph
import com.example.bahn_zeitkarten_tracker.R
import com.jjoe64.graphview.GraphView

//layout Main für grundlayout (auf allen seiten gleich)
@Composable
fun MainLayout() {
    Text("Bahn-Zeitkarten-Tracker")
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview() {
    MainLayout()
}