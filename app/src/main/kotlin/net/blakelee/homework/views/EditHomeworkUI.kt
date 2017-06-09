package net.blakelee.homework.views

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import net.blakelee.homework.R
import net.blakelee.homework.models.Homework
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.percent.percentRelativeLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView


class EditHomeworkUI(var hw: Homework) : AnkoComponent<AppCompatActivity> {

    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        verticalLayout {
            appBarLayout {
                toolbar {
                    setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                    id = R.id.toolbar_edit
                    inflateMenu(R.menu.edit_class_menu)
                }
            }

            textView("Title")
            editText {

            }

            textView("Problems")
            editText {

            }

            textView("Description")
            editText {

            }

            textView("Due")
            percentRelativeLayout {
                lparams(width = matchParent, height = wrapContent)

                val left = button {
                    backgroundResource = R.drawable.rounded_left
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.5f
                }

                button {
                    backgroundResource = R.drawable.rounded_right
                }.lparams {
                    rightOf(left)
                    percentLayoutInfo.widthPercent = 0.5f
                }
            }

            textView("Reminder")
            percentRelativeLayout {
                val days = textView("Days before").lparams {percentLayoutInfo.widthPercent = 0.25f}
                val time = textView("Time")
                val type = textView("Type")
            }
            recyclerView {
                id = R.id.homework_recycler
            }

            imageButton {
                id = R.id.add
                setImageResource(R.drawable.ic_add_black_24dp)
                setColorFilter(android.R.color.black, PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
}