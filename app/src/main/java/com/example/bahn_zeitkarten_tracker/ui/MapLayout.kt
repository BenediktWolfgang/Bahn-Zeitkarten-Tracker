package com.example.bahn_zeitkarten_tracker.ui

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bahn_zeitkarten_tracker.ui.theme.AppPrimary
import com.example.bahn_zeitkarten_tracker.ui.theme.AppSurface
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextLight
import com.example.bahn_zeitkarten_tracker.ui.theme.AppTextMuted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mapsforge.core.model.LatLong
import org.mapsforge.map.android.graphics.AndroidGraphicFactory
import org.mapsforge.map.android.util.AndroidUtil
import org.mapsforge.map.android.view.MapView
import org.mapsforge.map.layer.cache.TileCache
import org.mapsforge.map.layer.renderer.TileRendererLayer
import org.mapsforge.map.reader.MapFile
import org.mapsforge.map.rendertheme.internal.MapsforgeThemes

import java.io.File

@Composable
fun MapLayout( //Grundlayout
    modifier: Modifier = Modifier
){
    //Speicher von Eingabefeld
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }

    //Default
    val distanceText = if (from.isNotBlank() && to.isNotBlank()) {
        "Distanz: wird berechnet..."
    } else {
        "Distanz: Start und Ziel eingeben"
    } //TODO: noch ändern!

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) //Platz zwischen elementen
    ) {
        FahrtInput(
            from = from,
            to = to,
            onFromChange = { from = it },
            onToChange = { to = it }
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .offset(y=40.dp)
        ){
            //Karte einbinden
            MapLayoutKarte(
                modifier = Modifier.fillMaxSize()
                )
        }

        Text( //TODO: berechnung einbinden
            text = distanceText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        val aktiv = from.isNotBlank() && to.isNotBlank() //aktiver Button oder nicht

        Button( //fahrt loggen
            onClick = {
                // TODO: Fahrt loggen PopUp
            },
            enabled = aktiv,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors( //zuerst grau; dann farbig
                contentColor = AppTextLight,
                disabledContentColor = AppTextLight,
                containerColor = AppPrimary,
                disabledContainerColor = AppTextMuted
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "+ Fahrt loggen",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FahrtInput( //eingabefelder
    from: String,
    to: String,
    onFromChange: (String) -> Unit,
    onToChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = from,
            onValueChange = onFromChange,
            label = {
                Text("Von")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true //nur eine zeile
        )

        OutlinedTextField(
            value = to,
            onValueChange = onToChange,
            label = {
                Text("Bis")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}


@Composable
fun MapLayoutKarte( //Layout für aktuelle Karte
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mapFile = remember {File(context.filesDir, "berlin.map")}
    var isReady by remember { mutableStateOf(mapFile.exists()) }

    LaunchedEffect(Unit) {
        if (!mapFile.exists()) {
            withContext(Dispatchers.IO) {
                downloadMapFile("https://ftp-stud.hs-esslingen.de/pub/Mirrors/download.mapsforge.org/maps/v5/europe/germany/berlin.map", mapFile)
            }
            isReady = true
        }
    }

    if (mapFile.exists()) {
        MapsforgeMap(mapFile, modifier)
    } else {
        Column(
            modifier = modifier
                .background(
                    color = AppSurface,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    border = BorderStroke(1.dp, AppTextMuted),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Karte")
            Text("Karte wird nicht gefunden in :")
            Text(context.filesDir.toString())
            if (AndroidGraphicFactory.INSTANCE == null)
                Text("Also: AndroidGraphicFactory wird nicht instanziert.")
        }
    }
}

@Composable
fun MapsforgeMap(
    mapFile: File,
    modifier: Modifier = Modifier,
    initialPosition: LatLong = LatLong(52.518, 13.408),
    initialZoom: Byte = 12
) {
    val context = LocalContext.current
    var tileCache: TileCache? by remember { mutableStateOf(null) }
    var tileRendererLayer: TileRendererLayer? by remember {mutableStateOf(null)}

    AndroidView( modifier = modifier,
        factory = {
            ctx ->
            MapView(ctx).apply {
                //initial settings setup
                isClickable = true
                mapScaleBar.isVisible = true
                setBuiltInZoomControls(true)

                model.mapViewPosition.setCenter(initialPosition)
                model.mapViewPosition.zoomLevel = initialZoom

                val cache = AndroidUtil.createTileCache(
                    ctx, "tilecache", model.displayModel.tileSize, 1f, model.frameBufferModel.overdrawFactor
                )
                tileCache = cache

                val rendererLayer = TileRendererLayer(
                    cache, MapFile(mapFile), model.mapViewPosition, AndroidGraphicFactory.INSTANCE
                ).apply { setXmlRenderTheme(MapsforgeThemes.DEFAULT) }
                tileRendererLayer = rendererLayer

                layerManager.layers.add(rendererLayer)
            }
        })

    DisposableEffect(Unit) {
        onDispose {
            tileRendererLayer?.onDestroy()
            tileCache?.destroy()
        }
    }

}

suspend fun downloadMapFile(url: String, destination: File) {
    val connection = java.net.URL(url).openConnection() as java.net.HttpURLConnection
    connection.inputStream.use { input ->
        destination.outputStream().use { output ->
            input.copyTo(output)
        }
    }
}


@Preview( //vorschau in Android studio
    name = "Vorschau MapLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun MapLayoutPreview() {
    MapLayout(
        modifier = Modifier.fillMaxSize()
    )
}