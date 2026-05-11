package com.example.bahn_zeitkarten_tracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.EdgeToEdge
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

class loc{

}

class Zeitkarten {
    Name:       String,
    Firma:      String,
    Link:       String,
    giltv:      date,
    giltb:      date

    // giltvon und giltbis sind inklusive, d.h. deren Datum ist noch in der Gültigkeit inkludiert
    // alle Daten sind vom User selbst bestimmbar.
}

class VgFahrt {
    von:    loc,
    bis:    loc,
    dayt:   datetime,
    Co2:    float,
    Price:  Int,
    dist:   Int,

    // Price is saved in Cents
    // distance is saved as 1/10 of a km so we can display it with a , without wasting space with float
    // dayt saves the time the journey officially started
    // Co2 saves kg of Co2 saved
}


class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null

    @Override
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdge.enable(this)

        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, { v, insets ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        })
        setSupportActionBar(binding.toolbar)

        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        appBarConfiguration = Builder(navController.getGraph()).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.fab.setOnClickListener(object : OnClickListener() {
            @Override
            fun onClick(view: View?) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .setAction("Action", null).show()
            }
        })
    }

    @Override
    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    @Override
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @Override
    fun onSupportNavigateUp(): Boolean {
        val navController: NavController? =
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}