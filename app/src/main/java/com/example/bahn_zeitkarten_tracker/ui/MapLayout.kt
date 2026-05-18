package com.example.bahn_zeitkarten_tracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
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
import org.w3c.dom.Text
import java.io.File

@Composable
fun MapLayout(
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
        Column(modifier) {
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
    name = "Vorschau HomeLayout",
    showBackground = true,
    device = "id:pixel_9"
)
@Composable //Funktion, dass es MainLayout in Preview anzeigt
fun MapLayoutPreview() {
    MapLayout(
        modifier = Modifier.fillMaxSize()
    )
}