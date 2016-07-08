package net.blakelee.homework.mainactivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import net.blakelee.homework.R
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity (){

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)

        setContentView(R.layout.activity_main)
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.defaultmenu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}