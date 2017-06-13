package net.blakelee.homework.views

import android.content.res.ColorStateList
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AssignmentUI<out T: RecyclerView.Adapter<*>>(val listAdapter: T): AnkoComponent<Fragment> {
    override fun createView(ui: AnkoContext<Fragment>): View {
        return with(ui) {
            coordinatorLayout {

                floatingActionButton {
                    id = R.id.fab_main
                    imageResource = R.drawable.ic_add_black_24dp
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.accent))
                }.lparams {
                    margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                    gravity = Gravity.BOTTOM or GravityCompat.END
                }

                recyclerView {
                    id = R.id.assignment
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(ctx)
                    addItemDecoration(DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL))
                }
            }
        }
    }
}