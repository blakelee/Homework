package net.blakelee.homework.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import io.realm.RealmList
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ClassDetailsPagerAdapter
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Day
import net.blakelee.homework.models.Week
import net.blakelee.homework.views.ClassDetailsUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class ClassDetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Dummy data
        val classDetails = ClassDetails("Psychology 101", RealmList(Week(), Week()), "", "", "test@gmail.com", "(555) 123-4567", null, "", "Mrs. Puff", Day())

        ClassDetailsUI().setContentView(this)

        val tabLayout = find<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val pager = find<ViewPager>(R.id.class_pager)
        val pagerAdapter = ClassDetailsPagerAdapter(supportFragmentManager, 1, classDetails)

        pager.adapter = pagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }
        })
    }
}
