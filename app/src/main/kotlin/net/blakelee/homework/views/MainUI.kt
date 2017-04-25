package net.blakelee.homework.views

import android.app.Activity
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.MainActivityAdapter
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.floatingActionButton
import android.support.v4.view.GravityCompat
import org.jetbrains.anko.design.coordinatorLayout
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.DividerItemDecoration
import net.blakelee.homework.activities.EditClassActivity

class MainUI(val listAdapter: MainActivityAdapter) : AnkoComponent<Activity> {

    override fun createView(ui: AnkoContext<Activity>): View {
        return with(ui) {
            coordinatorLayout {

                appBarLayout {
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)

                    toolbar {

                        id = R.id.toolbar_main
                        setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                        overflowIcon!!.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(ctx, R.color.icons), PorterDuff.Mode.MULTIPLY)
                    }
                }.lparams(width = matchParent)

                floatingActionButton {
                    id = R.id.fab_main
                    imageResource = R.drawable.ic_add_black_24dp
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx,R.color.accent))

                    setOnClickListener {
                        startActivity<EditClassActivity>()
                    }
                }.lparams {
                    margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                    gravity = Gravity.BOTTOM or GravityCompat.END
                }

                verticalLayout {
                    recyclerView {
                        id = R.id.class_list
                        lparams(width = matchParent, height = matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = listAdapter
                        addItemDecoration(DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL))
                    }
                }.lparams {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }
        }
    }
}