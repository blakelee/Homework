package net.blakelee.homework.views

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.MainActivityAdapter
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout

class MainUI(val listAdapter: MainActivityAdapter) : AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>): View {
        return with(ui) {
            verticalLayout {
                appBarLayout {
                    backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)

                    toolbar {
                        id = R.id.toolbar_main
                        lparams(width = matchParent, height = wrapContent)
                        minimumHeight = R.attr.actionBarSize
                        setTitleTextColor(ContextCompat.getColor(ctx, R.color.backgroundGrey))
                    }
                }

                recyclerView {
                    id = R.id.class_list
                    lparams(width = matchParent, height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = listAdapter
                }
            }
        }
    }
}