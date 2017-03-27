package net.blakelee.homework.activities

import android.os.Bundle
import net.blakelee.homework.R
import net.blakelee.homework.views.CalendarUI
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.roomorama.caldroid.CaldroidFragment
import org.jetbrains.anko.*
import java.util.*


class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CalendarUI().setContentView(this)
        setSupportActionBar(find<Toolbar>(R.id.toolbar_calendar) as Toolbar?)
        initCalendar()
    }

    fun initCalendar() {
        val cf = CaldroidFragment()
        val args = Bundle()
        val cal = Calendar.getInstance()
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1)
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR))
        args.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true)
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false)
        cf.arguments = args
        val ft = supportFragmentManager.beginTransaction() //Have to split up the call otherwise no activity error
        ft.add(R.id.calendar_view, cf)
        ft.commit()
    }
}