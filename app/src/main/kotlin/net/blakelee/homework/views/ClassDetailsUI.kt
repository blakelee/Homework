package net.blakelee.homework.views

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class ClassDetailsUI : AnkoComponent<FragmentActivity> {

    override fun createView(ui: AnkoContext<FragmentActivity>): View {
        return with(ui) {
            relativeLayout {
                val apl = appBarLayout {
                    id = R.id.app_bar_layout
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)

                    toolbar {
                        id = R.id.toolbar_main
                        setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                        overflowIcon!!.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(ctx, R.color.icons), PorterDuff.Mode.MULTIPLY)
                    }
                }.lparams(width = matchParent)

                val tl = tabLayout {
                    id = R.id.tab_layout
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                    tabTextColors = ColorStateList.valueOf(ContextCompat.getColor(ctx, android.R.color.white))
                    tabGravity = TabLayout.GRAVITY_FILL
                    tabMode = TabLayout.MODE_FIXED
                    setSelectedTabIndicatorColor(ContextCompat.getColor(ctx, R.color.accent))
                }.lparams {
                    width = matchParent
                    below(apl)
                }

                viewPager {
                    id = R.id.class_pager
                }.lparams {
                    below(tl)
                }
            }

        }
    }
}