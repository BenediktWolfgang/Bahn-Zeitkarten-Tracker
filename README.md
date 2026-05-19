## Readme - M3

* Gruppe:	[3]
* Team-Nr.:	304
* Projektthema:	Bahn-Zeitkarten-Tracker

### Implementierung

Framework:	[Android]

API-Version:	[Android API-Level 36]

Geraet, auf dem getestet wurde:
[Pixel 9]
[Pixel 8]
[Medium Phone]

Externe Libraries und Frameworks:
[GraphView: https://github.com/jjoe64/GraphView]
[Mapsforge: https://github.com/mapsforge/mapsforge]

Dauer der Entwicklung:
[22.5 Stunden] Benedikt
[40 Stunden] Marisa
[24 Stunden] Erin
[86,5] Gesamt (inklusive Dokumentation und Vorarbeit)

Weitere Anmerkungen:

die vierte Person ist bei Beginn zu M4 abgesprungen. 

### KI Nutzung:
#### Benedikt:
[Ich habe KI benutzt um einen Überblick über GraphView und seine Funktionen zu bekommen und um diverse Funktionen zu finden, von denen ich sonst nicht gewusst hätte.
Außerdem habe ich für Error-Pop-Ups einfach die gesamte Funktion aus dem Internet kopiert, laut Moodle-Forum war das ok, aber dieser Code wird in der Praxis nicht benutzt, da wir kein Error Catching haben und für Bestätigungen die Zeit leider doch zu kurz war.
DspPopupMSG geholt von https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-snackbar.html
KI-Nutzung hat geholfen, aber musste bei GraphView und bei diversen Elementen von Jetpack Compose durch ein genaues Studieren der Dokumentation ergänzt werden, da
ich sonst nicht den Code verstanden hätte.

Ki-Prompts:
how to integrate GraphView into Kotlin main activity

can I have two setContentViews

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeScreen = createHomeScreen()

        setContentView(R.layout.activity_main)
        val graph = findViewById<GraphView>(R.id.graph)
        createLineGraph(graph)
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val graph = findViewById<GraphView>(R.id.graph)
        createLineGraph(graph)
    }
}

the app starts and instantly closes again

    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
what do these do

how do I declare them in activities

Why doesnt android studio run mainactivity

//<activity android:name=".MainActivity"> is this the file or the class called>

why does my app crash randomly

I have a class made up of a date, an int for price, and an int for distance, what is the most efficient way to extract the date and distance of each entry into a new list?

if Date is not given initialize today code

Date, not date

make parameter in function call optional

if Date is not given initialize a year ago

for loop example

give me a list of all my prompts so I can copy it into txt format

##### User Prompts Export

1. I want to map 2 values of a class to a LineGraphSeries(arrayOf(Datapoints)), how do I do that

2. Do Datapoints need to be double

3. int to double

4. how can I do math with date values

5. can I cast a Date value as Temporal?

6. how do I convert Duration to double

7. duration.toMillis().toDouble() is Date!, not double

8. What does Duration.between return

9. fun createLineGraph(graph: GraphView, entries: List<VgFahrt>, seit: LocalDateTime) {

   val series = LineGraphSeries(

   ```
    entries.map { entries ->

        val dauer = Duration.between(seit,entries.dayt)
        val stunden = dauer.toMinutes()
        DataPoint(

            y = entries.price.toDouble(),
            x = stunden.toDouble()
        )

    }.toTypedArray()
   ```

   )

10. brauch ich hier noch was?
    @Composable
    fun createLineGraph(graph: GraphView, entries: List<VgFahrt>, seit: LocalDateTime) {

    val series = LineGraphSeries(

    ```
    entries.map { entries ->

        DataPoint(

             ChronoUnit.HOURS.between(seit,entries.dayt).toDouble(),
             entries.price.toDouble(),
    ```
)

graph.addSeries(series)

// Optional settings graph.title = "Line Graph"

graph.viewport.isScalable = true graph.viewport.isScrollable = true }

y = entries.price.toDouble(), how to make this a function that also sums the values of all previous prices
brauch ich hier noch einen return value?? @Composable fun createLineGraph(graph: GraphView, entries: List, seit: LocalDateTime) { val series = LineGraphSeries( entries.map { entries -> DataPoint( ChronoUnit.HOURS.between(seit,entries.dayt).toDouble(), entries.price.toDouble(), ) }.toTypedArray() ) graph.addSeries(series) // Optional settings graph.title = "Line Graph" graph.viewport.isScalable = true graph.viewport.isScrollable = true }
I want the average of train.price of all class instances how
function to convert int to LocalDate
formatting is ddmmjjjj
only map entries of a list that fulfill certain criteria
make function parameter optional
what this do if(!showCo2) runningSum += entries.price else runningSum += entries.co2.toInt()
why error import androidx.compose.material.icons.Icons
give me a list of all my prompts so I can copy it into txt format
]

#### Marisa:
Ich wollte KI verwenden um mir zuerst einen überblick über Jetpack Compose und die Funktionsweise etc. zu machen. das hat aber nur teilweise funktioniert und die offizielle Dokumentation und das Tutorial auf https://developer.android.com/develop/ui/compose/tutorial?hl=de war hilfreicher.

Ich habe zuerst ChatGPT und gegen ende des Projekts dann Claude befragt. Dabei habe ich bemerkt, dass Claude für Programmierkontext besser geeignet ist. Bei beiden habe ich die Thinking version verwendet, da mir vorkommt, dass dann die Antworten weniger Fehleranfällig sind.

Ich habe hauptsächlich für Fehlermeldungen aber auch für dinge, wo ich nicht mehr weiterwusste und eine Erklärung brauchte KI befragt. grundsätzlich war das sehr hilfreich, da es mir sehr viel Recherchezeit erspart hat. einige Codeteile habe ich direkt kopiert (hoffentlich alle schlussendlich übernommenen im Code markiert). Bei reinen erklärungen habe ich nicht jeden Prompt hier herein kopiert, sondern vor allem die, die sich direkt auf den code etc beziehen.

##### Prompts für ChatGPT:
gehe mit mir durch den design prozess wie in einem tutorial. erkläre mir wofür die einzelnen bausteine sind und wie ich sie verwenden kann. Wie ich den abstand zwischen dingen baue etc. Mache dies schritt für schritt mit so viel erklärung wie nötig und so wenig wie möglich, dass es aber für einen compose beginner mit etwas XML Erfahrung verständlich ist
gehe auch auf folgende dinge ein: (reihenfolge so wie es als tutorial am sinnvollsten ist)
- wie starte ich am besten ?
- wie baue ich das app design auf (verschiedene Dateien, wofür eigene Funktionen etc)
- welche bausteine gibt es in compose, wofür sind sie gut
- was ist sonst noch wichtig.
  (ADD: dieser Prompt war nicht wirklich hilfreich, die Dokumentation auf https://developer.android.com/develop/ui/compose/tutorial?hl=de war hilfreicher)

Wenn ich einen Header mit Burgermenü und Titel und eine Navigation Bar machen will, erstelle ich dann in MainLayout einfach zwei separate Funktionen?

Wenn ich will, dass es nicht unter zeugs vom Testgerät rutscht, was gebe ich da ein?
kann ich text auch machen dass nur großbuchstaben sind ?
ich möchte das navigation selected true rot hinterlegt haben icon und text

Android erkennt die Includes nicht automatisch. Wie kann ich das ändern?

Wie kann ich eine Preview erstellen und was muss ich an die Preview alles übergeben?

[verschiedene Fehlermeldungen] hilf mir diese zu verstehen und gib lösungsansätze

Kann ich die XML-Graphenfunktion irgendwie in Kotlin einbinden, ohne dass ich sie neu schreiben muss?

Es zeigt mir einen schwarzen Header an den ich nicht in Compose erstellt habe wie kann ich den entfernen bzw. wo könnte der sein ?

liste mir hier alle Prompts von mir auf

##### Prompts für Claude 
(bin während des Projekts umgestiegen, da Claude bessere Antworten gibt im programmier Kontext):


Startprompt: Ich hallo, ich programmiere eine App (zur Info: ich arbeite in AndroidStudio und nutze Kotlin und JetpackCompose)
ich habe in MainLayout eine MutableStateofList aber beim übergeben in Home etc eine ArrayList ist das falsch. wie wäre das besser? warum bugt das ?
stell mir fragen falls du mehr information benötigtst."

Ich habe einen graph in meine App inkludiert. den zeigts mir aber nicht an. was ist falsch? was muss ich ändern? [relevante Codeausschnitte hinzugefügt]

analysiere das gesamte projekt warum der graph immer noch nicht schön angezeigt wird (Achsen schwarz, Linien schön sichtbar, etc.) gib mir bescheid welche Code Teile du dafür brauchst [auf nachfrage code teile mitgeschickt]

kannst du mir eine DemoInhalt datei für Zeitkarten und Fahrten machen dass ich sauber testen und demonstrieren kann? also so 2-4 Zeitkarten. gerne 1-3 nicht mehr aktiv. und pro zeitkarte so 10-30 fahrten 1 zeitkarte sonst gern nur ne monatskarte für wien. gib gerne auch vorschläge oder stell fragen die ich noch beantworte bevor du das machst, damit das sinnvoll ist.
Zusatzprompts für diesen:
"Klimaticket ist gut - gerne die aktuell aktive. bitte auch weitere strecken wie Innsbruck-Wien oder Salzburg Graz. muss nicht realistisch sein (also dass iwo der wohnort ist)
standardKalkulationswert behalten
Fahrten verteilt - ja
ja das wär super. am besten die aktive noch nicht und die andere schon (eine davon noch nicht)
btw ich glaub eine aktive reicht. ist realistischer."
"kann auch gern erwachsenen ticket sein. dann ist der preis höher aber bei klimaticket österreich 25 BreakEven erreicht. für Monatskarte mehr fahrten als 3 aber so dass es noch nicht erreicht ist sagen wir so 70% rentiert und das soll zwischen den Klimatickets sein. im Februar 2026 danach Klimaticket 26 davor Klimaticket 25"
"nimm aber den richtigen preis für die klimatickets (bei den fahrten nicht)"

[Logcat Fehlermeldung bei Emulator Crashes] was verursacht den Emulator crash? wie kann ich das beheben ?



#### Erin:
Nach viele Tutorien und Artikeln waren nichts sehr hilfreich; die Mapsforge API hat beschränkte Dokumentation, und Tutorien waren entweder veraltet oder mit KI generiert. Also, ich probierte Claude zu fragen.

Prompts für Claude

When I try to import org.mapsforge.map.android.view.Mapview, I receive an "Unresolved reference 'mapsforge'." error. How can I fix this?

How do I fix a "Failed to resolve: com.github.mapsforge.mapsforge:mapsforge-map:0.25.0" error?

Now, how do I integrate a map using mapsforge into a Jetpack Compose layout, instead of an XML layout?

Can you explain each step of the code you have provided?

What is "InternalRenderTheme"?

When was InternalRenderTheme added to mapsforge? [Nicht im Prompt: In der Realität, InternalRenderTheme ist eine veraltete Form von der Klasse "MapsforgeThemes". Claude hat das nicht versteht, ich musste im Github Changelog suchen dafür.]

what is the purpose of tileRendererLayer = rendererLayer?

What should I do to create an AndroidManifest.xml if it does not exist?

is it possible to make mapsforge render from an online map file, over a url?

When I try to display, I continually get an "java.lang.NullPointerException: Attempt to get length of null array" error.

These are the lines that followed in the stack trace:
at org.mapsforge.map.layer.download.TileDownloadLayer.start(TileDownloadLayer.java:128)
at com.example.bahn_zeitkarten_tracker.ui.MapLayoutKt.MapsforgeMap$lambda$6$0(MapLayout.kt:76)
at com.example.bahn_zeitkarten_tracker.ui.MapLayoutKt.$r8$lambda$G6Rp-0aScHZ5SauYyLqXpdJiK94(MapLayout.kt:0)
at com.example.bahn_zeitkarten_tracker.ui.MapLayoutKt$$ExternalSyntheticLambda1.invoke(D8$$SyntheticClass:0)

I am repeatedly getting "java.util.concurrent.ExecutionException: java.lang.NullPointerException: Attempt to invoke virtual method 'java.io.File android.content.Context.getCacheDir()' on a null object reference", constantly.
