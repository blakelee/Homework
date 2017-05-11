package net.blakelee.homework.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ClassDetailsPagerAdapter
import net.blakelee.homework.views.ClassDetailsUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class ClassDetailsActivity : FragmentActivity() {

    private var classId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        classId = bundle?.getInt("class_id")

        ClassDetailsUI().setContentView(this)

        val tabLayout = find<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("Class Details"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val pager = find<ViewPager>(R.id.class_pager)
        val pagerAdapter = ClassDetailsPagerAdapter(supportFragmentManager, 1, classId!!)

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
