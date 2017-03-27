package net.blakelee.homework.views

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout

class CalendarUI : AnkoComponent<AppCompatActivity> {
    override fun createView(ui: AnkoContext<AppCompatActivity>):View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                backgroundColor = ContextCompat.getColor(ctx, R.color.primary)

                toolbar {
                    id = R.id.toolbar_calendar
                    setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                    overflowIcon!!.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(ctx, R.color.icons), PorterDuff.Mode.MULTIPLY)
                }
            }.lparams(width = matchParent)

            verticalLayout {
                //TODO: Figure out R.attr.* instead of a hardcoded number. R.attr.actionBarSize didn't work
                topPadding = dip(56)
                id = R.id.calendar_view
            }
        }
    }
}