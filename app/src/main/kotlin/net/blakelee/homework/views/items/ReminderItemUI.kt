package net.blakelee.homework.views.items

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class ReminderItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            percentRelativeLayout {

                val text = editText {
                    id = R.id.days
                    backgroundResource = R.drawable.rounded_left
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                }

                val time = button {
                    id = R.id.time
                    backgroundResource = R.drawable.square
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(text)
                }

                val type = button {
                    id = R.id.type
                    backgroundResource = R.drawable.square
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                    rightOf(time)
                }

                button {
                    id = R.id.remove
                    backgroundResource = R.drawable.rounded_right
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                    rightOf(type)
                }

                lparams(height = wrapContent)
            }
        }
    }
}