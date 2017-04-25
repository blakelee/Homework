package net.blakelee.homework.views

import android.content.res.ColorStateList
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class ClassDetailsUI : AnkoComponent<FragmentActivity> {

    override fun createView(ui: AnkoContext<FragmentActivity>): View {
        return with(ui) {
            relativeLayout {
                tabLayout {
                    id = R.id.tab_layout
                    minimumHeight = R.attr.actionBarSize
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                    tabTextColors = ColorStateList.valueOf(ContextCompat.getColor(ctx, android.R.color.white))
                    tabGravity = TabLayout.GRAVITY_FILL
                    tabMode = TabLayout.MODE_FIXED
                    setSelectedTabIndicatorColor(ContextCompat.getColor(ctx, R.color.accent))
                }.lparams {
                    width = matchParent
                }

                viewPager {
                    id = R.id.class_pager
                }.lparams {
                    below(R.id.tab_layout)
                }
            }

        }
    }
}