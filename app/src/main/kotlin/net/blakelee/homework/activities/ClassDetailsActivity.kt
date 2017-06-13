package net.blakelee.homework.activities

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ClassDetailsPagerAdapter
import net.blakelee.homework.views.ClassDetailsUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class ClassDetailsActivity : AppCompatActivity() {

    private var classId : Long? = null
    private var color: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        classId = bundle?.getLong("class_id")
        color = bundle.getInt("color")

        ClassDetailsUI().setContentView(this)

        setupTabs()
        setupToolbar()
    }

    private fun setupTabs() {
        val tabLayout = find<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("Details"))
        tabLayout.addTab(tabLayout.newTab().setText("Homework"))
        tabLayout.addTab(tabLayout.newTab().setText("Exams"))
        tabLayout.addTab(tabLayout.newTab().setText("Notes"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val pager = find<ViewPager>(R.id.class_pager)
        val pagerAdapter = ClassDetailsPagerAdapter(supportFragmentManager, 4, classId!!, color)

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

    private fun setupToolbar() {
        val toolbar by lazy { find<Toolbar>(R.id.toolbar_main) }
        setSupportActionBar(toolbar)

        val arrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)

        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setTitle(R.string.class_details)
    }
}
