package net.blakelee.homework.views.items

import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class ReminderItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            percentRelativeLayout {
                editText {
                    id = R.id.days
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                }

                imageButton {
                    id = R.id.time
                    backgroundResource = R.drawable.rounded_left
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                }

                imageButton {
                    id = R.id.type
                    backgroundResource = R.drawable.square
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                }

                imageButton {
                    id = R.id.remove
                    backgroundResource = R.drawable.rounded_right
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                }
            }
        }
    }
}