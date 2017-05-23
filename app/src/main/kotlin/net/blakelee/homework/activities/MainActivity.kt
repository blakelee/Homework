package net.blakelee.homework.activities

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ClassesAdapter
import net.blakelee.homework.base.BaseLifecycleActivity
import net.blakelee.homework.interfaces.MainInterface
import net.blakelee.homework.models.Classes
import net.blakelee.homework.viewmodels.MainActivityViewModel
import net.blakelee.homework.views.MainUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : BaseLifecycleActivity<MainActivityViewModel>(), MainInterface {
    private val adapter = ClassesAdapter()
    override val viewModelClass = MainActivityViewModel::class.java

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)

        viewModel.getClasses().observe(this, Observer<List<Classes>> {
            it?.let { adapter.dataSource = it }
        })

        MainUI(adapter).setContentView(this)
        setSupportActionBar(find<Toolbar>(R.id.toolbar_main))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.defaultmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_about -> startActivity<AboutActivity>()
            R.id.action_calandar -> startActivity<CalendarActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    //TODO: Return dialog with edit and delete options
    override fun showMenu(name: String, id: Long): Boolean {
        return true
    }
}