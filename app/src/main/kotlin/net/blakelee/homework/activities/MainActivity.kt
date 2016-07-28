package net.blakelee.homework.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import net.blakelee.homework.R
import kotlinx.android.synthetic.main.toolbar_main.*
import net.blakelee.homework.adapters.MainActivityAdapter
import net.blakelee.homework.views.MainUI
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(){

    private var classAdapter: MainActivityAdapter? = null

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)

        /*
        Need to add toolbar to MainUI Instead of being separate in AppUI
         */
        MainUI(classAdapter).setContentView(this)
        val toolbar = find<R.id.toolbar_main>()
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.defaultmenu, menu)
        return true
    }

    override fun onDestroy() = super.onDestroy()

}